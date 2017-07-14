package app.la2008.com.ar.la2008.activities;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.os.Bundle;

import app.la2008.com.ar.la2008.R;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.newGameButton)
    public void newGame() {
        NewGameActivity.start(this);
    }
}
