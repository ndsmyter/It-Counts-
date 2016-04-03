package be.ndsmyter.countexperiment.visuals.common;

import android.util.Log;
import be.ndsmyter.countexperiment.visuals.squares.SquareVisual;
import be.ndsmyter.countexperiment.visuals.tally.TallyVisual;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nicolas De Smyter
 * @since 3 apr 16
 */
public class VisualManager {

    private final static String TAG = "VisualManager";

    private final static List<VisualElement> visualizations;

    static {
        // Fill up the visualizations list
        visualizations = new ArrayList<VisualElement>();
        visualizations.add(new VisualElement(TallyVisual.class, new TallyVisual().getName()));
        visualizations.add(new VisualElement(SquareVisual.class, new SquareVisual().getName()));
    }

    /**
     * Get the specific visualization from the list.
     *
     * @param index the index of the specific visualization.
     * @return the Visualization if one was found, null if none was found.
     */
    @SuppressWarnings("TryWithIdenticalCatches")
    public static Visualization getVisualization(int index) {
        Visualization visualization = null;
        try {
            visualization = visualizations.get(index).getTheClass().newInstance();
        } catch (InstantiationException e) {
            Log.e(TAG, "Couldn't instantiate the view with index " + index, e);
        } catch (IllegalAccessException e) {
            Log.e(TAG, "Couldn't instantiate the view with index " + index, e);
        }
        return visualization;
    }

    public static List<VisualElement> getVisualizations() {
        return visualizations;
    }

    public static class VisualElement {

        private Class<? extends Visualization> theClass;

        private String name;

        public VisualElement(Class<? extends Visualization> theClass, String name) {
            this.theClass = theClass;
            this.name = name;
        }

        public Class<? extends Visualization> getTheClass() {
            return theClass;
        }

        public String getName() {
            return name;
        }
    }
}
