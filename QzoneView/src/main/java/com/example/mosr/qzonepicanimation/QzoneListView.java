package com.example.mosr.qzonepicanimation;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

/**
 * Synopsis     ${SYNOPSIS}
 * Author		Mosr
 * version		${VERSION}
 * Create 	    2017/2/15 16:38
 * Email  		intimatestranger@sina.cn
 */
public class QzoneListView extends ListView {
    private ImageView mZoneImage;
    private ProgressBar mProgressBar;
    private int mZoneHeight;


    private RenovateListener mRenovateListener;

    public QzoneListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mZoneHeight = context.getResources().getDimensionPixelSize(R.dimen.size_img_height);
        View mView = ((Activity) context).getLayoutInflater().inflate(R.layout.listview_head, null);
        this.mZoneImage = (ImageView) mView.findViewById(R.id.img_head);
        this.mProgressBar = (ProgressBar) mView.findViewById(R.id.mProgressBar);
        this.addHeaderView(mView);
    }

    public void setmRenovateListener(RenovateListener mRenovateListener) {
        this.mRenovateListener = mRenovateListener;
    }

    public void setProgress(int mProgress) {
        mProgressBar.setProgress(mProgress);
        if (mProgress == 100)
            onFinish();
    }

    public void onFinish() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        //deltaY Y轴的变量

        if (deltaY < 0) {
            mZoneImage.getLayoutParams().height = mZoneImage.getHeight() - deltaY / 2;
            mZoneImage.requestLayout();
        } else {
            if (mZoneImage.getHeight() > mZoneHeight) {
                mZoneImage.getLayoutParams().height = mZoneImage.getHeight() - deltaY / 2;
                mZoneImage.requestLayout();
            }
        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (null != mZoneImage) {
            View header = (View) mZoneImage.getParent();
            int delaty = header.getTop();
            if (delaty < 0 && mZoneImage.getHeight() > mZoneHeight) {
                mZoneImage.getLayoutParams().height = mZoneImage.getHeight() + delaty / 2;
                header.layout(header.getLeft(), 0, header.getRight(), header.getHeight());
                mZoneImage.requestLayout();
            }
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    public void computeScroll() {

        super.computeScroll();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                if (null != mRenovateListener && mZoneImage.getHeight() > mZoneHeight + 300) {
                    mRenovateListener.onRenovate();
                    mProgressBar.setVisibility(View.VISIBLE);
                }
                ResetAnimation mResetAnimation = new ResetAnimation(mZoneImage, mZoneHeight);
                mResetAnimation.setDuration(400);
                mZoneImage.startAnimation(mResetAnimation);
                break;
        }
        return super.onTouchEvent(ev);
    }

    class ResetAnimation extends Animation {
        private ImageView mImageView;
        private int tartHeight;
        private int mCurrentHeight;
        private int mExtraHeight;

        public ResetAnimation(ImageView mImageView, int mTartHeight) {
            this.mImageView = mImageView;
            this.tartHeight = mTartHeight;
            this.mCurrentHeight = mImageView.getHeight();
            this.mExtraHeight = mCurrentHeight - tartHeight;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            mImageView.getLayoutParams().height = (int) (mCurrentHeight - mExtraHeight * interpolatedTime);
            mImageView.requestLayout();
            setProgress((int) (interpolatedTime * 100));
            super.applyTransformation(interpolatedTime, t);
        }
    }

    interface RenovateListener {
        void onRenovate();
    }
}
