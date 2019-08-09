package com.lyc.indonesia.customizeview;

import android.content.Context;
import androidx.core.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.*;
import android.widget.Scroller;

public class VerticalScrollLayout extends ViewGroup {
    private final int mScreenHeight;
    private Scroller mScroller;
    /**
     * 判定为拖动的最小移动像素数
     */
    private int mTouchSlop;
    /**
     * 手机按下时的屏幕坐标
     */
    private float mYDown;
    /**
     * 手机当时所处的屏幕坐标
     */
    private float mYMove;
    private float mYLastMove;
    private int topBorder;
    private int bottomBorder;

    public VerticalScrollLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 第一步，创建Scroller的实例
        mScroller = new Scroller(context);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        // 获取TouchSlop值
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);

        /**
         * 获得屏幕的高度
         */
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenHeight = outMetrics.heightPixels;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //以下代码也可以这样写measureChildren(widthMeasureSpec,heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                // 在水平方向上进行布局子控件
                childView.layout(0, i * childView.getMeasuredHeight(), childView.getMeasuredWidth(), (i+1) * childView.getMeasuredHeight());
            }
            // 初始化左右边界值
            topBorder = getChildAt(0).getTop();
            getChildAt(0).getBottom();
            bottomBorder = getChildAt(getChildCount() - 1).getBottom();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mYDown = ev.getRawY();
                mYLastMove = mYDown;
                break;
            case MotionEvent.ACTION_MOVE:
                mYMove = ev.getRawY();
                float diff = Math.abs(mYMove - mYDown);
                mYLastMove = mYMove;
                // 当手指拖动值大于TouchSlop值时，认为应该进行滚动，拦截子控件的事件
                if (diff > mTouchSlop) {
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mYMove = event.getRawY();
                //注意用老的值减去新的值，是按照Scroller的坐标来走的，正常情况下是
                //最新的坐标减去老的坐标得到的数正负刚好是View坐标系下的正负。
                int scrolledY = (int) (mYLastMove - mYMove);
                if (getScrollY() + scrolledY < topBorder) {
                    scrollTo(0, topBorder);
                    return true;
                } else if (getScrollY() + getHeight() + scrolledY > bottomBorder) {
                    scrollTo(0, bottomBorder - getHeight());
                    return true;
                }
                Log.d("test","scrolledY:"+scrolledY);
                scrollBy(0, scrolledY);
                mYLastMove = mYMove;
                break;
            case MotionEvent.ACTION_UP:
                // 当手指抬起时，根据当前的滚动值来判定应该滚动到哪个子控件的界面
                int targetIndex = (getScrollY() + getHeight() / 2) / getHeight();
                int dy = targetIndex * getHeight()-getScrollY();
                Log.d("test","getWidth():"+getHeight());
                Log.d("test","getScrollY():"+getScrollY());
                Log.d("test","targetIndex:"+targetIndex);
                Log.d("test","dy:"+dy);
                // 调用startScroll()方法来初始化滚动数据并刷新界面
                mScroller.startScroll(0, getScrollY(), 0, dy);
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        // 第三步，重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            invalidate();
        }
    }
}
