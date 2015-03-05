package com.example.brb_lab.swing3d;

import android.app.Activity;

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
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends Activity
{
    private int showRange;
    GLThread partGL = new GLThread();

    RadioButton radioButton1;
    RadioButton radioButton2;
    FrameLayout frameLayout1;
    SeekBar seekBar1;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout1 = (FrameLayout) findViewById(R.id.FrameLayout1);

        partGL.Initianl(this);

        frameLayout1.addView(partGL.getGLView());


        radioButton1 = (RadioButton)findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton)findViewById(R.id.radioButton2);
        seekBar1 = (SeekBar)findViewById(R.id.seekBar1);
        seekBar1.setMax(0);

        radioButton1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(radioButton1.isChecked())
                {
                    partGL.getGLView().setMoveMode(partGL.getGLView().ROTATE_BUTTON);
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
                    partGL.getGLView().setMoveMode(partGL.getGLView().MOVE_BUTTON);
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

                partGL.getRenderer().readButtonTapped(data);
                showRange = partGL.getRenderer().getLineLength();
                seekBar1.setMax((showRange - 1)/3 );
                seekBar1.setProgress((showRange - 1)/3);
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MoreLine();
            }
        });

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                LessLine();
            }
        });

        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                seekBar1.setProgress(0);
                for(int i = 0; i <= partGL.getRenderer().getLineLength()/3; i++)
                {
                    try
                    {
                        Thread.sleep(100);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    MoreLine();
                }

            }
        });



        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                showRange = (seekBar1.getProgress() + 1)*3;
                partGL.getRenderer().DrawTo(showRange);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });
    }
    protected void MoreLine()
    {
        if(showRange < partGL.getRenderer().getLineLength() - 2)
        {
            showRange++;
            showRange++;
            showRange++;
            seekBar1.setProgress((showRange - 1)/3);
        }
        else
        {
            showRange = partGL.getRenderer().getLineLength();
            seekBar1.setProgress((showRange - 1)/3);
        }
    }

    protected void LessLine()
    {
        if(showRange > 5)
        {
            showRange--;
            showRange--;
            showRange--;
            seekBar1.setProgress(showRange/3 - 1);
        }
        else
        {
            showRange = 0;
            seekBar1.setProgress(showRange/3 - 1);
        }
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
        partGL.getGLView().onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        partGL.getGLView().onResume();
    }
}