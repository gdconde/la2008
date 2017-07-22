package app.la2008.com.ar.la2008.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.la2008.com.ar.la2008.models.PlayerSummary;
import app.la2008.com.ar.la2008.util.Utils;
import app.la2008.com.ar.la2008.views.PlayerViewCompact;
import app.la2008.com.ar.la2008.R;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.fabric.sdk.android.Fabric;

public class InGameActivity extends Activity {

    private static final int IN_GAME_ACTIVITY_REQUEST = 2;

    @BindViews({
            R.id.player1,
            R.id.player2,
            R.id.player3,
            R.id.player4,
            R.id.player5,
            R.id.player6,
            R.id.player7,
            R.id.player8,
            R.id.player9,
            R.id.player10,
            R.id.player11,
            R.id.player12})
    List<PlayerViewCompact> players;
    @BindView(R.id.startButton) Button mStartButton;

    private String gameKey;
    private String gameName;
    private FirebaseDatabase mDatabase;
    private ArrayList<PlayerSummary> playersOnCourt = new ArrayList<>();

    private final View.OnClickListener playerListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            PlayerViewCompact playerView = (PlayerViewCompact) view;
            if(!playerView.isChecked()) {
                playerView.setChecked(true);
                playerView.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                playersOnCourt.add(playerView.getPlayerSummary());
            }
            else {
                playerView.setChecked(false);
                playerView.setBackgroundColor(getResources().getColor(android.R.color.white));
                playersOnCourt.remove(playerView.getPlayerSummary());
            }
        }
    };

    public static void start(Context context, ArrayList<String> playersNames, String gameName) {
        Intent starter = new Intent(context, InGameActivity.class);
        starter.putStringArrayListExtra("players_names", playersNames);
        starter.putExtra("game_name", gameName);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_in_game);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ButterKnife.bind(this);

        final ButterKnife.Action<PlayerViewCompact> SET_LISTENER = new ButterKnife.Action<PlayerViewCompact>() {
            @Override
            public void apply(@NonNull PlayerViewCompact view, int index) {
                view.setOnClickListener(playerListener);
            }
        };

        ButterKnife.apply(this.players, SET_LISTENER);

        Intent intent = getIntent();
        this.gameName = intent.getStringExtra("game_name");
        getActionBar().setTitle(this.gameName);

        ArrayList<String> playersNames = intent.getStringArrayListExtra("players_names");
        final ButterKnife.Setter<PlayerViewCompact, ArrayList<String>> SET_NAMES = new ButterKnife.Setter<PlayerViewCompact, ArrayList<String>>() {
            @Override
            public void set(@NonNull PlayerViewCompact view, ArrayList<String> names, int index) {
                view.setName(names.get(index));
            }
        };
        ButterKnife.apply(this.players, SET_NAMES, playersNames);

        this.mDatabase = Utils.getDatabase();
        this.gameKey = this.mDatabase.getReference().push().getKey();
        Map<String, Object> liveGamesUpdate = new HashMap<>();
        liveGamesUpdate.put("games_live/" + this.gameKey, gameName);
        this.mDatabase.getReference().updateChildren(liveGamesUpdate);
        DatabaseReference reference = this.mDatabase.getReference(this.gameKey);
        for (PlayerViewCompact playerView: this.players) {
            PlayerSummary playerSummary = playerView.getPlayerSummary();
            String playerKey = reference.push().getKey();
            playerView.setKey(playerKey);
            reference.child(playerKey).setValue(playerSummary);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IN_GAME_ACTIVITY_REQUEST && resultCode == RESULT_OK) {
            playersOnCourt = data.getParcelableArrayListExtra("players");
            updatePlayersData();
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder
                .setTitle(R.string.prompt_exit_game)
                .setMessage(R.string.prompt_exit_message)
                .setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { discardGame(); }
                })
                .setPositiveButton("Finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { finishGame(); }
                })
                .create()
                .show();
    }

    @OnClick(R.id.startButton)
    public void startOrContinueGame() {
        if (this.playersOnCourt.size() != 5) {
            Toast.makeText(this, R.string.must_select_5_players, Toast.LENGTH_SHORT).show();
            return;
        }
        Intent inGameActivityIntent = new Intent(this, PlayingActivity.class);
        inGameActivityIntent.putParcelableArrayListExtra("players", this.playersOnCourt);
        startActivityForResult(inGameActivityIntent, IN_GAME_ACTIVITY_REQUEST);
    }

    @OnClick(R.id.finishGameButton)
    public void finishGameClicked() {
        finishGame();
    }

    private void updatePlayersData() {
        for (PlayerSummary playerToUpdate: this.playersOnCourt) {
            this.players.get(playerToUpdate.index).setData(playerToUpdate);
            mDatabase
                    .getReference(this.gameKey + "/" + playerToUpdate.key)
                    .setValue(playerToUpdate);
        }
    }

    private void discardGame() {
        //Discard Game
        Log.v("GAME: ", "DISCARDED");
        mDatabase.getReference(gameKey).removeValue();
        mDatabase.getReference("games_live/" + gameKey).removeValue();
        finish();
    }

    private void finishGame() {
        //Finish Game
        Log.v("GAME: ", "FINISHED");
        mDatabase.getReference("games_live/" + gameKey).removeValue();
        Map<String, Object> finishedGamesUpdate = new HashMap<>();
        finishedGamesUpdate.put("games_finished/" + gameKey, this.gameName);
        mDatabase.getReference().updateChildren(finishedGamesUpdate);
        finish();
    }

}
