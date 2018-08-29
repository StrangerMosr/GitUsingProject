package com.example.mosr.gradientview;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Synopsis     ${SYNOPSIS}
 * Author		Mosr
 * version		${VERSION}
 * Create 	    2017/3/17 17:16
 * Email  		intimatestranger@sina.cn
 */
public class initBitmap {
    public static Bitmap bitmap;

    public static void init(Resources res) {
        bitmap = BitmapFactory.decodeResource(res, R.mipmap.ic_launcher);
    }
}
