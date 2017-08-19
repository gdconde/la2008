package app.la2008.com.ar.la2008.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.la2008.com.ar.la2008.R;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewGameActivity extends BaseActivity {

    @BindViews({R.id.player1EditText,
            R.id.player2EditText,
            R.id.player3EditText,
            R.id.player4EditText,
            R.id.player5EditText,
            R.id.player6EditText,
            R.id.player7EditText,
            R.id.player8EditText,
            R.id.player9EditText,
            R.id.player10EditText,
            R.id.player11EditText,
            R.id.player12EditText,
            R.id.player13EditText,
            R.id.player14EditText})
    List<EditText> playerNamesEditTexts;
    @BindView(R.id.gameName) EditText gameNameEditText;
    @BindView(R.id.addMorePlayersButton) Button addMorePlayersButton;
    @BindView(R.id.players13and14LinearLayout) LinearLayout players13and14LL;

    public static void start(Context context) {
        Intent starter = new Intent(context, NewGameActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ButterKnife.bind(this);
        if (barTitle != null) {
            barTitle.setText(R.string.app_name);
        }
    }

    @OnClick(R.id.startGameButton)
    public void startGame() {
        ArrayList<String> playerNames = new ArrayList<>();
        for (EditText editText : this.playerNamesEditTexts) {
            if (editText.getVisibility() != View.VISIBLE ||
                    editText.getText().toString().isEmpty()) continue;
            playerNames.add(editText.getText().toString());
        }
        if (playerNames.size() < 7) {
            Toast.makeText(this, "Must enter name for at least 7 players", Toast.LENGTH_LONG)
                    .show();
            return;
        }
        String gameName = this.gameNameEditText.getText().toString();
        InGameActivity.start(this, playerNames, gameName);
        finish();
    }

    @OnClick(R.id.addMorePlayersButton)
    public void addMorePlayers() {
        players13and14LL.setVisibility(View.VISIBLE);
        addMorePlayersButton.setVisibility(View.GONE);
    }
}
