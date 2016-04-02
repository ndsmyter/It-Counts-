package be.ndsmyter.countexperiment.common;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import be.ndsmyter.countexperiment.FragmentModel;
import be.ndsmyter.countexperiment.R;

/**
 * @author Nicolas De Smyter
 * @since 2 apr 16
 */
public class AbstractVisualization extends Fragment implements Visualization {

    protected FragmentModel fragmentModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View visualizationView = inflater.inflate(getVisualization(), container, false);

        // Listen to touches
        visualizationView.findViewById(R.id.touch_panel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fragmentModel != null) {
                    fragmentModel.addTouched();
                }
            }
        });
        return visualizationView;
    }

    protected int getVisualization() {
        return R.layout.empty_visualization;
    }

    @Override
    public void setModel(FragmentModel fragmentModel) {
        this.fragmentModel = fragmentModel;
    }
}
