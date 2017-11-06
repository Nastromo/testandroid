package com.face_location.facelocation;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;

/**
 * Created by admin on 23.10.17.
 */

public class CustomSizeFloatingActionButton extends FloatingActionButton {

    public CustomSizeFloatingActionButton(Context context) {
        super(context);
    }

    public CustomSizeFloatingActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSizeFloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        setMeasuredDimension((int) (width * 1.5f), (int) (height * 1.5f));
    }
}
