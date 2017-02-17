package com.example.mosr.qzonepicanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private QzoneListView mQzoneListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQzoneListView = ((QzoneListView) findViewById(R.id.Liv_Qzone));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new String[]{
                "星期一，",
                "星期二，",
                "星期三，",
                "星期四，",
                "星期五，",
                "星期六，",
                "星期七，",
                "星期一，",
                "星期二，",
                "星期三，",
                "星期四，",
                "星期五，",
                "星期六，",
                "星期七，",
        });
        mQzoneListView.setAdapter(adapter);
        mQzoneListView.setmRenovateListener(new QzoneListView.RenovateListener() {
            @Override
            public void onRenovate() {
                Toast.makeText(MainActivity.this, "onRenovate", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }
}
