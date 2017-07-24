package app.la2008.com.ar.la2008.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import app.la2008.com.ar.la2008.R;

public class SettingsFragment extends PreferenceFragment {

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }

}
