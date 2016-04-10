package be.ndsmyter.countexperiment;

import android.util.Log;

import be.ndsmyter.countexperiment.common.ListenerModel;
import be.ndsmyter.countexperiment.visuals.common.VisualManager;
import be.ndsmyter.countexperiment.visuals.common.Visualization;

import java.io.Serializable;

/**
 * @author Nicolas De Smyter
 * @since 2 apr 16
 */
public class FragmentModel extends ListenerModel implements Serializable {

    private final static String TAG = "CE";

    private final int uniqueId;

    private String title;

    private boolean useTouch = true;
    private int touchedPoints = 1;
    private String touchAction = "Add x";


    private boolean useVolumeUp = true;
    private int volumeUpPoints = 1;
    private String volumeUpAction = "Add x";

    private boolean useVolumeDown = true;
    private int volumeDownPoints = -1;
    private String volumeDownAction = "Add x";

    private boolean useShake = true;
    private int shakePoints = 0;
    private String shakeAction = "Reset to x";

    private int count = 0;

    private int visualization;

    private static int uniqueIds;

    public FragmentModel(String title) {
        this.uniqueId = ++uniqueIds;
        this.title = title;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public int getCount() {
        return count;
        //return touched * touchedPoints + volumeUps * volumeUpPoints + volumeDowns * volumeDownPoints;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyChanged();
    }

    public boolean getUseTouch() {
        return useTouch;
    }

    public void setUseTouch(boolean useTouch) {
        this.useTouch = useTouch;
        notifyChanged();
    }

    public String getTouchAction() {
        return touchAction;
    }

    public void setTouchAction(String touchAction) {
        this.touchAction = touchAction;
        notifyChanged();
    }

    public void addTouched() {
        Log.i("CE", "Process touch " + touchAction.replaceAll("x", "" + touchedPoints));
        switch (touchAction){
            case "Add x":
                count += touchedPoints; //this.touched++;
                break;
            case "Multiply with x":
                count *= touchedPoints;
                break;
            case "Reset to x":
                count = touchedPoints;
                break;
        }
        notifyChanged();
    }

    public boolean getUseVolumeUp() {
        return useVolumeUp;
    }

    public void setUseVolumeUp(boolean useVolumeUp) {
        this.useVolumeUp = useVolumeUp;
        notifyChanged();
    }

    public String getVolumeUpAction() {
        return volumeUpAction;
    }

    public void setVolumeUpAction(String volumeUpAction) {
        this.volumeUpAction = volumeUpAction;
        notifyChanged();
    }

    public void addVolumeUp() {
        Log.i("CE", "Process volume up " + volumeUpAction.replaceAll("x", ""+volumeUpPoints));
        if(useVolumeUp){
            switch (volumeUpAction){
                case "Add x":
                    count += volumeUpPoints;
                    break;
                case "Multiply with x":
                    count *= volumeUpPoints;
                    break;
                case "Reset to x":
                    count = volumeUpPoints;
                    break;
            }
        }
        notifyChanged();
    }

    public void addShaken() {
        Log.i("CE", "Process shaken " + shakeAction.replaceAll("x", ""+shakePoints));
        if(useShake){
            switch (shakeAction){
                case "Add x":
                    count += shakePoints;
                    break;
                case "Multiply with x":
                    count *= shakePoints;
                    break;
                case "Reset to x":
                    count = shakePoints;
                    break;
            }
        }
        notifyChanged();
    }

    public boolean getUseVolumeDown() {
        return useVolumeDown;
    }

    public void setUseVolumeDown(boolean useVolumeDown) {
        this.useVolumeDown = useVolumeDown;
        notifyChanged();
    }

    public String getVolumeDownAction() {
        return volumeDownAction;
    }

    public void setVolumeDownAction(String volumeDownAction) {
        this.volumeDownAction = volumeDownAction;
        notifyChanged();
    }

    public void addVolumeDown() {
        Log.i("CE", "Process volume down " + volumeDownAction.replaceAll("x", ""+volumeDownPoints));
        if(useVolumeDown) {
            switch (volumeDownAction) {
                case "Add x":
                    count += volumeDownPoints;
                    break;
                case "Multiply with x":
                    count *= volumeDownPoints;
                    break;
                case "Reset to x":
                    count = volumeDownPoints;
                    break;
            }
        }
        notifyChanged();
    }

    public int getTouchedPoints() {
        return touchedPoints;
    }

    public void setTouchedPoints(int touchedPoints) {
        this.touchedPoints = touchedPoints;
        notifyChanged();
    }

    public int getVolumeUpPoints() {
        return volumeUpPoints;
    }

    public void setVolumeUpPoints(int volumeUpPoints) {
        this.volumeUpPoints = volumeUpPoints;
        notifyChanged();
    }

    public int getVolumeDownPoints() {
        return volumeDownPoints;
    }

    public void setVolumeDownPoints(int volumeDownPoints) {
        this.volumeDownPoints = volumeDownPoints;
        notifyChanged();
    }

    /**
     * Get the visualization as an object. The current model has also been added to this view.
     *
     * @return the visualization instantiated and filled up with the model.
     */
    public Visualization getVisualization() {
        Visualization visual = VisualManager.getVisualization(visualization);
        if (visual != null) {
            visual.setModel(this);
        }
        return visual;
    }

    /**
     * Get the index of the visualization.
     *
     * @return the index of the visualization.
     */
    public int getVisualizationIndex() {
        return visualization;
    }

    /**
     * Update the current visualization.
     *
     * @param visualization the index of the visualization.
     */
    public void setVisualization(int visualization) {
        this.visualization = visualization;
    }
}
