package be.ndsmyter.countexperiment.visuals.squares;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.view.View;
import android.widget.LinearLayout;

import be.ndsmyter.countexperiment.common.Util;

/**
 * @author Sofie Van Gassen
 * @since 2 apr 2016
 */
public class LittleSquareDrawableView extends View {
    //private ShapeDrawable mDrawable;

    int count, nRow, nColLastRow, blockSize;

    private Paint paint;

    // Colours copy pasted from https://www.google.com/design/spec/style/color.html#color-color-palette Indigo
    private String[] colors =
            {"#E8EAF6", "#C5CAE9", "#9FA8DA", "#7986CB", "#5C6BC0", "#3F51B5", "#3949AB", "#303F9F", "#283593",
                    "#1A237E"};

    public LittleSquareDrawableView(Context context,int count, String color) {
        super(context);
        this.count = Math.abs(count);
        this.colors = Util.getColorScheme(color);
        nRow = (int) Math.ceil(count / 10);
        nColLastRow = count % 10;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        if (Build.VERSION.SDK_INT >= 11) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int margin = (int) (2*20 * getResources().getDisplayMetrics().density);
        int parentWidth = getRootView().getWidth()-margin;
        blockSize = (int) Math.floor(parentWidth / 10);
        int parentHeight = blockSize *(nRow+1); //getRootView().getWidth()-margin;
        this.setMeasuredDimension(parentWidth, parentHeight); //nRow*blockSize); //
        this.setLayoutParams(new LinearLayout.LayoutParams(parentWidth, parentHeight)); //nRow*blockSize)); //
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < count; i++) {
            int x = i % 10;
            int y = (int) Math.ceil(i / 10);
            paint.setColor(Color.parseColor(colors[((x + y) * 13) % 10]));
            canvas.drawRect(x * blockSize, y * blockSize, (x + 1) * blockSize, (y + 1) * blockSize, paint);
        }
    }
}
