package be.ndsmyter.countexperiment.visuals.tally;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import be.ndsmyter.countexperiment.FragmentModel;
import be.ndsmyter.countexperiment.R;
import be.ndsmyter.countexperiment.common.Listener;
import be.ndsmyter.countexperiment.visuals.common.AbstractVisualization;

/**
 * @author Nicolas De Smyter
 * @since 2 apr 16
 */
public class TallyVisual extends AbstractVisualization implements Listener {

    private int countShowing;

    private static final String TAG = "Tally";

    @Override
    public int getLayout() {
        return R.layout.tally_visualization;
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
        return "Tally Visual";
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
    }

    /**
     * Update the view.
     */
    private void updateView() {
        int count = this.countShowing;
        int fives = (int) Math.floor(count / 5);
        count -= fives * 5;

        // Add {fives} fives
        // Add 1 icon of the remaining {count}
        Log.i(TAG, "Fives=" + fives + " other=" + count);
        ViewGroup viewGroup = (ViewGroup) getRootView().findViewById(R.id.touch_panel);
        viewGroup.removeAllViews();
        for (int i = 0; i < fives; i++) {
            viewGroup.addView(getView(5));
        }
        if (count > 0) {
            viewGroup.addView(getView(count));
        }
    }

    /**
     * Get the view for the specific number.
     *
     * @param number the number that we should show.
     * @return the ImageView containing the specific number.
     */
    private ImageView getView(int number) {
        ImageView imageView = new ImageView(getFragmentContext());
        int resource = 0;
        switch (number) {
            case 1:
                resource = R.drawable.tally_1;
                break;
            case 2:
                resource = R.drawable.tally_2;
                break;
            case 3:
                resource = R.drawable.tally_3;
                break;
            case 4:
                resource = R.drawable.tally_4;
                break;
            case 5:
                resource = R.drawable.tally_5;
                break;
        }
        if (resource > 0) {
            imageView.setImageResource(resource);
        }

//        imageView.setLayoutParams(new GridLayout.LayoutParams(GridLayout.LayoutParams.WRAP_CONTENT,
//                                                              Util.toPixels(getActivity(), 20)));

        return imageView;
    }
}
