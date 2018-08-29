package com.example.mosr.gradientview;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.ViewTreeObserver;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    LightRender lightRender;
    GLSurfaceView glView;
    private TextView txt;
    private TextView txt2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initBitmap.init(this.getResources());

        lightRender = new LightRender();
        glView = new GLSurfaceView(this);
        glView.setRenderer(lightRender);
        txt = (TextView) findViewById(R.id.txt);
        txt2 = (TextView) findViewById(R.id.txt_2);
//        GradientDrawable mGradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR,
//                new int[]{Color.parseColor("#FF5B2D"),
//                        Color.parseColor("#FF3C4F")});
//        mGradientDrawable.setGradientCenter(0, 0);
//        mGradientDrawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);
//        mGradientDrawable.setShape(GradientDrawable.RECTANGLE);
//        mGradientDrawable.setGradientRadius(getWindowManager().getDefaultDisplay().getWidth());
//        txt.setBackgroundDrawable(mGradientDrawable);
        txt.setBackgroundDrawable(drawSystemBar());
        final int[] h1 = {0};
        ViewTreeObserver vto1 = txt.getViewTreeObserver();
        vto1.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                txt.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                h1[0] = txt.getHeight();
            }
        });
        ViewTreeObserver vto2 = txt2.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                txt2.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                float i = (float) h1[0] / (float) txt2.getHeight();
                txt2.setBackgroundDrawable(drawSystemBar(i));
            }
        });
    }

    // 处理事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        lightRender.onKeyDown(keyCode, event);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        lightRender.onKeyUp(keyCode, event);
        return super.onKeyUp(keyCode, event);
    }

    /**
     * 系统状态栏着色
     */
    public Drawable drawSystemBar() {
        GradientDrawable mGradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR,
                new int[]{Color.parseColor("#FFDD00"),
                        Color.parseColor("#FF3C4F")});
        mGradientDrawable.setGradientCenter(0, 0);
        mGradientDrawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        mGradientDrawable.setShape(GradientDrawable.RECTANGLE);
        mGradientDrawable.setGradientRadius(getWindowManager().getDefaultDisplay().getWidth() / 2);
        return mGradientDrawable;
    }


    /**
     * 系统状态栏着色
     */
    public Drawable drawSystemBar(float y) {
        GradientDrawable mGradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR,
                new int[]{Color.parseColor("#FFDD00"),
                        Color.parseColor("#FF3C4F")});
        mGradientDrawable.setGradientCenter(0, -y);
        mGradientDrawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        mGradientDrawable.setShape(GradientDrawable.RECTANGLE);
        mGradientDrawable.setGradientRadius(getWindowManager().getDefaultDisplay().getWidth() / 2);
//        mGradientDrawable.setSize(getWindowManager().getDefaultDisplay().getWidth(), top + bottom);
//        mGradientDrawable.setBounds(0, 0, mGradientDrawable.getIntrinsicWidth(),
//                mGradientDrawable.getIntrinsicHeight());
        return mGradientDrawable;
    }

}
