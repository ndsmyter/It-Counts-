package be.ndsmyter.countexperiment.common;

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
}
