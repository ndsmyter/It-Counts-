package be.ndsmyter.countexperiment.visuals;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;

import be.ndsmyter.countexperiment.R;

/**
 * Created by svgassen on 2/04/2016.
 */
public class LittleSquareDrawableView extends View {
    //private ShapeDrawable mDrawable;

    int count,nRow,nColLastRow,blockSize;
    // Colours copy pasted from https://www.google.com/design/spec/style/color.html#color-color-palette Indigo
    private String[] colors = {"#E8EAF6","#C5CAE9","#9FA8DA","#7986CB","#5C6BC0","#3F51B5","#3949AB","#303F9F","#283593","#1A237E"};


    public LittleSquareDrawableView(Context context, int count) {
        super(context);
        this.count = count;
        nRow = (int) Math.ceil(count / 10);
        nColLastRow = count % 10;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        blockSize = (int) Math.floor(parentWidth/10);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        this.setMeasuredDimension(parentWidth, parentHeight); //nRow*blockSize); //
        this.setLayoutParams(new LinearLayout.LayoutParams(parentWidth,parentHeight)); //nRow*blockSize)); //
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < count; i++) {
            int x = i % 10;
            int y = (int) Math.ceil(i / 10);
            paint.setColor(Color.parseColor(colors[((x+y)*13)%10]));
            canvas.drawRect( x * blockSize, y * blockSize, (x+1) * blockSize, (y+1) * blockSize, paint);
        }
    }
}
