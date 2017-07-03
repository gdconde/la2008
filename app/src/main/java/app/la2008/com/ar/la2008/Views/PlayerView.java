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

public class PlayerView extends LinearLayout {

    private CheckBox checkBox;
    private EditText name;
    private Chronometer chrono;
    private Button onePoint;
    private Button twoPoints;
    private Button threePoints;
    private Button rebound;
    private Button assist;
    private Button foul;
    private int simples = 0;
    private int doubles = 0;
    private int triples = 0;
    private int rebounds = 0;
    private int assists = 0;
    private int fouls = 0;

    public PlayerView(final Context context, AttributeSet attributes) {
        super(context, attributes);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_player, this, true);
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
                onePoint();
                Toast.makeText(context, "Libre", Toast.LENGTH_SHORT).show();
            }
        });

        twoPoints.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                twoPoints();
                Toast.makeText(context, "2 puntos", Toast.LENGTH_SHORT).show();
            }
        });

        threePoints.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                threePoints();
                Toast.makeText(context, "3 puntos", Toast.LENGTH_SHORT).show();
            }
        });

        rebound.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                rebound();
                Toast.makeText(context, "Rebote", Toast.LENGTH_SHORT).show();
            }
        });

        assist.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                assist();
                Toast.makeText(context, "Asistencia", Toast.LENGTH_SHORT).show();
            }
        });

        foul.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                foul();
                Toast.makeText(context, "Falta", Toast.LENGTH_SHORT).show();
            }
        });

        this.setButtonsClickable(false);

        TypedArray a = context.obtainStyledAttributes(attributes, R.styleable.PlayerView);

        String playerName = a.getString(R.styleable.PlayerView_name);
        if(playerName != null) name.setText(playerName);
    }

    public PlayerView startChrono() {
        chrono.setBase(SystemClock.elapsedRealtime() + Integer.valueOf((String)chrono.getTag()));
        chrono.start();
        return this;
    }

    public PlayerView stopChrono() {
        chrono.setTag(String.valueOf(chrono.getBase() - SystemClock.elapsedRealtime()));
        chrono.stop();
        return this;
    }

    public Boolean isChecked() {
        return checkBox.isChecked();
    }

    public PlayerView setChecked(boolean check) {
        checkBox.setChecked(check);
        return this;
    }

    public PlayerView onePoint() {
        this.simples++;
        return this;
    }

    public PlayerView twoPoints() {
        this.doubles++;
        return this;
    }

    public PlayerView threePoints() {
        this.triples++;
        return this;
    }

    public PlayerView rebound() {
        this.rebounds++;
        return this;
    }

    public PlayerView assist() {
        this.assists++;
        return this;
    }

    public PlayerView foul() {
        this.fouls++;
        return this;
    }

    public PlayerView setButtonsClickable(boolean clickable) {
        this.onePoint.setClickable(clickable);
        this.twoPoints.setClickable(clickable);
        this.threePoints.setClickable(clickable);
        this.rebound.setClickable(clickable);
        this.assist.setClickable(clickable);
        this.foul.setClickable(clickable);
        return this;
    }

    public PlayerSummary getPlayerSummary() {
        PlayerSummary summary = new PlayerSummary();
        summary.name = this.name.getText().toString();
        summary.timePlayed = this.chrono.getText().toString();
        summary.simplesConversions = this.simples;
        summary.twoPointsConversions = this.doubles;
        summary.threePointsConversions = this.triples;
        summary.rebounds = this.rebounds;
        summary.assists = this.assists;
        summary.fouls = this.fouls;
        summary.totalPoints = this.simples + this.doubles * 2 + this.triples * 3;
        return summary;
    }

    public PlayerView setData(PlayerSummary data) {
        this.name.setText(data.name);
        this.chrono.setBase(data.base);
        this.chrono.setText(data.timePlayed);
        this.simples = data.simplesConversions;
        this.doubles = data.twoPointsConversions;
        this.triples = data.threePointsConversions;
        this.assists = data.assists;
        this.rebounds = data.rebounds;
        this.fouls = data.fouls;
        return this;
    }
}
