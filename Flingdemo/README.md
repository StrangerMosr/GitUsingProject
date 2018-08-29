![效果图](https://img-blog.csdn.net/20180829104651239?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxNTY5Njk5OTcz/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)
重写LinearLayoutManager类，配合v7包中的PagerSnapHelper 达成效果

FlingLayoutManager:

```
/**
 * Synopsis     ${SYNOPSIS}
 * Author		Mosr
 * Version		${VERSION}
 * Create 	    2018.08.28 15:52
 * Email  		intimatestranger@sina.cn
 */
public class FlingLayoutManager extends LinearLayoutManager implements RecyclerView.OnChildAttachStateChangeListener {
    private OnViewPagerListener mOnViewPagerListener;
    private PagerSnapHelper mPagerSnapHelper;
    private int mDrift;//位移，判断移动方向

    public FlingLayoutManager setmOnViewPagerListener(OnViewPagerListener mOnViewPagerListener) {
        this.mOnViewPagerListener = mOnViewPagerListener;
        return this;
    }

    public FlingLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        mPagerSnapHelper = new PagerSnapHelper();
    }

    @Override
    public void onAttachedToWindow(RecyclerView view) {
        view.addOnChildAttachStateChangeListener(this);
        mPagerSnapHelper.attachToRecyclerView(view);
        super.onAttachedToWindow(view);
    }

    @Override
    public void onDetachedFromWindow(RecyclerView view, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(view, recycler);
    }

    /**
     * @param dy       下 负
     *                 上 正
     * @param recycler
     * @param state
     *
     * @return
     */
    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        this.mDrift = dy;
        return super.scrollVerticallyBy(dy, recycler, state);
    }

    @Override
    public boolean canScrollVertically() {
//        return super.canScrollVertically();
        return true;
    }

    @Override
    public void onChildViewAttachedToWindow(View view) {
        if (this.mOnViewPagerListener != null) {
            mOnViewPagerListener.onPageSelected(getPosition(view), mDrift > 0);
        }
    }

    @Override
    public void onChildViewDetachedFromWindow(View view) {
        if (this.mOnViewPagerListener != null) {
            mOnViewPagerListener.onPageRelease(getPosition(view), mDrift > 0);
        }
    }

    @Override
    public void onScrollStateChanged(int state) {
        switch (state) {
            case RecyclerView.SCROLL_STATE_IDLE:
                if (this.mOnViewPagerListener != null) {
                    View mView = mPagerSnapHelper != null ? mPagerSnapHelper.findSnapView(this) : null;
                    if (mView != null) {
                        mOnViewPagerListener.onPageSelected(getPosition(mView), true);
                    }
                }
                break;
        }
        super.onScrollStateChanged(state);
    }
}
```
[帖子地址](https://blog.csdn.net/qq569699973/article/details/82180045)
[CSDN下载](https://download.csdn.net/download/qq569699973/10633151)
[Git托管地址](https://github.com/StrangerMosr/GitUsingProject/tree/master/Flingdemo)
