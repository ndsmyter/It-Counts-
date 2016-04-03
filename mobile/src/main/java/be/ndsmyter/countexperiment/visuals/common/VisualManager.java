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
        visualizations.add(new VisualElement(SquareVisual.class, new SquareVisual().getName()));
        visualizations.add(new VisualElement(TallyVisual.class, new TallyVisual().getName()));
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

    /**
     * Get a list of the visualization elements.
     *
     * @return the list of visualization elements.
     */
    public static List<VisualElement> getVisualizations() {
        return visualizations;
    }

    /**
     * Get the visual element from the given index.
     *
     * @param index the index of the required visual element
     * @return the required visual element.
     */
    public static VisualElement getVisualElement(int index) {
        return visualizations.get(index);
    }

    /**
     * Get the visual element from the given index.
     *
     * @param index the index of the required visual element
     * @return the required visual element.
     * @see #getVisualElement(int)
     */
    public static VisualElement getVisualElement(String index) {
        return getVisualElement(Integer.parseInt(index));
    }

    /**
     * Inner class to hold some information about every visualization.
     */
    public static class VisualElement {

        private Class<? extends Visualization> theClass;

        private String name;

        /**
         * Create a new VisualElement, an object that holds more information about the visualization.
         *
         * @param theClass the class that will be used to initialize the object.
         * @param name     the name of the visualization.
         */
        public VisualElement(Class<? extends Visualization> theClass, String name) {
            this.theClass = theClass;
            this.name = name;
        }

        /**
         * Get the class visualization.
         *
         * @return the visualization.
         */
        public Class<? extends Visualization> getTheClass() {
            return theClass;
        }

        /**
         * Get the name of the visualization.
         *
         * @return the name of the visualization
         */
        public String getName() {
            return name;
        }
    }
}
