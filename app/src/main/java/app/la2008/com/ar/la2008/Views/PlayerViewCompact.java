package app.la2008.com.ar.la2008.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import app.la2008.com.ar.la2008.Models.PlayerSummary;
import app.la2008.com.ar.la2008.R;

/**
 * Created by gdconde on 31/3/17.
 */

public class PlayerViewCompact extends LinearLayout {

    private CheckBox checkBox;
    private EditText name;
    private Chronometer chrono;
    private Button onePoint;
    private Button twoPoints;
    private Button threePoints;
    private Button rebound;
    private Button assist;
    private Button foul;

    private PlayerSummary player = new PlayerSummary();

    public PlayerViewCompact(final Context context, AttributeSet attributes) {
        super(context, attributes);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_player_compact, this, true);
        checkBox = (CheckBox) findViewById(R.id.checkbox);
        name = (EditText) findViewById(R.id.name);
        chrono = (Chronometer) findViewById(R.id.chrono);
        onePoint = (Button) findViewById(R.id.simpleButton);
        twoPoints = (Button) findViewById(R.id.twoPointsButton);
        threePoints = (Button) findViewById(R.id.threePointsButton);
        rebound = (Button) findViewById(R.id.reboundButton);
        assist = (Button) findViewById(R.id.assistButton);
        foul = (Button) findViewById(R.id.foulButton);

        onePoint.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                player.ftm++;
                Toast.makeText(context, "Libre", Toast.LENGTH_SHORT).show();
            }
        });

        twoPoints.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                player.fgm++;
                Toast.makeText(context, "2 puntos", Toast.LENGTH_SHORT).show();
            }
        });

        threePoints.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                player.tpm++;
                Toast.makeText(context, "3 puntos", Toast.LENGTH_SHORT).show();
            }
        });

        rebound.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                player.reb++;
                Toast.makeText(context, "Rebote", Toast.LENGTH_SHORT).show();
            }
        });

        assist.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                player.ast++;
                Toast.makeText(context, "Asistencia", Toast.LENGTH_SHORT).show();
            }
        });

        foul.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                player.pf++;
                Toast.makeText(context, "Falta", Toast.LENGTH_SHORT).show();
            }
        });

        this.setButtonsClickable(false);

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
    }

    public Boolean isChecked() {
        return checkBox.isChecked();
    }

    public PlayerViewCompact setChecked(boolean check) {
        checkBox.setChecked(check);
        return this;
    }

    public PlayerViewCompact setButtonsClickable(boolean clickable) {
        this.onePoint.setClickable(clickable);
        this.twoPoints.setClickable(clickable);
        this.threePoints.setClickable(clickable);
        this.rebound.setClickable(clickable);
        this.assist.setClickable(clickable);
        this.foul.setClickable(clickable);
        return this;
    }

    public PlayerSummary getPlayerSummary() {
        return player;
    }

    public PlayerViewCompact setData(PlayerSummary data) {
        this.name.setText(data.name);
        this.chrono.setText(secondsToString(data.secondsPlayed));
        this.chrono.setTag(data.tag);
        this.player.tag = data.tag;
        this.player.ftm = data.ftm;
        this.player.fgm = data.fgm;
        this.player.tpm = data.tpm;
        this.player.ast = data.ast;
        this.player.reb = data.reb;
        this.player.pf = data.pf;
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
}
