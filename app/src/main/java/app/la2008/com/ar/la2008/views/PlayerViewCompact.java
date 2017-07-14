package app.la2008.com.ar.la2008.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import app.la2008.com.ar.la2008.models.PlayerSummary;
import app.la2008.com.ar.la2008.R;

/**
 * Created by gdconde on 31/3/17.
 */

public class PlayerViewCompact extends LinearLayout {

    private final CheckBox checkBox;
    private final EditText name;
    private final Chronometer chrono;
    private final TextView points;
    private final TextView rebounds;
    private final TextView assists;
    private final TextView fouls;

    private final PlayerSummary player = new PlayerSummary();

    public PlayerViewCompact(Context context, AttributeSet attributes) {
        super(context, attributes);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_player_compact, this, true);
        checkBox = (CheckBox) findViewById(R.id.checkbox);
        name = (EditText) findViewById(R.id.name);
        chrono = (Chronometer) findViewById(R.id.chrono);
        points = (TextView) findViewById(R.id.pointsTextView);
        rebounds = (TextView) findViewById(R.id.reboundsTextView);
        assists = (TextView) findViewById(R.id.assistsTextView);
        fouls = (TextView) findViewById(R.id.foulsTextView);
        Button rebound = (Button) findViewById(R.id.reboundButton);
        Button assist = (Button) findViewById(R.id.assistButton);
        Button foul = (Button) findViewById(R.id.foulButton);

        rebound.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                rebounds.setText(String.valueOf(++player.reb));
                showToast();
            }
        });

        assist.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                assists.setText(String.valueOf(++player.ast));
                showToast();
            }
        });

        foul.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                fouls.setText(String.valueOf(++player.pf));
                showToast();
            }
        });

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
        a.recycle();
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
        this.chrono.setText(secondsToString(data.secondsPlayed));
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
        this.player.tap = data.tap;
        this.player.rob = data.rob;
        this.player.per = data.per;
        this.player.key = data.key;
        this.points.setText(String.valueOf(
                this.player.ftm + 2 * this.player.fgm + 3 * this.player.tpm));
        this.rebounds.setText(String.valueOf(this.player.reb));
        this.assists.setText(String.valueOf(this.player.ast));
        this.fouls.setText(String.valueOf(this.player.pf));
        return this;
    }

    private String secondsToString(long secondsPlayed) {
        StringBuilder builder = new StringBuilder();
        int minutes = (int) secondsPlayed / 60;
        int seconds = (int) secondsPlayed % 60;
        if (minutes < 10) builder.append("0");
        builder.append(minutes);
        builder.append(":");
        if (seconds < 10) builder.append("0");
        builder.append(seconds);
        return builder.toString();
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
}
