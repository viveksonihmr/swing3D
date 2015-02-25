package com.example.brb_lab.swing3d;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends Activity
{
    private int showRange;
    private MyGLSurfaceView mGLView;
    private MyRenderer mRenderer;

    RadioButton radioButton1;
    RadioButton radioButton2;
    FrameLayout frameLayout1;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout1 = (FrameLayout) findViewById(R.id.FrameLayout1);

        mRenderer = new MyRenderer(this);
        mGLView = new MyGLSurfaceView(this);
        mGLView.setRenderer(mRenderer);

        frameLayout1.addView(mGLView);

        radioButton1 = (RadioButton)findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton)findViewById(R.id.radioButton2);

        radioButton1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(radioButton1.isChecked())
                {
                    mGLView.setMoveMode(mGLView.ROTATE_BUTTON);
                }
            }
        });

        radioButton2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(radioButton2.isChecked())
                {
                    mGLView.setMoveMode(mGLView.MOVE_BUTTON);
                }
            }
        });

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String fileName = "/sdcard/3Dswing/test.txt";
                String data = readSwing(fileName);

                mRenderer.readButtonTapped(data);
            }
        });
    }

    protected String readSwing(String fileName)
    {
        File file = new File(fileName);
        String outPut = null;
        try
        {
            FileInputStream inputStream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuffer StrBuf = new StringBuffer();
            String aLine = "";
            while(aLine != null)
            {
                aLine = reader.readLine();
                if(aLine != null)
                {
                    StrBuf.append(aLine + "\n");
                }
            }
            outPut = StrBuf.toString();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Toast.makeText(this,"File read FAIL",Toast.LENGTH_LONG).show();
        }

        return outPut;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLView.onResume();
    }
}