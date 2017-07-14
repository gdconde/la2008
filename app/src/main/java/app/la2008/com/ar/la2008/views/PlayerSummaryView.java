package app.la2008.com.ar.la2008.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.la2008.com.ar.la2008.models.PlayerSummary;
import app.la2008.com.ar.la2008.R;

/**
 * Created by gdconde on 3/4/17.
 */

public class PlayerSummaryView extends LinearLayout {

    private final TextView nameTextView;
    private final TextView timeTextView;
    private final TextView pointsTextView;
    private final TextView reboundsTextView;
    private final TextView assistsTextView;
    private final TextView foulsTextView;
    private final TextView triplesTextView;

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
//        this.timeTextView.setText(String.valueOf(summary.timePlayed));
        this.pointsTextView.setText(String.valueOf(summary.pts));
        this.reboundsTextView.setText(String.valueOf(summary.reb));
        this.assistsTextView.setText(String.valueOf(summary.ast));
        this.triplesTextView.setText(String.valueOf(summary.tpm));
        this.foulsTextView.setText(String.valueOf(summary.pf));
        return this;
    }
}
