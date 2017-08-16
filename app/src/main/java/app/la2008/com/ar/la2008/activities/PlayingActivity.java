package app.la2008.com.ar.la2008.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import app.la2008.com.ar.la2008.R;
import app.la2008.com.ar.la2008.models.PlayerSummary;
import app.la2008.com.ar.la2008.views.PlayerViewFull;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlayingActivity extends BaseActivity {

    @BindViews({R.id.player1, R.id.player2, R.id.player3, R.id.player4, R.id.player5})
    List<PlayerViewFull> playersOnCourt;

    private ArrayList<PlayerSummary> players = new ArrayList<>();
    private long startTime;
    private long totalTime;


    private final ButterKnife.Setter<PlayerViewFull, Boolean> SET_CHRONO = new ButterKnife.Setter<PlayerViewFull, Boolean>() {
        @Override
        public void set(@NonNull PlayerViewFull view, Boolean value, int index) {
            if (value) {
                view.startChrono();
            }
            else {
                view.stopChrono();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);
        this.players = getIntent().getParcelableArrayListExtra("players");
        this.totalTime = getIntent().getLongExtra("time", 0);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ButterKnife.bind(this);

        final ButterKnife.Action<PlayerViewFull> SET_DATA = new ButterKnife.Action<PlayerViewFull>() {
            @Override
            public void apply(@NonNull PlayerViewFull view, int index) {
                view.setData(players.get(index));
            }
        };

        ButterKnife.apply(this.playersOnCourt, SET_DATA);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ButterKnife.apply(this.playersOnCourt, SET_CHRONO, true);
        this.startTime = System.currentTimeMillis();
    }

    @OnClick(R.id.stopButton)
    public void stop() {
        ButterKnife.apply(this.playersOnCourt, SET_CHRONO, false);
        long endTime = System.currentTimeMillis();
        this.totalTime += (endTime - this.startTime) / 1000;

        ArrayList<PlayerSummary> playerSummaries = new ArrayList<>();
        for (PlayerViewFull playerView: this.playersOnCourt) {
            playerSummaries.add(playerView.getPlayerSummary());
        }
        Intent resultIntent = new Intent();
        resultIntent.putParcelableArrayListExtra("players", playerSummaries);
        resultIntent.putExtra("time", this.totalTime);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
