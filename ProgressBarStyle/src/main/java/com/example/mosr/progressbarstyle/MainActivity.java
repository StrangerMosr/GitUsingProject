package com.example.mosr.progressbarstyle;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mCustomProgressBar;
    private ProgressBar mCustomProgressBar2;
    private ProgressBar mQQProgressBar;
    private ProgressBar mWeChatProgressBar;
    private ProgressBar mCodeCustomProgressBar;
    private ProgressBar mCodeCustomProgressBar2;
    private ProgressBar mCodeQQProgressBar;
    private ProgressBar mCodeWeChatProgressBar;

    private ResetAnimation mResetAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCustomProgressBar = (ProgressBar) findViewById(R.id.mCustomProgressBar);
        mCustomProgressBar2 = (ProgressBar) findViewById(R.id.mCustomProgressBar2);
        mQQProgressBar = (ProgressBar) findViewById(R.id.mQQProgressBar);
        mWeChatProgressBar = (ProgressBar) findViewById(R.id.mWeChatProgressBar);
        mCodeCustomProgressBar = (ProgressBar) findViewById(R.id.mCodeCustomProgressBar);
        mCodeCustomProgressBar2 = (ProgressBar) findViewById(R.id.mCodeCustomProgressBar2);
        mCodeQQProgressBar = (ProgressBar) findViewById(R.id.mCodeQQProgressBar);
        mCodeWeChatProgressBar = (ProgressBar) findViewById(R.id.mCodeWeChatProgressBar);

        mResetAnimation = new ResetAnimation();
        mResetAnimation.setDuration(3000);
        mResetAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mCustomProgressBar.setVisibility(View.VISIBLE);
                mCustomProgressBar2.setVisibility(View.VISIBLE);
                mQQProgressBar.setVisibility(View.VISIBLE);
                mWeChatProgressBar.setVisibility(View.VISIBLE);
                mCodeQQProgressBar.setVisibility(View.VISIBLE);
                mCodeWeChatProgressBar.setVisibility(View.VISIBLE);
                mCodeCustomProgressBar.setVisibility(View.VISIBLE);
                mCodeCustomProgressBar2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mCustomProgressBar.setVisibility(View.INVISIBLE);
                mCustomProgressBar2.setVisibility(View.INVISIBLE);
                mQQProgressBar.setVisibility(View.INVISIBLE);
                mWeChatProgressBar.setVisibility(View.INVISIBLE);
                mCodeQQProgressBar.setVisibility(View.INVISIBLE);
                mCodeWeChatProgressBar.setVisibility(View.INVISIBLE);
                mCodeCustomProgressBar.setVisibility(View.INVISIBLE);
                mCodeCustomProgressBar2.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        /*代码编写样式*/
        /*自定义*/
        mCodeCustomProgressBar.setProgressDrawable(new ClipDrawable(customStyle("#DABDFF", "#6F00FF", "#DABDFF"), ClipDrawable.VERTICAL, ClipDrawable.HORIZONTAL));
        mCodeCustomProgressBar2.setProgressDrawable(new ClipDrawable(customStyle("#DABDFF", "#6F00FF", "#DABDFF"), ClipDrawable.HORIZONTAL, ClipDrawable.HORIZONTAL));
        //QQ
        mCodeQQProgressBar.setBackgroundColor(Color.parseColor("#12B7F5"));
        mCodeQQProgressBar.setProgressDrawable(new ClipDrawable(mQQStyle(this, "#A5FFFFFF", "#FFFFFF"), ClipDrawable.VERTICAL, ClipDrawable.HORIZONTAL));
        //WetChat
        mCodeWeChatProgressBar.setProgressDrawable(new ClipDrawable(new ColorDrawable(Color.parseColor("#46C01B")), ClipDrawable.VERTICAL, ClipDrawable.HORIZONTAL));
    }

    public void onClickStart(View v) {
        if (null != mResetAnimation)
            v.startAnimation(mResetAnimation);
    }

    public Drawable customStyle(@NonNull String mStartColor, @NonNull String mCenterColor, @NonNull String mEndColor) {
        GradientDrawable mGradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{Color.parseColor(mStartColor), Color.parseColor(mCenterColor),
                        Color.parseColor(mEndColor)});
        mGradientDrawable.setGradientCenter(0, 0.5f);
        return mGradientDrawable;
    }

    public Drawable mQQStyle(@NonNull Activity mActivity, @NonNull String mStartColor, @NonNull String mEndColor) {
        GradientDrawable mGradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR,
                new int[]{Color.parseColor(mStartColor),
                        Color.parseColor(mEndColor)});
        mGradientDrawable.setGradientCenter(0, 0);
        mGradientDrawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        mGradientDrawable.setShape(GradientDrawable.RECTANGLE);
        mGradientDrawable.setGradientRadius(mActivity.getWindowManager().getDefaultDisplay().getWidth() / 2);
        return mGradientDrawable;
    }

    class ResetAnimation extends Animation {

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            mCustomProgressBar.setProgress((int) (interpolatedTime * 100));
            mCustomProgressBar2.setProgress((int) (interpolatedTime * 100));
            mQQProgressBar.setProgress((int) (interpolatedTime * 100));
            mWeChatProgressBar.setProgress((int) (interpolatedTime * 100));
            mCodeQQProgressBar.setProgress((int) (interpolatedTime * 100));
            mCodeWeChatProgressBar.setProgress((int) (interpolatedTime * 100));
            mCodeCustomProgressBar.setProgress((int) (interpolatedTime * 100));
            mCodeCustomProgressBar2.setProgress((int) (interpolatedTime * 100));
            super.applyTransformation(interpolatedTime, t);
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }
}

