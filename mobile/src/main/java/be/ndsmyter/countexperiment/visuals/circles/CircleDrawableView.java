package be.ndsmyter.countexperiment.visuals.circles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import be.ndsmyter.countexperiment.common.Util;

/**
 * @author Sofie Van Gassen
 * @since 2 apr 2016
 */
public class CircleDrawableView extends View {
    //private ShapeDrawable mDrawable;

    int count, parentWidth,parentHeight, min,circleDiff;

    private Paint paint;

    // Colours copy pasted from https://www.google.com/design/spec/style/color.html#color-color-palette Indigo
    private String[] colors =
            {"#E8EAF6", "#C5CAE9", "#9FA8DA", "#7986CB", "#5C6BC0", "#3F51B5", "#3949AB", "#303F9F", "#283593",
                    "#1A237E"};

    public CircleDrawableView(Context context, int count, String color) {
        super(context);
        this.count = Math.abs(count);
        this.colors = Util.getColorScheme(color);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        if (Build.VERSION.SDK_INT >= 11) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int margin = (int) (2*20 * getResources().getDisplayMetrics().density);
        parentWidth = getRootView().getWidth()-margin;// MeasureSpec.getSize(widthMeasureSpec);
        parentHeight = getRootView().getWidth()-margin; //MeasureSpec.getSize(heightMeasureSpec);
        min = Math.min(parentHeight,parentWidth);
        if(count > 0) circleDiff = (int)Math.floor((min/2)/count);
        else circleDiff = 0;
        this.setMeasuredDimension(parentWidth, parentHeight); //nRow*blockSize); //
        this.setLayoutParams(new LinearLayout.LayoutParams(parentWidth, parentHeight)); //nRow*blockSize)); //
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL);
        Log.i("CE Circle", "" + circleDiff+" * "+count);
        for (int i = 0; i < count; i++) {
            Log.i("CE Circle", "" + (i * circleDiff) +","+
                    (min - i * circleDiff) +" -> " + colors[(i * 13) % 10]);

            paint.setColor(Color.parseColor(colors[(i * 13) % 10]));
            canvas.drawOval(i * circleDiff,
                    i * circleDiff,
                    min - i * circleDiff,
                    min - i * circleDiff, paint);
        }
    }
}
