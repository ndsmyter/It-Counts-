package be.ndsmyter.countexperiment;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.util.Log;

public class FragmentPreferencesActivity extends AppCompatPreferenceActivity
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = "CE";

    public static final String KEY_COUNTER_NAME = "counterName";

    public static final String KEY_SCREEN_TOUCH = "screenTouch";

    public static final String KEY_VOLUME_UP = "volumeUp";

    public static final String KEY_VOLUME_DOWN = "volumeDown";

    private FragmentModel fragmentModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            fragmentModel = (FragmentModel) extras.getSerializable("model");
            if (fragmentModel == null) {
                Log.i(TAG, "Model is null");
                return;
            }

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            prefs.registerOnSharedPreferenceChangeListener(this);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(KEY_COUNTER_NAME, fragmentModel.getTitle());
            editor.putString(KEY_SCREEN_TOUCH, "" + fragmentModel.getTouchedPoints());
            editor.putString(KEY_VOLUME_UP, "" + fragmentModel.getVolumeUpPoints());
            editor.putString(KEY_VOLUME_DOWN, "" + fragmentModel.getVolumeDownPoints());
            editor.apply();
        }

        getFragmentManager().beginTransaction().replace(android.R.id.content, new GeneralPreferenceFragment()).commit();
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * This method stops fragment injection in malicious applications. Make sure to deny any unknown fragments here.
     */
    protected boolean isValidFragment(String fragmentName) {
        return PreferenceFragment.class.getName().equals(fragmentName)
                || GeneralPreferenceFragment.class.getName().equals(fragmentName);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        String id = "-" + fragmentModel.getUniqueId();
        switch (key) {
            case KEY_COUNTER_NAME:
                editor.putString(key + id, prefs.getString(KEY_COUNTER_NAME, fragmentModel.getTitle()));
                break;
            case KEY_SCREEN_TOUCH:
                editor.putString(key + id, prefs.getString(key, "" + fragmentModel.getTouchedPoints()));
                break;
            case KEY_VOLUME_DOWN:
                editor.putString(key + id, prefs.getString(key, "" + fragmentModel.getVolumeDownPoints()));
                break;
            case KEY_VOLUME_UP:
                editor.putString(key + id, prefs.getString(key, "" + fragmentModel.getVolumeUpPoints()));
                break;
            default:
                break;
        }
        editor.apply();
    }

    /**
     * This fragment shows general preferences only. It is used when the activity is showing a two-pane settings UI.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class GeneralPreferenceFragment extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.fragment_preferences);
        }
    }
}
