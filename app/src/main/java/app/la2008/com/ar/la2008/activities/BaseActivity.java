package app.la2008.com.ar.la2008.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import app.la2008.com.ar.la2008.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaseActivity extends AppCompatActivity {

    @Nullable @BindView(R.id.toolbar) Toolbar toolbar;
    @Nullable @BindView(R.id.barTitle) TextView barTitle;
    @Nullable @BindView(R.id.barSubTitle) TextView barSubTitle;
    @Nullable @BindView(R.id.backBtt) View backBtt;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ButterKnife.bind(this);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setContentInsetsAbsolute(0, 0);
        }
    }

    @OnClick(R.id.backBtt)
    public void back() {
        onBackPressed();
    }
}
