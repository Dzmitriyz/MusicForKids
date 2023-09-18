package com.example.musicforkids;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;

public class CustomViewPager extends ViewPager {
    private boolean swipeable = false;
    public CustomViewPager( Context context) {
        super(context);
    }

    public CustomViewPager(  Context context,AttributeSet attrs) {
        super(context, attrs);
    }

    public void setWipeable(boolean swipeable){
        this.swipeable = swipeable;
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0){
        return (this.swipeable) ? super.onInterceptTouchEvent(arg0) : false;
    }
}
