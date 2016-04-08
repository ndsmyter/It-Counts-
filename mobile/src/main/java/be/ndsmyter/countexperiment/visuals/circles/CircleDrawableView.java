package be.ndsmyter.countexperiment.visuals.circles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author Sofie Van Gassen
 * @since 2 apr 2016
 */
public class CircleDrawableView extends View {
    //private ShapeDrawable mDrawable;

    int count, parentWidth,parentHeight,max,circleDiff;

    private Paint paint;

    // Colours copy pasted from https://www.google.com/design/spec/style/color.html#color-color-palette Indigo
    private String[] colors =
            {"#E8EAF6", "#C5CAE9", "#9FA8DA", "#7986CB", "#5C6BC0", "#3F51B5", "#3949AB", "#303F9F", "#283593",
                    "#1A237E"};

    public CircleDrawableView(Context context, int count) {
        super(context);
        this.count = count;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        if (Build.VERSION.SDK_INT >= 11) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        max=Math.max(parentHeight,parentWidth);
        circleDiff = max/count;
        this.setMeasuredDimension(parentWidth, parentHeight); //nRow*blockSize); //
        this.setLayoutParams(new LinearLayout.LayoutParams(parentWidth, parentHeight)); //nRow*blockSize)); //
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < count; i++) {
            paint.setColor(Color.parseColor(colors[(i * 13) % 10]));

            canvas.drawOval(0 + i *circleDiff,
                    0 + i * circleDiff,
                    max - i * circleDiff,
                    max-i*circleDiff, paint);
        }
    }
}
