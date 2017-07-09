package app.la2008.com.ar.la2008.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import app.la2008.com.ar.la2008.models.PlayerSummary;
import app.la2008.com.ar.la2008.R;
import app.la2008.com.ar.la2008.views.PlayerViewFull;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InGameActivity extends Activity {

    @BindViews({R.id.player1, R.id.player2, R.id.player3, R.id.player4, R.id.player5})
    List<PlayerViewFull> playersOnCourt;

    ArrayList<PlayerSummary> players = new ArrayList<>();

    final ButterKnife.Setter<PlayerViewFull, Boolean> SET_CHRONO = new ButterKnife.Setter<PlayerViewFull, Boolean>() {
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
        setContentView(R.layout.activity_in_game);
        players = getIntent().getParcelableArrayListExtra("players");
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
    }

    @OnClick(R.id.stopButton)
    public void stop() {
        ButterKnife.apply(this.playersOnCourt, SET_CHRONO, false);

        ArrayList<PlayerSummary> playerSummaries = new ArrayList<>();
        for (PlayerViewFull playerView: this.playersOnCourt) {
            playerSummaries.add(playerView.getPlayerSummary());
        }
        Intent resultIntent = new Intent();
        resultIntent.putParcelableArrayListExtra("players", playerSummaries);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
