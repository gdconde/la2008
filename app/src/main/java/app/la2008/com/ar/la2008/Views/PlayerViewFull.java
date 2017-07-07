package app.la2008.com.ar.la2008.Views;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.la2008.com.ar.la2008.Models.PlayerSummary;
import app.la2008.com.ar.la2008.R;

/**
 * Created by gdconde on 7/3/17.
 */

public class PlayerViewFull extends LinearLayout {

    private TextView nameTV;
    private Chronometer timeChronometer;
    private Button rebButton;
    private Button astButton;
    private Button tapButton;
    private Button robButton;
    private Button perButton;
    private Button falButton;
    private Button ftmButton;
    private Button ftaButton;
    private Button fgmButton;
    private Button fgaButton;
    private Button tpmButton;
    private Button tpaButton;
    private PlayerSummary playerSummary;

    public PlayerViewFull(Context context, AttributeSet attributes) {
        super(context, attributes);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_player_full, this, true);

        this.nameTV = (TextView) findViewById(R.id.name);
        this.timeChronometer = (Chronometer) findViewById(R.id.chrono);
        this.rebButton = (Button) findViewById(R.id.reb);
        this.astButton = (Button) findViewById(R.id.ast);
        this.tapButton = (Button) findViewById(R.id.tap);
        this.robButton = (Button) findViewById(R.id.rob);
        this.perButton = (Button) findViewById(R.id.per);
        this.falButton = (Button) findViewById(R.id.fal);
        this.ftmButton = (Button) findViewById(R.id.ftm);
        this.ftaButton = (Button) findViewById(R.id.fta);
        this.fgmButton = (Button) findViewById(R.id.fgm);
        this.fgaButton = (Button) findViewById(R.id.fga);
        this.tpmButton = (Button) findViewById(R.id.tpm);
        this.tpaButton = (Button) findViewById(R.id.tpa);

        rebButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                playerSummary.reb++;
            }
        });

        astButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                playerSummary.ast++;
            }
        });
    }

    public PlayerViewFull startChrono() {
        timeChronometer.setBase(SystemClock.elapsedRealtime() + Integer.valueOf((String)timeChronometer.getTag()));
        timeChronometer.start();
        return this;
    }

    public PlayerViewFull stopChrono() {
        timeChronometer.
                setTag(String.valueOf(timeChronometer.getBase() - SystemClock.elapsedRealtime()));
        timeChronometer.stop();
        this.playerSummary.tag =
                String.valueOf(timeChronometer.getBase() - SystemClock.elapsedRealtime());
        this.playerSummary.secondsPlayed =
                (SystemClock.elapsedRealtime() - timeChronometer.getBase()) / 1000;
        return this;
    }

    public PlayerViewFull setData(PlayerSummary data) {
        this.playerSummary = data;
        this.nameTV.setText(this.playerSummary.name);
        this.timeChronometer.setTag(data.tag);
        return this;
    }

    public PlayerSummary getPlayerSummary() {
        return playerSummary;
    }
}
