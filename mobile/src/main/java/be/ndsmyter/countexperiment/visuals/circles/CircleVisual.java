package be.ndsmyter.countexperiment.visuals.circles;

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
public class CircleVisual extends AbstractVisualization implements Listener {

    private int countShowing;
    private String color;

    private static final String TAG = "Circle";

    @Override
    public int getLayout() {
        return R.layout.square_visualization;
    }

    @Override
    public void drawOnView() {
        FragmentModel model = getModel();
        countShowing = model.getCount();
        color = model.getColor();
        updateView();
        model.addListener(this);
    }

    @Override
    public String getName() {
        return "Circle Visual";
    }

    @Override
    public void notifyChanged() {
        FragmentModel model = getModel();
        if (model == null) {
            Log.e(TAG, "The model is null ?");
            return;
        }
        if (getView() == null) {
            model.removeListener(this);
        }
        int currentCount = model.getCount();
        if (countShowing != currentCount) {
            // Only redraw the view if the count has changed
            countShowing = currentCount;
            updateView();
        }
        String modelColor = model.getColor();
        if (!color.equals(modelColor)) {
            // Only redraw the view if the count has changed
            color = modelColor;
            updateView();
        }
    }

    /**
     * Update the view.
     */
    private void updateView() {
        // Do something fancy to update the view
        Log.i(TAG, "The value should show " + countShowing);
        int count = this.countShowing;
        // Add 1 icon of the count

        ViewGroup viewGroup = (ViewGroup) getRootView().findViewById(R.id.touch_panel);
        viewGroup.removeAllViews();
        viewGroup.addView(new CircleDrawableView(getFragmentContext(), count,color));

    }
}
