package app.la2008.com.ar.la2008.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.la2008.com.ar.la2008.R;
import app.la2008.com.ar.la2008.models.PlayerSummary;
import app.la2008.com.ar.la2008.util.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayerStatsView extends LinearLayout {

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

    private Boolean header = false;

    public PlayerStatsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_player_stats, this, true);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PlayerStatsView);
        this.header = a.getBoolean(R.styleable.PlayerStatsView_headerStats, false);
        a.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);

        if (header) {
            this.playerNameTextView
                    .setText(getResources().getString(R.string.stats_name).toUpperCase());
            this.playerTimePlayedTextView
                    .setText(getResources().getString(R.string.stats_time).toUpperCase());
            this.playerPointsTextView
                    .setText(getResources().getString(R.string.stats_points).toUpperCase());
            this.playerFreeThrowsMadeTextView
                    .setText(getResources().getString(R.string.stats_ftm).toUpperCase());
            this.playerFreeThrowsAttempedTextView
                    .setText(getResources().getString(R.string.stats_fta).toUpperCase());
            this.playerFieldGoalsMadeTextView
                    .setText(getResources().getString(R.string.stats_fgm).toUpperCase());
            this.playerFieldGoalsAttempedTextView
                    .setText(getResources().getString(R.string.stats_fga).toUpperCase());
            this.playerThreePointsMadeTextView
                    .setText(getResources().getString(R.string.stats_3pm).toUpperCase());
            this.playerThreePointsAttempedTextView
                    .setText(getResources().getString(R.string.stats_3pa).toUpperCase());
            this.playerReboundsTextView
                    .setText(getResources().getString(R.string.stats_reb).toUpperCase());
            this.playerAssistsTextView
                    .setText(getResources().getString(R.string.stats_ast).toUpperCase());
            this.playerStealsTextView
                    .setText(getResources().getString(R.string.stats_stl).toUpperCase());
            this.playerBlocksTextView
                    .setText(getResources().getString(R.string.stats_blk).toUpperCase());
            this.playerTurnoversTextView
                    .setText(getResources().getString(R.string.stats_tov).toUpperCase());
            this.playerFoulsTextView
                    .setText(getResources().getString(R.string.stats_pf).toUpperCase());
        }
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
        this.playerStealsTextView.setText(String.valueOf(playerSummary.stl));
        this.playerBlocksTextView.setText(String.valueOf(playerSummary.blk));
        this.playerTurnoversTextView.setText(String.valueOf(playerSummary.tov));
        this.playerFoulsTextView.setText(String.valueOf(playerSummary.pf));
    }
}
