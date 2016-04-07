package be.ndsmyter.countexperiment.common;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Nicolas De Smyter
 * @since 2 apr 16
 */
public class ListenerModel {

    private List<Listener> listeners;

    private final String listenerLock = "listenerLock";

    /**
     * Create a new model that will hold the listeners that will be notified of any changes to the model.
     */
    public ListenerModel() {
        synchronized (listenerLock) {
            this.listeners = new CopyOnWriteArrayList<Listener>();
        }
    }

    /**
     * Add a new Listener to the list of listeners that will be notified on a change.
     *
     * @param listener the listener that should be notified.
     */
    public void addListener(Listener listener) {
        synchronized (listenerLock) {
            this.listeners.add(listener);
        }
    }

    /**
     * Remove the listener from the list of listeners.
     *
     * @param listener the listener that should be removed.
     */
    public void removeListener(Listener listener) {
        synchronized (listenerLock) {
            this.listeners.remove(listener);
        }
    }

    /**
     * Notify the listeners that something has changed.
     */
    public void notifyChanged() {
        synchronized (listenerLock) {
            for (Listener listener : listeners) {
                listener.notifyChanged();
            }
        }
    }
}
