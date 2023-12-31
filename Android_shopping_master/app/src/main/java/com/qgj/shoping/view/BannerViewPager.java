package com.qgj.shoping.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;


public class BannerViewPager extends ViewPager {

    Handler mhander;


    public BannerViewPager( Context context) {
        super(context);
    }

    public BannerViewPager( Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mhander = new Handler(Looper.getMainLooper());
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startLooper();
    }

    private void startLooper() {
       mhander.postDelayed(looper,1000);
    }
    private  final Runnable looper = new Runnable() {
        @Override
        public void run() {
            int time = getCurrentItem();
            time ++;
            setCurrentItem(time);
            postDelayed(this,3000);
        }
    };
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mhander.removeCallbacks(looper);
    }
}
