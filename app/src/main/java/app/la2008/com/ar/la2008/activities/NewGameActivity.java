package app.la2008.com.ar.la2008.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

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
            R.id.player12EditText})
    List<EditText> playerNamesEditTexts;
    @BindView(R.id.gameName) EditText gameNameEditText;

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
            playerNames.add(editText.getText().toString());
        }
        String gameName = this.gameNameEditText.getText().toString();
        InGameActivity.start(this, playerNames, gameName);
        finish();
    }
}
