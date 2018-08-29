package com.example.mosr.clipdrawable;

import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ClipDrawable drawable;
    Timer timer;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                /**setlevel()设置图片截取的大小
                 * 修改ClipDrawable的level值，level值为0--10000；
                 * 0：截取图片大小为空白，10000：截取图片为整张图片；
                 */
                drawable.setLevel(drawable.getLevel() + 50);
            }
        }

    };

    public void start(View v) {
        drawable.setLevel(0);
        if (timer != null)
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    Message msg = new Message();
                    msg.what = 0x123;
                    handler.sendMessage(msg);
                    if (drawable.getLevel() >= 10000) {
                        this.cancel();
                    }
                }
            }, 0, 10);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.image);
        //获取图片所显示的ClipDrawable对象
        drawable = (ClipDrawable) imageView.getDrawable();
        timer = new Timer();
    }
}
