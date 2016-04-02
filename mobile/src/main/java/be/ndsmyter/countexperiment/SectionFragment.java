package be.ndsmyter.countexperiment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import be.ndsmyter.countexperiment.common.Listener;

/**
 * A placeholder fragment containing a simple view.
 */
public class SectionFragment extends Fragment implements View.OnClickListener, Listener {

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
    public static SectionFragment newInstance(FragmentModel fragmentModel) {
        SectionFragment fragment = new SectionFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_FRAGMENT_MODEL, fragmentModel);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Create a new SectionFragment. Default constructor without any arguments because Fragments should be initialized
     * like this. The methods setArguments and getArguments should be used instead to pass arguments.
     */
    public SectionFragment() {
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
        return rootView;
    }

    /**
     * Set the text of the TextView with the given id.
     *
     * @param id   the ID of the TextView we're looking for.
     * @param text the text that should be placed on the TextView.
     */
    private void setText(int id, String text) {
        if (this.rootView == null) {
            Log.i(TAG, "Trying to update " + id + " failed (" + text + ")");
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
            this.fragmentModel.addKeyDown();
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            this.fragmentModel.addKeyUp();
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
}
