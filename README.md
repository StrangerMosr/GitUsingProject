# QZone下拉图片放大控件



先看效果图：
------



![这里写图片描述](http://img.my.csdn.net/uploads/201702/17/1487327798_2825.gif)


思路：
--
看见Qzone的效果，很像**ListView**，所以就用ListView来写首先头部有一张图片，

所以用addHeadView给ListView添加一个ImageView，然后根据手指滑动的距离来放

大图片：onTouch事件。当松手的时候呢让ImageView自动回到原来位置，思路如此

代码：
---

```

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
```

用法：
---
ImageView设置

     android:scaleType="centerCrop"

布局设置

    <com.example.mosr.qzonepicanimation.QzoneListView
        android:id="@+id/Liv_Qzone"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>

监听设置

        mQzoneListView.setmRenovateListener(new QzoneListView.RenovateListener() {
            @Override
            public void onRenovate() {
                Toast.makeText(MainActivity.this, "onRenovate", Toast.LENGTH_SHORT).show();
            }
        });


[Git托管地址，欢迎Fork](https://github.com/StrangerMosr/GitUsingProject.git)

[Csdn地址](http://blog.csdn.net/qq569699973/article/details/55520494)
