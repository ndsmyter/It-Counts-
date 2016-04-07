package be.ndsmyter.countexperiment.visuals.common;

import android.content.Context;
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
public abstract class AbstractVisualization extends Fragment implements Visualization {

    protected FragmentModel fragmentModel;

    private View rootView;

    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    /**
     * Get the fragment context that was stored using {#onAttach(Context)}
     *
     * @return the current context.
     */
    public Context getFragmentContext() {
        return context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(getLayout(), container, false);

        // Listen to touches
        rootView.findViewById(R.id.touch_panel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fragmentModel != null) {
                    fragmentModel.addTouched();
                }
            }
        });

        drawOnView();
        return rootView;
    }

    @Override
    public int getLayout() {
        return R.layout.empty_visualization;
    }

    @Override
    public void setModel(FragmentModel fragmentModel) {
        this.fragmentModel = fragmentModel;
    }

    /**
     * Get the model of this visualization.
     *
     * @return the model of the visualization.
     */
    protected FragmentModel getModel() {
        return this.fragmentModel;
    }

    /**
     * Get the root view to draw on.
     *
     * @return the view of this visualization.
     */
    protected View getRootView() {
        return this.rootView;
    }
}
