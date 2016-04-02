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

    public SectionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int sectionNumber = this.getArguments().getInt(ARG_SECTION_NUMBER);
        this.rootView = inflater.inflate(R.layout.fragment_main, container, false);
        rootView.setOnClickListener(this);
        setText(R.id.section_label, "Section" + sectionNumber);
        setText(R.id.count_label, "" + touched);
        return rootView;
    }

    private void setText(int label, String text) {
        if (this.rootView == null) {
            Log.i(TAG, "Trying to update " + label + " failed (" + text + ")");
            return;
        }
        ((TextView) this.rootView.findViewById(label)).setText(text);
    }

    @Override
    public void onClick(View view) {
        this.touched++;
        updateCount();
    }

    public boolean onKeyDown(int keyCode) {
        Log.i(TAG, "Key " + keyCode);
        Log.i(TAG, "Expecting " + KeyEvent.KEYCODE_VOLUME_DOWN + " or " + KeyEvent.KEYCODE_VOLUME_UP);
        Log.i(TAG, "Got: " + KeyEvent.keyCodeToString(keyCode));
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            this.downKey++;
            updateCount();
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            this.upKey++;
            updateCount();
        }
        return false;
    }

    private void updateCount() {
        setText(R.id.count_label, "" + (touched * touchedPoints + downKey * downKeyPoints + upKey * upKeyPoints));
    }
}
