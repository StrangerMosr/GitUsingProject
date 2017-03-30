# QQ、微信、立体感、中间展开样式进度条



先看效果图：
------

![效果图](http://img.blog.csdn.net/20170330093640913?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXE1Njk2OTk5NzM=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


这特么是废话：
--
<--废话开始-->

就是有这么一个小需求，所以写了。写过几种简单样式的，没有深度。所以这次装X写个好看点的。在娘娘身上搜了半天出来的都是写自定义控件啥的，至于嘛？就一个进度条，还自定义...装X了吧。我这个能用源码的决不自定义的人 就不想看，所以调研了一番，结合以前知识写了本文。

<--废话结束-->
XML 写法（与效果图一一对应）：
---

自定义样式（壹）、
```
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">

    <item android:id="@android:id/secondaryProgress">
        <clip>
        <shape>
            <gradient
                android:angle="270"
                android:centerColor="#6F00FF"
                android:centerY="0.5"
                android:endColor="#DABDFF"
                android:startColor="#DABDFF"
                />
        </shape>
        </clip>
    </item>
</layer-list>
```
自定义样式（贰）、
```
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">

    <item android:id="@android:id/secondaryProgress">
        <clip
            android:clipOrientation="horizontal"
            android:gravity="center_vertical">
            <shape>
                <gradient
                    android:angle="270"
                    android:centerColor="#6F00FF"
                    android:centerY="0.5"
                    android:endColor="#DABDFF"
                    android:startColor="#DABDFF"
                    />
            </shape>
        </clip>
    </item>
</layer-list>
```
QQ样式、

```
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">
    <!--QQ样式-->
    <item android:id="@android:id/background">
        <shape>
            <gradient
                android:angle="270"
                android:centerColor="#12B7F5"
                android:centerY="0.75"
                android:endColor="#12B7F5"
                android:startColor="#12B7F5"
                />
        </shape>
    </item>
    <item>
        <clip>
            <shape>
                <gradient
                    android:centerX="0"
                    android:centerY="0"
                    android:endColor="#FFFFFF"
                    android:gradientRadius="360"
                    android:startColor="#A5FFFFFF"
                    android:type="radial"/>
            </shape>
        </clip>
    </item>
</layer-list>
```
微信样式

```
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">
    <!--微信样式-->
    <item android:id="@android:id/progress">
        <clip>
            <shape>
                <gradient
                    android:angle="270"
                    android:centerColor="#46C01B"
                    android:centerY="0.5"
                    android:endColor="#46C01B"
                    android:startColor="#46C01B"
                    />
            </shape>
        </clip>
    </item>
</layer-list>
```
Style定义、

```
    <style name="mCustomProgress_horizontal">
        <item name="android:indeterminateOnly">false</item>
        <item name="android:progressDrawable">@drawable/mprogress_custom_horizontal</item>
        <item name="android:indeterminateDrawable">@android:drawable/progress_indeterminate_horizontal</item>
        <item name="android:minHeight">3dip</item>
        <item name="android:maxHeight">20dip</item>
    </style>
    <style name="mCustomProgress_horizontal2">
        <item name="android:indeterminateOnly">false</item>
        <item name="android:progressDrawable">@drawable/mprogress_custom_horizontal2</item>
        <item name="android:indeterminateDrawable">@android:drawable/progress_indeterminate_horizontal</item>
        <item name="android:minHeight">3dip</item>
        <item name="android:maxHeight">20dip</item>
    </style>
    <style name="mQQProgress_horizontal">
        <item name="android:indeterminateOnly">false</item>
        <item name="android:progressDrawable">@drawable/mprogress_qq_horizontal</item>
        <item name="android:indeterminateDrawable">@android:drawable/progress_indeterminate_horizontal</item>
        <item name="android:minHeight">3dip</item>
        <item name="android:maxHeight">20dip</item>
    </style>
    <style name="mWeChatProgress_horizontal">
        <item name="android:indeterminateOnly">false</item>
        <item name="android:progressDrawable">@drawable/mprogress_wechat_horizontal</item>
        <item name="android:indeterminateDrawable">@android:drawable/progress_indeterminate_horizontal</item>
        <item name="android:minHeight">3dip</item>
        <item name="android:maxHeight">20dip</item>
```
使用、

```
    <ProgressBar
        android:id="@+id/mCustomProgressBar"
        style="@style/mCustomProgress_horizontal"
        android:layout_width="match_parent"
        android:layout_height="3dip"
        android:layout_marginTop="5dip"
        android:background="@android:color/transparent"
        android:visibility="invisible"/>

    <ProgressBar
        android:id="@+id/mCustomProgressBar2"
        style="@style/mCustomProgress_horizontal2"
        android:layout_width="match_parent"
        android:layout_height="3dip"
        android:layout_marginTop="5dip"
        android:background="@android:color/transparent"
        android:visibility="invisible"/>

    <ProgressBar
        android:id="@+id/mQQProgressBar"
        style="@style/mQQProgress_horizontal"
        android:layout_width="match_parent"
        android:layout_height="3dip"
        android:layout_marginTop="5dip"
        android:background="@android:color/transparent"
        android:visibility="invisible"/>

    <ProgressBar
        android:id="@+id/mWeChatProgressBar"
        style="@style/mWeChatProgress_horizontal"
        android:layout_width="match_parent"
        android:layout_height="3dip"
        android:layout_marginTop="5dip"
        android:background="@android:color/transparent"
        android:visibility="invisible"/>
```

代码 写法（与效果图一一对应）：
---
个人需要灵活通用的，所以代码写法才是主要在意的。
```
        /*代码编写样式*/
        /*自定义*/
        mCodeCustomProgressBar.setProgressDrawable(
                new ClipDrawable(customStyle("#DABDFF", "#6F00FF", "#DABDFF"), ClipDrawable.VERTICAL, ClipDrawable.HORIZONTAL));

        mCodeCustomProgressBar2.setProgressDrawable(
                new ClipDrawable(customStyle("#DABDFF", "#6F00FF", "#DABDFF"), ClipDrawable.HORIZONTAL, ClipDrawable.HORIZONTAL));
        /*QQ*/
        mCodeQQProgressBar.setBackgroundColor(Color.parseColor("#12B7F5"));

        mCodeQQProgressBar.setProgressDrawable(
                new ClipDrawable(mQQStyle(this, "#A5FFFFFF", "#FFFFFF"), ClipDrawable.VERTICAL, ClipDrawable.HORIZONTAL));
        /*WetChat*/
        mCodeWeChatProgressBar.setProgressDrawable(
                new ClipDrawable(new ColorDrawable(Color.parseColor("#46C01B")), ClipDrawable.VERTICAL, ClipDrawable.HORIZONTAL));
```
具体方法

```
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

```
必须要说的一个ClipDrawable类，XML中体现为clip标签。想要有进度条一点一点增长的效果就必须要加上去。ClipDrawable是通过设置一个Drawable的当前显示比例来裁剪出另一张Drawable，你可以通过调节这个比例来控制裁剪的宽高，以及裁剪内容占整个容器的权重，通过ClipDrawable的setLevel()方法调节显示比例可以实现类似Progress进度条的效果。ClipDrawable的level值范围在[0,10000]，level的值越大裁剪的内容越少，如果level为10000时则完全显示。

[Git托管地址，欢迎Fork](https://github.com/StrangerMosr/GitUsingProject/tree/master/ProgressBarStyle)

[Csdn地址](http://blog.csdn.net/qq569699973/article/details/68483855)

[Csdn下载](http://dl.download.csdn.net/down11/20170330/3bae3aeb4ed0c61f52f643eacce24609.rar?response-content-disposition=attachment%3Bfilename%3D%22ProgressBarStyle.rar%22&OSSAccessKeyId=9q6nvzoJGowBj4q1&Expires=1490857612&Signature=GW4gvweHZnLKl%2B3VTY3Lh3v0gUU%3D)
