package app.la2008.com.ar.la2008.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import app.la2008.com.ar.la2008.models.PlayerSummary;
import app.la2008.com.ar.la2008.R;
import app.la2008.com.ar.la2008.util.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlayerViewCompact extends LinearLayout {

    @BindView(R.id.checkbox) CheckBox checkBox;
    @BindView(R.id.name) TextView name;
    @BindView(R.id.chrono) Chronometer chrono;
    @BindView(R.id.pointsTextView) TextView points;
    @BindView(R.id.reboundsTextView) TextView rebounds;
    @BindView(R.id.assistsTextView) TextView assists;
    @BindView(R.id.foulsTextView) TextView fouls;
    @BindView(R.id.reboundButton) Button rebButton;
    @BindView(R.id.assistButton) Button astButton;
    @BindView(R.id.foulButton) Button foulButton;
    @BindView(R.id.chronoHeader) TextView chronoHeader;
    @BindView(R.id.reboundHeader) TextView reboundsHeader;
    @BindView(R.id.assistHeader) TextView assistsHeader;
    @BindView(R.id.foulHeader) TextView foulsHeader;

    private final PlayerSummary player = new PlayerSummary();

    public PlayerViewCompact(Context context, AttributeSet attributes) {
        super(context, attributes);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_player_compact, this, true);
        ButterKnife.bind(this);

        this.player.tag = "0";

        TypedArray a = context.obtainStyledAttributes(attributes, R.styleable.PlayerViewCompact);

        String playerName = a.getString(R.styleable.PlayerViewCompact_name);
        if(playerName != null) {
            name.setText(playerName);
            this.player.name = playerName;
        }

        Integer playerIndex = a.getInteger(R.styleable.PlayerViewCompact_index, -1);
        if(playerIndex != -1) {
            this.player.index = playerIndex;
        }

        Boolean header = a.getBoolean(R.styleable.PlayerViewCompact_headerCompact, false);

        a.recycle();

        if (header) {
            this.name.setText(R.string.compact_view_name);
            this.chronoHeader.setText(R.string.compact_view_time);
            this.points
                    .setText(getResources().getString(R.string.compact_view_pts).toUpperCase());
            this.rebounds
                    .setText(getResources().getString(R.string.compact_view_reb).toUpperCase());
            this.assists
                    .setText(getResources().getString(R.string.compact_view_ast).toUpperCase());
            this.fouls
                    .setText(getResources().getString(R.string.compact_view_pf).toUpperCase());
            this.reboundsHeader
                    .setText(getResources().getString(R.string.compact_view_plus_reb).toUpperCase());
            this.assistsHeader
                    .setText(getResources().getString(R.string.compact_view_plus_ast).toUpperCase());
            this.foulsHeader
                    .setText(getResources().getString(R.string.compact_view_plus_pf).toUpperCase());

            this.chrono.setVisibility(GONE);
            this.rebButton.setVisibility(GONE);
            this.astButton.setVisibility(GONE);
            this.foulButton.setVisibility(GONE);
            this.chronoHeader.setVisibility(VISIBLE);
            this.reboundsHeader.setVisibility(VISIBLE);
            this.assistsHeader.setVisibility(VISIBLE);
            this.foulsHeader.setVisibility(VISIBLE);
        }
    }

    public Boolean isChecked() {
        return checkBox.isChecked();
    }

    public PlayerViewCompact setChecked(boolean check) {
        checkBox.setChecked(check);
        return this;
    }

    public PlayerViewCompact setName(String name) {
        this.name.setText(name);
        this.player.name = name;
        return this;
    }

    public PlayerViewCompact setKey(String key) {
        this.player.key = key;
        return this;
    }

    public PlayerSummary getPlayerSummary() {
        return player;
    }

    public PlayerViewCompact setData(PlayerSummary data) {
        this.name.setText(data.name);
        this.chrono.setText(Utils.secondsToString(data.secondsPlayed));
        this.chrono.setTag(data.tag);
        this.player.name = data.name;
        this.player.tag = data.tag;
        this.player.ftm = data.ftm;
        this.player.fta = data.fta;
        this.player.fgm = data.fgm;
        this.player.fga = data.fga;
        this.player.tpm = data.tpm;
        this.player.tpa = data.tpa;
        this.player.ast = data.ast;
        this.player.reb = data.reb;
        this.player.pf = data.pf;
        this.player.blk = data.blk;
        this.player.stl = data.stl;
        this.player.tov = data.tov;
        this.player.key = data.key;
        this.points.setText(String.valueOf(
                this.player.ftm + 2 * this.player.fgm + 3 * this.player.tpm));
        this.rebounds.setText(String.valueOf(this.player.reb));
        this.assists.setText(String.valueOf(this.player.ast));
        this.fouls.setText(String.valueOf(this.player.pf));
        return this;
    }

    private void showToast() {
        final Toast toast = Toast.makeText(getContext(), "+1", Toast.LENGTH_SHORT);
        toast.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 500);
    }

    @OnClick(R.id.reboundButton)
    public void reboundsButtonClicked() {
        rebounds.setText(String.valueOf(++player.reb));
        showToast();
    }

    @OnClick(R.id.assistButton)
    public void assistsButtonClicked() {
        assists.setText(String.valueOf(++player.ast));
        showToast();
    }

    @OnClick(R.id.foulButton)
    public void foulButtonClicked() {
        fouls.setText(String.valueOf(++player.pf));
        showToast();
    }
}
