package com.tjek.sdk.sample.zoomlayoutsample.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * There is a bug in ViewPager, this is just a simple work around
 */
public class HackedViewPager extends ViewPager {

    public HackedViewPager(Context context) {
        super(context);
    }

    public HackedViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            // pointerIndex out of range
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            return super.onTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            // pointerIndex out of range
        }
        return false;
    }

}
