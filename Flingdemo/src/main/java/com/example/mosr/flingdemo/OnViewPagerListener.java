package com.example.mosr.flingdemo;

/**
 * Synopsis     ${SYNOPSIS}
 * Author		Mosr
 * Version		${VERSION}
 * Create 	    2018.08.28 16:07
 * Email  		intimatestranger@sina.cn
 */
public interface OnViewPagerListener {
    void onPageSelected(int postion, boolean isNext);

    void onPageRelease(int postion, boolean isNext);
}
