package be.ndsmyter.countexperiment.visuals.squares;

import android.util.Log;
import android.view.ViewGroup;
import be.ndsmyter.countexperiment.FragmentModel;
import be.ndsmyter.countexperiment.R;
import be.ndsmyter.countexperiment.common.Listener;
import be.ndsmyter.countexperiment.visuals.common.AbstractVisualization;

/**
 * @author Nicolas De Smyter
 * @since 2 apr 16
 */
public class SquareVisual extends AbstractVisualization implements Listener {

    private int countShowing;

    private static final String TAG = "Square";

    @Override
    public int getLayout() {
        return R.layout.square_visualization;
    }

    @Override
    public void drawOnView() {
        FragmentModel model = getModel();
        countShowing = model.getCount();
        updateView();
        model.addListener(this);
    }

    @Override
    public String getName() {
        return "Square Visual";
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
        int count = this.countShowing;
        // Add 1 icon of the count
        if (count > 0) {
            ViewGroup viewGroup = (ViewGroup) getRootView().findViewById(R.id.touch_panel);
            viewGroup.removeAllViews();
            viewGroup.addView(new LittleSquareDrawableView(getActivity(), count));
        }
    }
}
