package app.la2008.com.ar.la2008.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.la2008.com.ar.la2008.Models.PlayerSummary;
import app.la2008.com.ar.la2008.R;

/**
 * Created by gdconde on 3/4/17.
 */

public class PlayerSummaryView extends LinearLayout {

    private TextView nameTextView;
    private TextView timeTextView;
    private TextView pointsTextView;
    private TextView reboundsTextView;
    private TextView assistsTextView;
    private TextView foulsTextView;
    private TextView triplesTextView;

    public PlayerSummaryView(final Context context, AttributeSet attributes) {
        super(context, attributes);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_player_summary, this, true);
        nameTextView = (TextView) findViewById(R.id.name);
        timeTextView = (TextView) findViewById(R.id.timePlayed);
        pointsTextView = (TextView) findViewById(R.id.points);
        reboundsTextView = (TextView) findViewById(R.id.rebounds);
        assistsTextView = (TextView) findViewById(R.id.assists);
        triplesTextView= (TextView) findViewById(R.id.triples);
        foulsTextView = (TextView) findViewById(R.id.fouls);
    }

    public PlayerSummaryView setSummary(PlayerSummary summary) {
        this.nameTextView.setText(summary.name);
        this.timeTextView.setText(String.valueOf(summary.timePlayed));
        this.pointsTextView.setText(String.valueOf(summary.totalPoints));
        this.reboundsTextView.setText(String.valueOf(summary.rebounds));
        this.assistsTextView.setText(String.valueOf(summary.assists));
        this.triplesTextView.setText(String.valueOf(summary.threePointsConversions));
        this.foulsTextView.setText(String.valueOf(summary.fouls));
        return this;
    }
}
