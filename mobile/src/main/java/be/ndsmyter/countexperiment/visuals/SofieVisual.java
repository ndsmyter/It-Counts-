package be.ndsmyter.countexperiment.visuals;

import android.util.Log;
import be.ndsmyter.countexperiment.FragmentModel;
import be.ndsmyter.countexperiment.R;
import be.ndsmyter.countexperiment.common.AbstractVisualization;
import be.ndsmyter.countexperiment.common.Listener;

/**
 * @author Nicolas De Smyter
 * @since 2 apr 16
 */
public class SofieVisual extends AbstractVisualization implements Listener {

    private int countShowing;

    private static final String TAG = "Sofie";

    @Override
    public int getLayout() {
        return R.layout.sofie_visualization;
    }

    @Override
    public void drawOnView() {
        FragmentModel model = getModel();
        countShowing = model.getCount();
        updateView();
        model.addListener(this);
    }

    @Override
    public void notifyChanged() {
        int currentCount = getModel().getCount();
        if (countShowing != currentCount) {
            // Only redraw the view if the count has changed
            countShowing = currentCount;
            updateView();
        }
    }

    private void updateView() {
        // Do something fancy to update the view
        Log.i(TAG, "The value should show " + countShowing);
    }
}
