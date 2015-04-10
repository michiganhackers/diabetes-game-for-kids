package com.mdstudios.diabeticons.Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by jawad on 04/12/14.
 *
 * Creates a square ImageView, where the height matches the width
 */
public class SquareImageView extends ImageView {
    // TODO: Figure out why the below is neccessary [seen in multiple examples]
    int squareDim = 1000000000;

    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int currentSquareDim;
        int width = getMeasuredWidth();

        // If the width is not zero, then make the height match it
        if(width != 0)
          currentSquareDim = width;
        // Otherwise, making the width match the height
        else
          currentSquareDim = getMeasuredHeight();

        // TODO: Hmmm....? Purpose?
        if(currentSquareDim < squareDim) {
            squareDim = currentSquareDim;
        }

        // Set the height an width both to the width
        setMeasuredDimension(squareDim, squareDim);
    }
}