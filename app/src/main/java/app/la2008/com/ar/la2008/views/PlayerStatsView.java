package app.la2008.com.ar.la2008.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import app.la2008.com.ar.la2008.R;
import app.la2008.com.ar.la2008.models.PlayerSummary;
import app.la2008.com.ar.la2008.util.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gdconde on 7/19/17.
 */

public class PlayerStatsView extends ScrollView {

    @BindView(R.id.playerNameTextView) TextView playerNameTextView;
    @BindView(R.id.playerTimePlayedTextView) TextView playerTimePlayedTextView;
    @BindView(R.id.playerPointsTextView) TextView playerPointsTextView;
    @BindView(R.id.playerFreeThrowsMadeTextView) TextView playerFreeThrowsMadeTextView;
    @BindView(R.id.playerFreeThrowsAttempedTextView) TextView playerFreeThrowsAttempedTextView;
    @BindView(R.id.playerFieldGoalsMadeTextView) TextView playerFieldGoalsMadeTextView;
    @BindView(R.id.playerFieldGoalsAttempedTextView) TextView playerFieldGoalsAttempedTextView;
    @BindView(R.id.playerThreePointsMadeTextView) TextView playerThreePointsMadeTextView;
    @BindView(R.id.playerThreePointsAttempedTextView) TextView playerThreePointsAttempedTextView;
    @BindView(R.id.playerReboundsTextView) TextView playerReboundsTextView;
    @BindView(R.id.playerAssistsTextView) TextView playerAssistsTextView;
    @BindView(R.id.playerStealsTextView) TextView playerStealsTextView;
    @BindView(R.id.playerBlocksTextView) TextView playerBlocksTextView;
    @BindView(R.id.playerTurnoversTextView) TextView playerTurnoversTextView;
    @BindView(R.id.playerFoulsTextView) TextView playerFoulsTextView;

    public PlayerStatsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_player_stats, this, true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void setPlayerStats(PlayerSummary playerSummary) {
        this.playerNameTextView.setText(playerSummary.name);
        this.playerTimePlayedTextView.setText(Utils.secondsToString(playerSummary.secondsPlayed));
        this.playerPointsTextView.setText(String.valueOf(playerSummary.pts));
        this.playerFreeThrowsMadeTextView.setText(String.valueOf(playerSummary.ftm));
        this.playerFreeThrowsAttempedTextView.setText(String.valueOf(playerSummary.fta));
        this.playerFieldGoalsMadeTextView.setText(String.valueOf(playerSummary.fgm));
        this.playerFieldGoalsAttempedTextView.setText(String.valueOf(playerSummary.fga));
        this.playerThreePointsMadeTextView.setText(String.valueOf(playerSummary.tpm));
        this.playerThreePointsAttempedTextView.setText(String.valueOf(playerSummary.tpa));
        this.playerReboundsTextView.setText(String.valueOf(playerSummary.reb));
        this.playerAssistsTextView.setText(String.valueOf(playerSummary.ast));
        this.playerStealsTextView.setText(String.valueOf(playerSummary.rob));
        this.playerBlocksTextView.setText(String.valueOf(playerSummary.tap));
        this.playerTurnoversTextView.setText(String.valueOf(playerSummary.per));
        this.playerFoulsTextView.setText(String.valueOf(playerSummary.pf));
    }
}
