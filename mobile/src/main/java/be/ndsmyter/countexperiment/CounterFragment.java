package be.ndsmyter.countexperiment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import be.ndsmyter.countexperiment.common.Listener;
import be.ndsmyter.countexperiment.preferences.FragmentPreferencesActivity;
import be.ndsmyter.countexperiment.visuals.TallyVisual;

/**
 * A placeholder fragment containing a simple view.
 */
public class CounterFragment extends Fragment implements View.OnClickListener, Listener {

    private static final String TAG = "CE";

    /**
     * The fragment argument representing the section number for this fragment.
     */
    private static final String ARG_FRAGMENT_MODEL = "fragment_model";

    private View rootView;

    private FragmentModel fragmentModel;

    /**
     * Returns a new instance of this fragment for the given section number.
     */
    public static CounterFragment newInstance(FragmentModel fragmentModel) {
        CounterFragment fragment = new CounterFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_FRAGMENT_MODEL, fragmentModel);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Create a new SectionFragment. Default constructor without any arguments because Fragments should be initialized
     * like this. The methods setArguments and getArguments should be used instead to pass arguments.
     */
    public CounterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.fragmentModel = (FragmentModel) this.getArguments().getSerializable(ARG_FRAGMENT_MODEL);
        if (this.fragmentModel == null) {
            Log.e(TAG, "We're missing the model for this view");
            return null;
        }
        this.fragmentModel.addListener(this);
        this.rootView = inflater.inflate(R.layout.fragment_main, container, false);
        rootView.setOnClickListener(this);
        updateTitle();
        updateCount();

        rootView.findViewById(R.id.settings_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSettings();
            }
        });

        // Add visualization
        TallyVisual fragment = new TallyVisual();
        fragment.setModel(fragmentModel);
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.visual_container, fragment)
                .commit();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        readPreferences();
        updateCount();
        updateTitle();
    }

    private void readPreferences() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String id = "-" + fragmentModel.getUniqueId();
        fragmentModel.setTitle(
                prefs.getString(FragmentPreferencesActivity.KEY_COUNTER_NAME + id, fragmentModel.getTitle()));
        fragmentModel.setTouchedPoints(Integer.parseInt(
                prefs.getString(FragmentPreferencesActivity.KEY_SCREEN_TOUCH + id,
                                "" + fragmentModel.getTouchedPoints())));
        fragmentModel.setVolumeUpPoints(Integer.parseInt(
                prefs.getString(FragmentPreferencesActivity.KEY_VOLUME_UP + id,
                                "" + fragmentModel.getVolumeUpPoints())));
        fragmentModel.setVolumeDownPoints(Integer.parseInt(
                prefs.getString(FragmentPreferencesActivity.KEY_VOLUME_DOWN + id,
                                "" + fragmentModel.getVolumeDownPoints())));
    }

    /**
     * Set the text of the TextView with the given id.
     *
     * @param id   the ID of the TextView we're looking for.
     * @param text the text that should be placed on the TextView.
     */
    private void setText(int id, String text) {
        if (this.rootView == null) {
            return;
        }
        ((TextView) this.rootView.findViewById(id)).setText(text);
    }

    @Override
    public void onClick(View view) {
        this.fragmentModel.addTouched();
    }

    /**
     * Handle the key events.
     *
     * @param keyCode the key code of the key event.
     * @return true if the event should be propagated upwards (and handled by other classes), false otherwise.
     */
    public boolean onKeyDown(int keyCode) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            this.fragmentModel.addVolumeDown();
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            this.fragmentModel.addVolumeUp();
        }
        return false;
    }

    /**
     * Get the current title of this fragment.
     *
     * @return the title of the fragment
     */
    public String getTitle() {
        return fragmentModel.getTitle();
    }

    /**
     * Update the count on the screen.
     */
    private void updateCount() {
        setText(R.id.count_label, "" + this.fragmentModel.getCount());
    }

    /**
     * Set the new title of this fragment.
     */
    public void updateTitle() {
        setText(R.id.title_label, fragmentModel.getTitle());
    }

    @Override
    public void notifyChanged() {
        updateTitle();
        updateCount();
    }

    /**
     * Open the settings for this counter.
     */
    public void openSettings() {
        Intent intent = new Intent(getActivity(), FragmentPreferencesActivity.class);
        intent.putExtra("model", fragmentModel);
        startActivity(intent);
    }
}
