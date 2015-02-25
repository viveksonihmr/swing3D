package com.example.brb_lab.swing3d;


import android.annotation.SuppressLint;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.FloatMath;
import android.view.MotionEvent;


public class MyGLSurfaceView extends GLSurfaceView
{
    public static int NO_BUTTON = 0;
    public static int ROTATE_BUTTON = 1;
    public static int MOVE_BUTTON = 2;
    public int pointer;
    public float distance;
    private MyRenderer mRenderer;
    float mPreviousX;
    float mPreviousY;
    int clickCount = 0;
    int moveMode = 0;
    float dx;
    float dy;

    public MyGLSurfaceView(Context context)
    {
        super(context);
    }

    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouchEvent(MotionEvent event)
    {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                pointer = 1;
                break;
            case MotionEvent.ACTION_POINTER_2_DOWN:
                pointer = 2;
                dx = event.getX() - mPreviousX;
                dy = event.getY() - mPreviousY;
                distance = FloatMath.sqrt(dx * dx + dy * dy);
                break;
            case MotionEvent.ACTION_MOVE:
                if(pointer == 1)
                {
                    dx = event.getX() - mPreviousX;
                    dy = event.getY() - mPreviousY;

                    if (moveMode == ROTATE_BUTTON) {
                        mRenderer.setXAngle((mRenderer.getXAngle() + dx) / getWidth() * 100);
                        mRenderer.setYAngle((mRenderer.getYAngle() + dy) / getHeight() * 100);
                    } else if (moveMode == MOVE_BUTTON) {
                        mRenderer.setDx((mRenderer.getDx() + dx) / getWidth());
                        mRenderer.setDy((mRenderer.getDy() - dy) / getHeight());
                    }
                }
                else if(pointer == 2)
                {
                    dx = event.getX() - mPreviousX;
                    dy = event.getY() - mPreviousY;
                    distance = FloatMath.sqrt(dx * dx + dy * dy);
                }
                requestRender();
                break;
            case MotionEvent.ACTION_UP:
                mPreviousX = event.getX();
                mPreviousY = event.getY();
                clickCount++;

                break;
        }

        return true;
    }

    public void setRenderer(Renderer renderer)
    {
        super.setRenderer(renderer);
        mRenderer = (MyRenderer)renderer;
    }

    public void setMoveMode(int mode)
    {
        moveMode = mode;
    }
    public int getMoveMode()
    {
        return moveMode;
    }


}
