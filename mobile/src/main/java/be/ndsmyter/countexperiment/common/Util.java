package be.ndsmyter.countexperiment.common;

import android.content.Context;

/**
 * @author Nicolas De Smyter
 * @since 2 apr 16
 */
public class Util {

    /**
     * Convert Display Scale Factor sizes to pixels.
     *
     * @param context the context of the view.
     * @param dps     the size in DPS
     * @return the size in pixels
     */
    public static int toPixels(Context context, int dps) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dps * scale + 0.5f);
    }
}
