package be.ndsmyter.countexperiment.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nicolas De Smyter
 * @since 2 apr 16
 */
public class ListenerModel {

    private List<Listener> listeners;

    /**
     * Create a new model that will hold the listeners that will be notified of any changes to the model.
     */
    public ListenerModel() {
        this.listeners = new ArrayList<Listener>();
    }

    /**
     * Add a new Listener to the list of listeners that will be notified on a change.
     *
     * @param listener the listener that should be notified.
     */
    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    /**
     * Remove the listener from the list of listeners.
     *
     * @param listener the listener that should be removed.
     */
    public void removeListener(Listener listener) {
        this.listeners.remove(listener);
    }

    /**
     * Notify the listeners that something has changed.
     */
    public void notifyChanged() {
        for (Listener listener : listeners) {
            listener.notifyChanged();
        }
    }
}
