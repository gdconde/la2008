package app.la2008.com.ar.la2008.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import app.la2008.com.ar.la2008.R;
import app.la2008.com.ar.la2008.models.GameSignature;
import app.la2008.com.ar.la2008.models.PlayerSummary;
import app.la2008.com.ar.la2008.util.Utils;
import app.la2008.com.ar.la2008.views.PlayerStatsView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class WatchGameActivity extends BaseActivity {

    @BindViews({R.id.playerStatsView1,
            R.id.playerStatsView2,
            R.id.playerStatsView3,
            R.id.playerStatsView4,
            R.id.playerStatsView5,
            R.id.playerStatsView6,
            R.id.playerStatsView7,
            R.id.playerStatsView8,
            R.id.playerStatsView9,
            R.id.playerStatsView10,
            R.id.playerStatsView11,
            R.id.playerStatsView12,
            R.id.playerStatsView13,
            R.id.playerStatsView14})
    List<PlayerStatsView> playerStatsViews;
    private GameSignature game;
    private FirebaseDatabase mDatabase;

    public static void start(Context context, GameSignature game) {
        Intent starter = new Intent(context, WatchGameActivity.class);
        starter.putExtra("game", game);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_game);
        Intent intent = getIntent();
        this.game = intent.getParcelableExtra("game");
        this.mDatabase = Utils.getDatabase();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ButterKnife.bind(this);
        if (barTitle != null && barSubTitle != null) {
            barTitle.setText(this.game.name);
            barSubTitle.setText(Utils.gameTimeInSecondsToHumanString(this.game.time));
        }

        DatabaseReference dbReference = mDatabase.getReference(this.game.key);

        ChildEventListener playersListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                PlayerSummary player = dataSnapshot.getValue(PlayerSummary.class);
                if (player != null) {
                    playerStatsViews.get(player.index).setPlayerStats(player);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                PlayerSummary player = dataSnapshot.getValue(PlayerSummary.class);
                if (player != null) {
                    playerStatsViews.get(player.index).setPlayerStats(player);
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        dbReference.addChildEventListener(playersListener);
    }
}
