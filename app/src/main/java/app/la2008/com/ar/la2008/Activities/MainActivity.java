package app.la2008.com.ar.la2008.Activities;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;

import java.util.ArrayList;

import app.la2008.com.ar.la2008.Models.PlayerSummary;
import app.la2008.com.ar.la2008.Views.PlayerView;
import app.la2008.com.ar.la2008.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.player1) PlayerView player1;
    @BindView(R.id.player2) PlayerView player2;
    @BindView(R.id.player3) PlayerView player3;
    @BindView(R.id.player4) PlayerView player4;
    @BindView(R.id.player5) PlayerView player5;
    @BindView(R.id.player6) PlayerView player6;
    @BindView(R.id.player7) PlayerView player7;
    @BindView(R.id.player8) PlayerView player8;
    @BindView(R.id.player9) PlayerView player9;
    @BindView(R.id.player10) PlayerView player10;
    @BindView(R.id.player11) PlayerView player11;
    @BindView(R.id.player12) PlayerView player12;
    @BindView(R.id.startButton) Button mStartButton;

    private ArrayList<PlayerView> players = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
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

        View.OnClickListener playerListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayerView playerView = (PlayerView) view;
                if(!playerView.isChecked()) {
                    playerView.setChecked(true);
                    players.add(playerView);
                }
                else {
                    playerView.setChecked(false);
                    players.remove(playerView);
                }
            }
        };

        player1.setOnClickListener(playerListener);
        player2.setOnClickListener(playerListener);
        player3.setOnClickListener(playerListener);
        player4.setOnClickListener(playerListener);
        player5.setOnClickListener(playerListener);
        player6.setOnClickListener(playerListener);
        player7.setOnClickListener(playerListener);
        player8.setOnClickListener(playerListener);
        player9.setOnClickListener(playerListener);
        player10.setOnClickListener(playerListener);
        player11.setOnClickListener(playerListener);
        player12.setOnClickListener(playerListener);

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putParcelable("player1", player1.getPlayerSummary());
        outState.putParcelable("player2", player2.getPlayerSummary());
        outState.putParcelable("player3", player3.getPlayerSummary());
        outState.putParcelable("player4", player4.getPlayerSummary());
        outState.putParcelable("player5", player5.getPlayerSummary());
        outState.putParcelable("player6", player6.getPlayerSummary());
        outState.putParcelable("player7", player7.getPlayerSummary());
        outState.putParcelable("player8", player8.getPlayerSummary());
        outState.putParcelable("player9", player9.getPlayerSummary());
        outState.putParcelable("player10", player10.getPlayerSummary());
        outState.putParcelable("player11", player11.getPlayerSummary());
        outState.putParcelable("player12", player12.getPlayerSummary());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.player1.setData((PlayerSummary)savedInstanceState.getParcelable("player1"));
        this.player2.setData((PlayerSummary)savedInstanceState.getParcelable("player2"));
        this.player3.setData((PlayerSummary)savedInstanceState.getParcelable("player3"));
        this.player4.setData((PlayerSummary)savedInstanceState.getParcelable("player4"));
        this.player5.setData((PlayerSummary)savedInstanceState.getParcelable("player5"));
        this.player6.setData((PlayerSummary)savedInstanceState.getParcelable("player6"));
        this.player7.setData((PlayerSummary)savedInstanceState.getParcelable("player7"));
        this.player8.setData((PlayerSummary)savedInstanceState.getParcelable("player8"));
        this.player9.setData((PlayerSummary)savedInstanceState.getParcelable("player9"));
        this.player10.setData((PlayerSummary)savedInstanceState.getParcelable("player10"));
        this.player11.setData((PlayerSummary)savedInstanceState.getParcelable("player11"));
        this.player12.setData((PlayerSummary)savedInstanceState.getParcelable("player12"));
    }

    @OnClick(R.id.startButton)
    public void chronometer() {
        if(mStartButton.getText().toString().equalsIgnoreCase("Start")) {
            if(players.size() > 5 || players.size() < 3) {
                Toast.makeText(this, "Seleccionar 5 jugadores", Toast.LENGTH_SHORT).show();
                return;
            }
            synchronized (players) {
                for (PlayerView player : players) {
                    player.startChrono();
                    player.setButtonsClickable(true);
                }
            }
            player1.setClickable(false);
            player2.setClickable(false);
            player3.setClickable(false);
            player4.setClickable(false);
            player5.setClickable(false);
            player6.setClickable(false);
            player7.setClickable(false);
            player8.setClickable(false);
            player9.setClickable(false);
            player10.setClickable(false);
            player11.setClickable(false);
            player12.setClickable(false);
            mStartButton.setText("Stop");
        }
        else {
            synchronized (players) {
                for (PlayerView player : players) {
                    player.stopChrono();
                    player.setButtonsClickable(false);
                }
            }
            player1.setClickable(true);
            player2.setClickable(true);
            player3.setClickable(true);
            player4.setClickable(true);
            player5.setClickable(true);
            player6.setClickable(true);
            player7.setClickable(true);
            player8.setClickable(true);
            player9.setClickable(true);
            player10.setClickable(true);
            player11.setClickable(true);
            player12.setClickable(true);
            mStartButton.setText("Start");
        }
    }

    @OnClick(R.id.summaryButton)
    public void summary() {
        Intent intent = new Intent(this, SummaryActivity.class);
        ArrayList<PlayerSummary> summary = new ArrayList<>();
        summary.add(player1.getPlayerSummary());
        summary.add(player2.getPlayerSummary());
        summary.add(player3.getPlayerSummary());
        summary.add(player4.getPlayerSummary());
        summary.add(player5.getPlayerSummary());
        summary.add(player6.getPlayerSummary());
        summary.add(player7.getPlayerSummary());
        summary.add(player8.getPlayerSummary());
        summary.add(player9.getPlayerSummary());
        summary.add(player10.getPlayerSummary());
        summary.add(player11.getPlayerSummary());
        summary.add(player12.getPlayerSummary());
        SummaryActivity.playersSummaries = summary;
        startActivity(intent);
    }

}
