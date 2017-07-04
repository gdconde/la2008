package app.la2008.com.ar.la2008.Activities;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.la2008.com.ar.la2008.Models.PlayerSummary;
import app.la2008.com.ar.la2008.Views.PlayerViewCompact;
import app.la2008.com.ar.la2008.R;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

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

    private ArrayList<PlayerSummary> playersOnCourt = new ArrayList<>();

    View.OnClickListener playerListener = new View.OnClickListener() {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_main);
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
    }

    @OnClick(R.id.startButton)
    public void chronometer() {
        if (this.playersOnCourt.size() > 5 || this.playersOnCourt.size() < 4) {
            Toast.makeText(this, "Debes seleccionar 5 jugadores", Toast.LENGTH_SHORT).show();
            return;
        }
        InGameActivity.start(this, this.playersOnCourt);
        finish();
    }

    @OnClick(R.id.summaryButton)
    public void summary() {
//        Intent intent = new Intent(this, SummaryActivity.class);
//        ArrayList<PlayerSummary> summary = new ArrayList<>();
//        summary.add(player1.getPlayerSummary());
//        summary.add(player2.getPlayerSummary());
//        summary.add(player3.getPlayerSummary());
//        summary.add(player4.getPlayerSummary());
//        summary.add(player5.getPlayerSummary());
//        summary.add(player6.getPlayerSummary());
//        summary.add(player7.getPlayerSummary());
//        summary.add(player8.getPlayerSummary());
//        summary.add(player9.getPlayerSummary());
//        summary.add(player10.getPlayerSummary());
//        summary.add(player11.getPlayerSummary());
//        summary.add(player12.getPlayerSummary());
//        SummaryActivity.playersSummaries = summary;
//        startActivity(intent);
    }

}
