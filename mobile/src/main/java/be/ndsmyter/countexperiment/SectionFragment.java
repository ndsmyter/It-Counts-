package be.ndsmyter.countexperiment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class SectionFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "CE";

    /**
     * The fragment argument representing the section number for this fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private int touchedPoints = 1;

    private int upKeyPoints = 10;

    private int downKeyPoints = 100;

    private int touched = 0;

    private int upKey = 0;

    private int downKey = 0;

    private View rootView;

    private String title;

    /**
     * Returns a new instance of this fragment for the given section number.
     */
    public static SectionFragment newInstance(int sectionNumber) {
        SectionFragment fragment = new SectionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
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
        int sectionNumber = this.getArguments().getInt(ARG_SECTION_NUMBER);
        this.rootView = inflater.inflate(R.layout.fragment_main, container, false);
        rootView.setOnClickListener(this);
        setTitle("Section " + sectionNumber);
        setText(R.id.count_label, "" + touched);
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
        this.touched++;
        updateCount();
    }

    /**
     * Handle the key events.
     *
     * @param keyCode the key code of the key event.
     * @return true if the event should be propagated upwards (and handled by other classes), false otherwise.
     */
    public boolean onKeyDown(int keyCode) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            this.downKey++;
            updateCount();
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            this.upKey++;
            updateCount();
        }
        return false;
    }

    /**
     * Update the count on the screen.
     */
    private void updateCount() {
        setText(R.id.count_label, "" + (touched * touchedPoints + downKey * downKeyPoints + upKey * upKeyPoints));
    }

    /**
     * Get the current title of this fragment.
     *
     * @return the title of the fragment
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the new title of this fragment.
     *
     * @param title the new title of the fragment
     */
    public void setTitle(String title) {
        this.title = title;
        setText(R.id.title_label, title);
    }
}
