package be.ndsmyter.countexperiment.preferences;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;

import be.ndsmyter.countexperiment.FragmentModel;
import be.ndsmyter.countexperiment.R;
import be.ndsmyter.countexperiment.common.Util;
import be.ndsmyter.countexperiment.visuals.common.VisualManager;

import java.util.List;

public class FragmentPreferencesActivity extends AppCompatPreferenceActivity
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = "CE";

    public static final String KEY_COUNTER_NAME = "counterName";

    public static final String KEY_SCREEN_TOUCH = "pref_touch_value";
    public static final String KEY_SCREEN_TOUCH_ACTION = "pref_touch_action";
    public static final String KEY_SCREEN_TOUCH_USE = "pref_use_touch";

    public static final String KEY_VOLUME_UP = "pref_volume_up_value";
    public static final String KEY_VOLUME_UP_ACTION = "pref_volume_up_action";
    public static final String KEY_VOLUME_UP_USE = "pref_use_volume_up";

    public static final String KEY_VOLUME_DOWN = "pref_volume_down_value";
    public static final String KEY_VOLUME_DOWN_ACTION = "pref_volume_down_action";
    public static final String KEY_VOLUME_DOWN_USE = "pref_use_volume_down";

    public static final String KEY_VISUALIZATION = "visualization";
    public static final String KEY_COLOR = "color";

    private static final String[] KEYS =
            new String[]{KEY_COUNTER_NAME,
                    KEY_SCREEN_TOUCH, KEY_VOLUME_UP, KEY_VOLUME_DOWN,
                    KEY_SCREEN_TOUCH_ACTION, KEY_VOLUME_UP_ACTION, KEY_VOLUME_DOWN_ACTION,
                    KEY_VISUALIZATION, KEY_COLOR};

    private FragmentModel fragmentModel;

    private GeneralPreferenceFragment preferenceFragment;

    @Override
    @SuppressLint("CommitPrefEdits")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();

        String title; //= "";//, touched = "";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            fragmentModel = (FragmentModel) extras.getSerializable("model");
            if (fragmentModel == null) {
                Log.i(TAG, "Model is null");
                return;
            }
            title = fragmentModel.getTitle();
            //touched = "" + fragmentModel.getTouchedPoints();

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            prefs.registerOnSharedPreferenceChangeListener(this);
            SharedPreferences.Editor editor = prefs.edit();

            // Update preferences
            editor.putString(KEY_COUNTER_NAME, title);

            editor.putString(KEY_SCREEN_TOUCH, "" + fragmentModel.getTouchedPoints());
            editor.putBoolean(KEY_SCREEN_TOUCH_USE, fragmentModel.getUseTouch());
            editor.putString(KEY_VOLUME_UP, "" + fragmentModel.getVolumeUpPoints());
            editor.putBoolean(KEY_SCREEN_TOUCH_USE, fragmentModel.getUseVolumeUp());
            editor.putString(KEY_VOLUME_DOWN, "" + fragmentModel.getVolumeDownPoints());
            editor.putBoolean(KEY_SCREEN_TOUCH_USE, fragmentModel.getUseTouch());

            editor.putString(KEY_VISUALIZATION, "" + fragmentModel.getVisualizationIndex());
            editor.putString(KEY_COLOR, fragmentModel.getColor());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                editor.apply();
            } else {
                editor.commit();
            }
        }
        preferenceFragment = new GeneralPreferenceFragment();
        getFragmentManager().beginTransaction().replace(android.R.id.content, preferenceFragment).commit();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * This method stops fragment injection in malicious applications. Make sure to deny any unknown fragments here.
     */
    protected boolean isValidFragment(String fragmentName) {
        return PreferenceFragment.class.getName().equals(fragmentName)
                || GeneralPreferenceFragment.class.getName().equals(fragmentName);
    }

    @Override
    @SuppressLint("CommitPrefEdits")
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        String id = key + "-" + fragmentModel.getUniqueId();
        String value;
        switch (key) {
            case KEY_COUNTER_NAME:
                value = prefs.getString(key, fragmentModel.getTitle());
                editor.putString(id, value);
                updateSummary(key, R.string.pref_desc_name, value);
                break;
            case KEY_SCREEN_TOUCH:
                value = prefs.getString(key, "" + fragmentModel.getTouchedPoints());
                editor.putString(id, value);
                updateSummary(key, R.string.pref_desc_touch, value);
                break;
            case KEY_VOLUME_DOWN:
                value = prefs.getString(key, "" + fragmentModel.getVolumeDownPoints());
                editor.putString(id, value);
                updateSummary(key, R.string.pref_desc_volume_down, value);
                break;
            case KEY_VOLUME_UP:
                value = prefs.getString(key, "" + fragmentModel.getVolumeUpPoints());
                editor.putString(id, value);
                updateSummary(key, R.string.pref_desc_volume_up, value);
                break;
            case KEY_SCREEN_TOUCH_USE:
                editor.putBoolean(id, prefs.getBoolean(key, fragmentModel.getUseTouch()));
                break;
            case KEY_VOLUME_DOWN_USE:
                editor.putBoolean(id, prefs.getBoolean(key, fragmentModel.getUseVolumeDown()));
                break;
            case KEY_VOLUME_UP_USE:
                editor.putBoolean(id, prefs.getBoolean(key, fragmentModel.getUseVolumeUp()));
                break;
            case KEY_SCREEN_TOUCH_ACTION:
                value = prefs.getString(key, "" + fragmentModel.getTouchAction());
                editor.putString(id, value);
                updateSummary(key, R.string.pref_desc_action_touch, value);
                break;
            case KEY_VOLUME_DOWN_ACTION:
                value = prefs.getString(key, "" + fragmentModel.getVolumeDownAction());
                editor.putString(id, value);
                updateSummary(key, R.string.pref_desc_action_volume_down, value);
                break;
            case KEY_VOLUME_UP_ACTION:
                value = prefs.getString(key, "" + fragmentModel.getVolumeUpAction());
                editor.putString(id, value);
                updateSummary(key, R.string.pref_desc_action_volume_up, value);
                break;
            case KEY_VISUALIZATION:
                value = prefs.getString(key, "" + fragmentModel.getVisualizationIndex());
                editor.putString(id, value);
                updateSummary(key, R.string.pref_desc_visualization, VisualManager.getVisualElement(value).getName());
            case KEY_COLOR:
                value = prefs.getString(key, "" + fragmentModel.getColor());
                editor.putString(id, value);
                updateSummary(key, R.string.pref_desc_color, value);
            default:
                Log.w("CE", "Key " + key + " not handled in fragment preferences activity");
                break;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    /**
     * Update summary of the setting with the given key. The text identified by the stringId will be loaded and the
     * value will be appended.
     *
     * @param key      the key of the setting on the screen.
     * @param stringId the ID of the string from the resources.
     * @param value    the value that will be appended to the summary.
     */
    private void updateSummary(String key, int stringId, String value) {
        if (!value.isEmpty() && preferenceFragment != null) {
            Preference preference = preferenceFragment.findPreference(key);
            if (preference != null) {
                preference.setSummary(getResources().getString(stringId) + ": " + value);
            }
        }
    }

    /**
     * Show the general preferences of this application.
     */
    public static class GeneralPreferenceFragment extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.fragment_preferences);

            addVisualizations();
            addColors();
            initSummary();
        }

        /**
         * Add the list of visualization to the preference screen.
         */
        private void addVisualizations() {
            List<VisualManager.VisualElement> visualizations = VisualManager.getVisualizations();
            int length = visualizations.size();
            String[] entries = new String[length];
            String[] entryValues = new String[length];
            for (int i = 0; i < length; i++) {
                VisualManager.VisualElement visualization = visualizations.get(i);
                entries[i] = visualization.getName();
                entryValues[i] = "" + i;
            }

            ListPreference preference = (ListPreference) findPreference(KEY_VISUALIZATION);
            preference.setEntries(entries);
            preference.setEntryValues(entryValues);
        }

        private void addColors() {
            String[] entries = Util.getColorSchemeOptions();
            String[] entryValues = Util.getColorSchemeOptions();
            ListPreference preference = (ListPreference) findPreference(KEY_COLOR);
            preference.setEntries(entries);
            preference.setEntryValues(entryValues);
        }
        /**
         * Initialize the summary for the preferences.
         */
        private void initSummary() {
            for (String key : KEYS) {
                updateSummary(key);
            }
        }

        /**
         * Update the summary for the preference identified by the given key.
         *
         * @param key the key of the preference that needs an updated summary.
         */
        private void updateSummary(String key) {
            Preference preference = findPreference(key);
            if (preference != null) {
                String value = "";
                if (preference instanceof EditTextPreference) {
                    value = ((EditTextPreference) preference).getText();
                } else if (preference instanceof ListPreference) {
                    value = (String) ((ListPreference) preference).getEntry();
                }

                preference.setSummary(preference.getSummary() + ": " + value);
            }
        }
    }
}
