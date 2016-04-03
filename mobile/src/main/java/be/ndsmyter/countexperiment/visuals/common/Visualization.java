package be.ndsmyter.countexperiment.visuals.common;

import be.ndsmyter.countexperiment.FragmentModel;

/**
 * @author Nicolas De Smyter
 * @since 2 apr 16
 */
public interface Visualization {

    /**
     * Set the model that contains all the information for the visualization.
     *
     * @param fragmentModel the model that contains all the useful information.
     */
    void setModel(FragmentModel fragmentModel);

    /**
     * Get the layout for the visualization.
     *
     * @return the ID of the layout.
     */
    int getLayout();

    /**
     * Draw on the view.
     */
    void drawOnView();

    /**
     * The name for the visualization.
     *
     * @return the name of the visualization.
     */
    String getName();
}
