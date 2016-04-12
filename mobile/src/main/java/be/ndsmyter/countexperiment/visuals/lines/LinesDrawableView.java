package be.ndsmyter.countexperiment.visuals.lines;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Random;

import be.ndsmyter.countexperiment.common.Util;

/**
 * @author Sofie Van Gassen
 * @since 2 apr 2016
 */
public class LinesDrawableView extends View {
    //private ShapeDrawable mDrawable;

    int count, parentWidth,parentHeight,maxCoord;
    Random r = new Random();
    private Paint paint;

    // Colours copy pasted from https://www.google.com/design/spec/style/color.html#color-color-palette Indigo
    private String[] colors =
            {"#E8EAF6", "#C5CAE9", "#9FA8DA", "#7986CB", "#5C6BC0", "#3F51B5", "#3949AB", "#303F9F", "#283593",
                    "#1A237E"};

    public LinesDrawableView(Context context,int count, String color) {
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
        parentWidth = getRootView().getWidth()-margin;
        parentHeight = getRootView().getWidth()-margin;
        maxCoord = Math.min(parentWidth,parentHeight);
        this.setMeasuredDimension(parentWidth, parentHeight); //nRow*blockSize); //
        this.setLayoutParams(new LinearLayout.LayoutParams(parentWidth, parentHeight)); //nRow*blockSize)); //
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStrokeWidth(5);
        for (int i = 0; i < count; i++) {
            paint.setColor(Color.parseColor(colors[(i * 13) % 10]));
            canvas.drawLine((float)r.nextInt(maxCoord),
                    (float)r.nextInt(maxCoord),
                    (float)r.nextInt(maxCoord),
                    (float)r.nextInt(maxCoord),
                    paint);
        }
    }
}
