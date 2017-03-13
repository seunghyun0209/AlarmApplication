package com.example.song.timerapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * Created by Song on 2017-03-09.
 */

public class SelectTimerActivity extends AppCompatActivity{

    TimePicker tp;
    Button Savebt;
    TextView test;
    CheckBox Mon,Tue, Wed,Thu, Fri, Sat, Sun;
    private int Time;
    private int min;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_timer);
        final Intent intent = new Intent();
        tp = (TimePicker) findViewById(R.id.timePicker);
        Savebt = (Button) findViewById(R.id.Savebt);
        test = (TextView) findViewById(R.id.test);
        Mon = (CheckBox) findViewById(R.id.CheckMon);
        Tue = (CheckBox) findViewById(R.id.CheckTue);
        Wed = (CheckBox) findViewById(R.id.CheckWed);
        Thu = (CheckBox) findViewById(R.id.CheckThur);
        Fri = (CheckBox) findViewById(R.id.CheckFri);
        Sat = (CheckBox) findViewById(R.id.CheckSat);
        Sun = (CheckBox) findViewById(R.id.CheckSun);

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Time = hourOfDay;
                min = minute;
                test.setText("바뀐 시간 값 : "+Time+" 바뀐 분 값 : "+min);
            }
        });

        Savebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("Time", Time);
                intent.putExtra("Min", min);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    private final View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.CheckMon){

            }
        }
    };
}
