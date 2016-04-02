package be.ndsmyter.countexperiment.visuals;

import be.ndsmyter.countexperiment.R;
import be.ndsmyter.countexperiment.common.AbstractVisualization;

/**
 * @author Nicolas De Smyter
 * @since 2 apr 16
 */
public class TallyVisual extends AbstractVisualization {

    @Override
    protected int getVisualization() {
        return R.layout.tally_visualization;
    }
}
