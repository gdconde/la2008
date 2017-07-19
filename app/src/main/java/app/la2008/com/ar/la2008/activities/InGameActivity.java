package app.la2008.com.ar.la2008.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

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

    public static void start(Context context, ArrayList<String> playersNames) {
        Intent starter = new Intent(context, InGameActivity.class);
        starter.putStringArrayListExtra("players_names", playersNames);
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

        ArrayList<String> playersNames = getIntent().getStringArrayListExtra("players_names");
        final ButterKnife.Setter<PlayerViewCompact, ArrayList<String>> SET_NAMES = new ButterKnife.Setter<PlayerViewCompact, ArrayList<String>>() {
            @Override
            public void set(@NonNull PlayerViewCompact view, ArrayList<String> names, int index) {
                view.setName(names.get(index));
            }
        };
        ButterKnife.apply(this.players, SET_NAMES, playersNames);

        this.mDatabase = Utils.getDatabase();
        this.gameKey = this.mDatabase.getReference("games").push().getKey();
        DatabaseReference reference = this.mDatabase.getReference("games/" + this.gameKey);
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

    @OnClick(R.id.summaryButton)
    public void summary() {

    }

    private void updatePlayersData() {
        for (PlayerSummary playerToUpdate: this.playersOnCourt) {
            this.players.get(playerToUpdate.index).setData(playerToUpdate);
            mDatabase
                    .getReference("games/" + this.gameKey + "/" + playerToUpdate.key)
                    .setValue(playerToUpdate);
        }
    }

}
