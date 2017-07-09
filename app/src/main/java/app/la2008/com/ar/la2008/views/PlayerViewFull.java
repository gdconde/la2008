package app.la2008.com.ar.la2008.views;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import app.la2008.com.ar.la2008.models.PlayerSummary;
import app.la2008.com.ar.la2008.R;

/**
 * Created by gdconde on 7/3/17.
 */

public class PlayerViewFull extends LinearLayout {

    private TextView nameTV;
    private Chronometer timeChronometer;
    private PlayerSummary playerSummary;

    public PlayerViewFull(Context context, AttributeSet attributes) {
        super(context, attributes);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_player_full, this, true);

        this.nameTV = (TextView) findViewById(R.id.name);
        this.timeChronometer = (Chronometer) findViewById(R.id.chrono);
        Button rebButton = (Button) findViewById(R.id.reb);
        Button astButton = (Button) findViewById(R.id.ast);
        Button tapButton = (Button) findViewById(R.id.tap);
        Button robButton = (Button) findViewById(R.id.rob);
        Button perButton = (Button) findViewById(R.id.per);
        Button falButton = (Button) findViewById(R.id.fal);
        Button ftmButton = (Button) findViewById(R.id.ftm);
        Button ftaButton = (Button) findViewById(R.id.fta);
        Button fgmButton = (Button) findViewById(R.id.fgm);
        Button fgaButton = (Button) findViewById(R.id.fga);
        Button tpmButton = (Button) findViewById(R.id.tpm);
        Button tpaButton = (Button) findViewById(R.id.tpa);

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
        tapButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                playerSummary.tap++;
            }
        });
        robButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                playerSummary.rob++;
            }
        });
        perButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                playerSummary.per++;
            }
        });
        falButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                playerSummary.pf++;
            }
        });
        ftmButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                playerSummary.ftm++;
                playerSummary.fta++;
                showToast();
            }
        });
        ftaButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                playerSummary.fta++;
                showToast();
            }
        });
        fgmButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                playerSummary.fgm++;
                playerSummary.fga++;
                showToast();
            }
        });
        fgaButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                playerSummary.fga++;
                showToast();
            }
        });
        tpmButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                playerSummary.tpm++;
                playerSummary.tpa++;
                showToast();
            }
        });
        tpaButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                playerSummary.tpa++;
                showToast();
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
