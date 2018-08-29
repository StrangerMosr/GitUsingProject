package com.example.mosr.gradientview;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.view.KeyEvent;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Synopsis     ${SYNOPSIS}
 * Author		Mosr
 * version		${VERSION}
 * Create 	    2017/3/17 17:15
 * Email  		intimatestranger@sina.cn
 */
public class LightRender implements GLSurfaceView.Renderer {

    /**
     * 渲染类
     * author:pis
     */
    private Context context;
    private int one = 0x10000;
    private Bitmap bitmap;


    public boolean mFlag;
    public boolean bLight = true;//是否开启灯光

    private int[] vertices;//顶点数组
    private int[] textCood;//纹理数组

    float step = 0.3f;
    float xrot, yrot; //旋转
    float xSpeed, ySpeed; //移动速度
    private int[] textures = new int[1];

    private IntBuffer vertexBuffer; //顶点缓冲
    private IntBuffer textCoodBuffer; //纹理缓冲
    /**
     * 设置灯光
     *
     * @param context
     */
    //环境光
    private float[] lightAmbient;
    private FloatBuffer AmbientBuffer;
    //漫射光
    private float[] lightDiffuse;
    private FloatBuffer diffuseBuffer;
    //光源位置
    private float[] lightPosition;
    private FloatBuffer positionBuffer;

    /**
     * 初始化缓冲数据
     */
    private void initBuffer() {
        //顶点
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asIntBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        //纹理
        ByteBuffer tbb = ByteBuffer.allocateDirect(textCood.length * 4 * 6);
        tbb.order(ByteOrder.nativeOrder());
        textCoodBuffer = tbb.asIntBuffer();
        for (int i = 0; i < 6; i++) {
            textCoodBuffer.put(textCood);
        }
        textCoodBuffer.position(0);

        //环境光
        ByteBuffer ambientbb = ByteBuffer.allocateDirect(lightAmbient.length * 4 * 6);
        ambientbb.order(ByteOrder.nativeOrder());
        AmbientBuffer = ambientbb.asFloatBuffer();
        AmbientBuffer.put(lightAmbient);
        AmbientBuffer.position(0);

        //漫射光
        ByteBuffer diffusebb = ByteBuffer.allocateDirect(lightDiffuse.length * 4 * 6);
        diffusebb.order(ByteOrder.nativeOrder());
        diffuseBuffer = diffusebb.asFloatBuffer();
        diffuseBuffer.put(lightDiffuse);
        diffuseBuffer.position(0);

        //灯光位置
        ByteBuffer positionbb = ByteBuffer.allocateDirect(lightPosition.length * 4 * 6);
        positionbb.order(ByteOrder.nativeOrder());
        positionBuffer = positionbb.asFloatBuffer();
        positionBuffer.put(lightPosition);
        positionBuffer.position(0);

    }

    /**
     * 初始化顶点、纹理、灯光数据
     */
    private void initData() {
        //顶点数组
        vertices = new int[]{-one, -one, one, one, -one, one, -one, one, one,
                one, one, one, one, -one, one, one, -one, -one, one, one, one,
                one, one, -one, one, -one, -one, -one, -one, -one, one, one,
                -one, -one, one, -one, -one, -one, -one, -one, -one, one, -one,
                one, -one, -one, one, one, -one, one, -one, one, one, -one,
                -one, one, one, one, one, one, -one, -one, -one, -one, -one,
                one, one, -one, -one, one, -one, one};

        //纹理数组,贴图时注意android中坐标与OpengGL 中定义的不同，android，y轴是向下的
        textCood = new int[]{0, 0, one, 0, 0, one, one, one};
        //灯光
        lightAmbient = new float[]{0.5f, 0.5f, 0.5f, 1.0f};
        lightDiffuse = new float[]{1.0f, 1.0f, 1.0f, 1.0f};
        lightPosition = new float[]{0.0f, 0.0f, 2.0f, 1.0f};


    }

    public LightRender() {

        initData();
        initBuffer();

    }


    @Override
    public void onDrawFrame(GL10 gl) {
        //清除颜色和深度缓存
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        //启用灯光
        gl.glEnable(GL10.GL_LIGHTING);

        //启用顶点和纹理缓存
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        //移动和旋转设置
        gl.glTranslatef(0.0f, 0.0f, -6.0f);
        gl.glRotatef(xrot, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(yrot, 0.0f, 1.0f, 0.0f);

        //设置顶点和纹理,经常忘记设置，唉！
        gl.glVertexPointer(3, GL10.GL_FIXED, 0, vertexBuffer);
        gl.glTexCoordPointer(2, GL10.GL_FIXED, 0, textCoodBuffer);

        //绘制六个面，贴图
        for (int i = 0; i < 6; i++) {
            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, i * 4, 4);
        }
        //取消缓存，需我们自己手动
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glLoadIdentity();

        if (mFlag) {
            xrot += 0.5f;
            yrot += 0.5f;
        }
        if (!bLight) {
            gl.glDisable(GL10.GL_LIGHT1);
        } else {
            gl.glEnable(GL10.GL_LIGHT1);
        }
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        //场景大小
        gl.glViewport(0, 0, width, height);
        float ratio = (float) width / height;
        //投影矩阵
        gl.glMatrixMode(GL10.GL_PROJECTION);
        //重置下
        gl.glLoadIdentity();
        //视图大小设置
        gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
        //观察模型
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //透视效果
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
        //清屏
        gl.glClearColor(0, 0, 0, 0);
        //启用阴影平滑
        gl.glShadeModel(GL10.GL_SMOOTH);
        //清除深度缓存
        gl.glClearDepthf(one);
        //启用深度缓存
        gl.glEnable(GL10.GL_DEPTH_TEST);
        //深度缓存模式
        gl.glDepthFunc(GL10.GL_LEQUAL);

        /**
         * 设置纹理
         */
        //启用纹理
        gl.glEnable(GL10.GL_TEXTURE_2D);
        //创建纹理
        gl.glGenTextures(1, textures, 0);
        //绑定纹理
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
        //生成纹理
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, initBitmap.bitmap, 0);
        //线性滤波处理
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
                GL10.GL_LINEAR);
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
                GL10.GL_LINEAR);
        /**
         * 设置灯光
         */

        //设置环境光
        gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_AMBIENT, AmbientBuffer);
        //设置漫射光
        gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_DIFFUSE, diffuseBuffer);
        //设置灯光位置
        gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_POSITION, positionBuffer);
        //启用1号灯光
        gl.glEnable(GL10.GL_LIGHT1);

    }

    /**
     * 操作键盘上的上、下、左、右、确认键进行正方体的翻转和灯光操作
     *
     * @param keyCode
     * @param event
     *
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                mFlag = true;
                xSpeed = -step;
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                mFlag = true;
                xSpeed = step;
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                mFlag = true;
                ySpeed = -step;
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                mFlag = true;
                ySpeed = step;
                break;
            case KeyEvent.KEYCODE_DPAD_CENTER:
                bLight = !bLight;
                break;
        }
        return false;
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        mFlag = false;
        return false;
    }

}
