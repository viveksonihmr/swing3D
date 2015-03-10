package com.example.brb_lab.swing3d;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UpperActivity extends Activity
{
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upper);

        Button button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent mChanYoung = new Intent(getApplicationContext(),ChanMain.class);
                startActivity(mChanYoung);
            }
        });
        Button button8 = (Button)findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent mVivek = new Intent(getApplicationContext(),vivekactivity.class);
                startActivity( mVivek);
            }
        });
    }

    public void onPause()
    {
        super.onPause();
    }

    public void onResume()
    {
        super.onResume();
    }
}
