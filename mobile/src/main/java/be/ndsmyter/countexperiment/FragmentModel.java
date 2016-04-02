package be.ndsmyter.countexperiment;

import be.ndsmyter.countexperiment.common.ListenerModel;
import be.ndsmyter.countexperiment.common.Visualization;

import java.io.Serializable;

/**
 * @author Nicolas De Smyter
 * @since 2 apr 16
 */
public class FragmentModel extends ListenerModel implements Serializable {

    private final int uniqueId;

    private String title;

    private int touched;

    private int volumeUps;

    private int volumeDowns;

    private int touchedPoints = 1;

    private int volumeUpPoints = 10;

    private int volumeDownPoints = 100;

    private Visualization visualization;

    private static int uniqueIds = 0;

    public FragmentModel(String title) {
        this.uniqueId = ++uniqueIds;
        this.title = title;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public int getCount() {
        return touched * touchedPoints + volumeUps * volumeUpPoints + volumeDowns * volumeDownPoints;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyChanged();
    }

    public int getTouched() {
        return touched;
    }

    public void addTouched() {
        this.touched++;
        notifyChanged();
    }

    public void setTouched(int touched) {
        this.touched = touched;
        notifyChanged();
    }

    public int getVolumeUps() {
        return volumeUps;
    }

    public void addVolumeUp() {
        this.volumeUps++;
        notifyChanged();
    }

    public void setVolumeUps(int volumeUps) {
        this.volumeUps = volumeUps;
        notifyChanged();
    }

    public int getVolumeDowns() {
        return volumeDowns;
    }

    public void addVolumeDown() {
        this.volumeDowns++;
        notifyChanged();
    }

    public void setVolumeDowns(int keyDowns) {
        this.volumeDowns = keyDowns;
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

    public Visualization getVisualization() {
        return visualization;
    }

    public void setVisualization(Visualization visualization) {
        visualization.setModel(this);
        this.visualization = visualization;
    }
}
