package be.ndsmyter.countexperiment;

import be.ndsmyter.countexperiment.common.ListenerModel;

import java.io.Serializable;

/**
 * @author Nicolas De Smyter
 * @since 2 apr 16
 */
public class FragmentModel extends ListenerModel implements Serializable {

    private String title;

    private int touched;

    private int keyUps;

    private int keyDowns;

    private int touchedPoints = 1;

    private int keyUpPoints = 10;

    private int keyDownPoints = 100;

    public FragmentModel(String title) {
        this.title = title;
    }

    public int getCount() {
        return touched * touchedPoints + keyUps * keyUpPoints + keyDowns * keyDownPoints;
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

    public int getKeyUps() {
        return keyUps;
    }

    public void addKeyUp() {
        this.keyUps++;
        notifyChanged();
    }

    public void setKeyUps(int keyUps) {
        this.keyUps = keyUps;
        notifyChanged();
    }

    public int getKeyDowns() {
        return keyDowns;
    }

    public void addKeyDown() {
        this.keyDowns++;
        notifyChanged();
    }

    public void setKeyDowns(int keyDowns) {
        this.keyDowns = keyDowns;
        notifyChanged();
    }

    public int getTouchedPoints() {
        return touchedPoints;
    }

    public void setTouchedPoints(int touchedPoints) {
        this.touchedPoints = touchedPoints;
        notifyChanged();
    }

    public int getKeyUpPoints() {
        return keyUpPoints;
    }

    public void setKeyUpPoints(int keyUpPoints) {
        this.keyUpPoints = keyUpPoints;
        notifyChanged();
    }

    public int getKeyDownPoints() {
        return keyDownPoints;
    }

    public void setKeyDownPoints(int keyDownPoints) {
        this.keyDownPoints = keyDownPoints;
        notifyChanged();
    }
}
