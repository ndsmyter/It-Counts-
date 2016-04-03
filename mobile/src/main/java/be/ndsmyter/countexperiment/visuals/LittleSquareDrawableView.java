package be.ndsmyter.countexperiment.visuals;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.LinearLayout;

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

    public LittleSquareDrawableView(Context context, int count) {
        super(context);
        this.count = count;
        nRow = (int) Math.ceil(count / 10);
        nColLastRow = count % 10;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        blockSize = (int) Math.floor(parentWidth / 10);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
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
