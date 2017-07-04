package app.la2008.com.ar.la2008.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

import app.la2008.com.ar.la2008.Models.PlayerSummary;
import app.la2008.com.ar.la2008.Views.PlayerSummaryView;
import app.la2008.com.ar.la2008.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gdconde on 31/3/17.
 */

public class SummaryActivity extends AppCompatActivity {

    @BindView(R.id.player1) PlayerSummaryView player1;
    @BindView(R.id.player2) PlayerSummaryView player2;
    @BindView(R.id.player3) PlayerSummaryView player3;
    @BindView(R.id.player4) PlayerSummaryView player4;
    @BindView(R.id.player5) PlayerSummaryView player5;
    @BindView(R.id.player6) PlayerSummaryView player6;
    @BindView(R.id.player7) PlayerSummaryView player7;
    @BindView(R.id.player8) PlayerSummaryView player8;
    @BindView(R.id.player9) PlayerSummaryView player9;
    @BindView(R.id.player10) PlayerSummaryView player10;
    @BindView(R.id.player11) PlayerSummaryView player11;
    @BindView(R.id.player12) PlayerSummaryView player12;
    public static ArrayList<PlayerSummary> playersSummaries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_summary);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ButterKnife.bind(this);
        if (playersSummaries != null && playersSummaries.size() > 0) {
            for(int i = 0; i < playersSummaries.size(); i++) {
                switch (i) {
                    case 0: player1.setSummary(playersSummaries.get(i)); break;
                    case 1: player2.setSummary(playersSummaries.get(i)); break;
                    case 2: player3.setSummary(playersSummaries.get(i)); break;
                    case 3: player4.setSummary(playersSummaries.get(i)); break;
                    case 4: player5.setSummary(playersSummaries.get(i)); break;
                    case 5: player6.setSummary(playersSummaries.get(i)); break;
                    case 6: player7.setSummary(playersSummaries.get(i)); break;
                    case 7: player8.setSummary(playersSummaries.get(i)); break;
                    case 8: player9.setSummary(playersSummaries.get(i)); break;
                    case 9: player10.setSummary(playersSummaries.get(i)); break;
                    case 10: player11.setSummary(playersSummaries.get(i)); break;
                    case 11: player12.setSummary(playersSummaries.get(i)); break;
                    default: break;
                }
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        super.onSupportNavigateUp();
        return true;
    }

}
