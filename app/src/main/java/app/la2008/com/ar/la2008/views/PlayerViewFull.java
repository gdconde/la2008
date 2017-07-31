package app.la2008.com.ar.la2008.views;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import app.la2008.com.ar.la2008.R;
import app.la2008.com.ar.la2008.models.PlayerSummary;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlayerViewFull extends LinearLayout {

    @BindView(R.id.name) TextView nameTV;
    @BindView(R.id.chrono) Chronometer timeChronometer;
    private PlayerSummary playerSummary;

    public PlayerViewFull(Context context, AttributeSet attributes) {
        super(context, attributes);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_player_full, this, true);

        ButterKnife.bind(this);
    }

    public PlayerViewFull startChrono() {
        timeChronometer.setBase(SystemClock.elapsedRealtime() +
                Integer.valueOf((String) timeChronometer.getTag()));
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

    @OnClick({R.id.reb, R.id.ast, R.id.rob, R.id.tap, R.id.per, R.id.fal, R.id.fgm, R.id.fga,
            R.id.tpm, R.id.tpa, R.id.ftm, R.id.fta})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reb:
                playerSummary.reb++;
                break;
            case R.id.ast:
                playerSummary.ast++;
                break;
            case R.id.rob:
                playerSummary.stl++;
                break;
            case R.id.tap:
                playerSummary.blk++;
                break;
            case R.id.per:
                playerSummary.tov++;
                break;
            case R.id.fal:
                playerSummary.pf++;
                break;
            case R.id.fgm:
                playerSummary.pts += 2;
                playerSummary.fgm++;
                playerSummary.fga++;
                showToast();
                break;
            case R.id.fga:
                playerSummary.fga++;
                showToast();
                break;
            case R.id.tpm:
                playerSummary.pts += 3;
                playerSummary.fgm++;
                playerSummary.fga++;
                playerSummary.tpm++;
                playerSummary.tpa++;
                showToast();
                break;
            case R.id.tpa:
                playerSummary.fga++;
                playerSummary.tpa++;
                showToast();
                break;
            case R.id.ftm:
                playerSummary.pts += 1;
                playerSummary.ftm++;
                playerSummary.fta++;
                showToast();
                break;
            case R.id.fta:
                playerSummary.fta++;
                showToast();
                break;
        }
    }
}
