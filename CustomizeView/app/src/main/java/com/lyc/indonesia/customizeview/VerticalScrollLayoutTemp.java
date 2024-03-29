package com.lyc.indonesia.customizeview;

import android.content.Context;
import androidx.core.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.*;
import android.widget.Scroller;

public class VerticalScrollLayoutTemp extends ViewGroup {
    private final int mTouchSlop;
    /**
     * 屏幕的高度
     */
    private int mScreenHeight;
    /**
     * 手指按下时的getScrollY
     */
    private int mScrollStart;
    /**
     * 手指抬起时的getScrollY
     */
    private int mScrollEnd;
    /**
     * 记录移动时的Y
     */
    private int mLastY;
    /**
     * 滚动的辅助类
     */
    private Scroller mScroller;
    /**
     * 是否正在滚动
     */
    private boolean isScrolling;
    /**
     * 加速度检测
     */
    private VelocityTracker mVelocityTracker;
    /**
     * 记录当前页
     */
    private int currentPage = 0;
    private float mYDown;
    private float mYLastMove;
    private float mYMove;

    public VerticalScrollLayoutTemp(Context context, AttributeSet attrs) {
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
        int count = getChildCount();
        for (int i = 0; i < count; ++i)
        {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec,mScreenHeight);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            // 设置主布局的高度
            MarginLayoutParams lp = (MarginLayoutParams) getLayoutParams();
            lp.height = mScreenHeight * childCount;
            setLayoutParams(lp);

            for (int i = 0; i < childCount; i++)
            {
                View child = getChildAt(i);
                if (child.getVisibility() != View.GONE)
                {
                    child.layout(l, i * mScreenHeight, r, (i + 1) * mScreenHeight);// 调用每个自布局的layout
                }
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                mYDown = ev.getRawY();
//                mYLastMove = mYDown;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                mYMove = ev.getRawY();
//                float diff = Math.abs(mYLastMove - mYDown);
//                mYLastMove = mYMove;
//                // 当手指拖动值大于TouchSlop值时，认为应该进行滚动，拦截子控件的事件
//                if (diff > mTouchSlop) {
//                    return true;
//                }
//                break;
//        }
//        return super.onInterceptTouchEvent(ev);
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 如果当前正在滚动，调用父类的onTouchEvent
        if (isScrolling) {
            return super.onTouchEvent(event);
        }

        int action = event.getAction();
        int y = (int) event.getY();

        obtainVelocity(event);
        switch (action)
        {
            case MotionEvent.ACTION_DOWN:

                mScrollStart = getScrollY();
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:

                if (!mScroller.isFinished())
                {
                    mScroller.abortAnimation();
                }

                int dy = mLastY - y;
                // 边界值检查
                int scrollY = getScrollY();
                // 已经到达顶端，下拉多少，就往上滚动多少
                if (dy < 0 && scrollY + dy < 0)
                {
                    dy = -scrollY;
                }
                // 已经到达底部，上拉多少，就往下滚动多少
                if (dy > 0 && scrollY + dy > getHeight() - mScreenHeight)
                {
                    dy = getHeight() - mScreenHeight - scrollY;
                }

                scrollBy(0, dy);
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:

                mScrollEnd = getScrollY();

                int dScrollY = mScrollEnd - mScrollStart;

                if (wantScrollToNext())// 往上滑动
                {
                    if (shouldScrollToNext())
                    {
                        mScroller.startScroll(0, getScrollY(), 0, mScreenHeight - dScrollY);

                    } else
                    {
                        mScroller.startScroll(0, getScrollY(), 0, -dScrollY);
                    }

                }

                if (wantScrollToPre())// 往下滑动
                {
                    if (shouldScrollToPre())
                    {
                        mScroller.startScroll(0, getScrollY(), 0, -mScreenHeight - dScrollY);

                    } else
                    {
                        mScroller.startScroll(0, getScrollY(), 0, -dScrollY);
                    }
                }
                isScrolling = true;
                postInvalidate();
                recycleVelocity();
                break;
        }

        return true;
    }


    /**
     * 根据滚动距离判断是否能够滚动到下一页
     *
     * @return
     */
    private boolean shouldScrollToNext()
    {
        return mScrollEnd - mScrollStart > mScreenHeight / 2 || Math.abs(getVelocity()) > 600;
    }

    /**
     * 根据用户滑动，判断用户的意图是否是滚动到下一页
     *
     * @return
     */
    private boolean wantScrollToNext()
    {
        return mScrollEnd > mScrollStart;
    }

    /**
     * 根据滚动距离判断是否能够滚动到上一页
     *
     * @return
     */
    private boolean shouldScrollToPre()
    {
        return -mScrollEnd + mScrollStart > mScreenHeight / 2 || Math.abs(getVelocity()) > 600;
    }

    /**
     * 根据用户滑动，判断用户的意图是否是滚动到上一页
     *
     * @return
     */
    private boolean wantScrollToPre()
    {
        return mScrollEnd < mScrollStart;
    }

    @Override
    public void computeScroll()
    {
        super.computeScroll();
        if (mScroller.computeScrollOffset())
        {
            scrollTo(0, mScroller.getCurrY());
            postInvalidate();
        } else
        {
            int position = getScrollY() / mScreenHeight;
            Log.e("xxx", position + "," + currentPage);
            isScrolling = false;
        }

    }

    /**
     * 获取y方向的加速度
     *
     * @return
     */
    private int getVelocity()
    {
        mVelocityTracker.computeCurrentVelocity(1000);
        return (int) mVelocityTracker.getYVelocity();
    }

    /**
     * 释放资源
     */
    private void recycleVelocity()
    {
        if (mVelocityTracker != null)
        {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    /**
     * 初始化加速度检测器
     *
     * @param event
     */
    private void obtainVelocity(MotionEvent event)
    {
        if (mVelocityTracker == null)
        {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }
}
