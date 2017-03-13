package com.example.song.timerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;

import java.util.ArrayList;

public class Timer_Main extends AppCompatActivity {

    final ArrayList<ListViewItem> item = new ArrayList<>();
    ListView listview;
    final ListViewAdapter adapter = new ListViewAdapter(item);
    Button EditBt;
    Button DeleBt;

    static final int REQ_ADD_CONTACT = 1 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer__main);




        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);
        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        EditBt = (Button) findViewById(R.id.Editbt);
        DeleBt = (Button) findViewById(R.id.Delebt);
        EditBt.setOnClickListener(listener);
        DeleBt.setOnClickListener(listener);
       // Sw = (Switch) findViewById(R.id.switchbt1);




    }


    private final View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.Editbt){
                int count = adapter.getCount();

                Intent intent = new Intent(Timer_Main.this, SelectTimerActivity.class);
                startActivityForResult(intent, REQ_ADD_CONTACT);
               // adapter.addItem(count+"번째 알람", "추가");
                //adapter.notifyDataSetChanged();

            }
            else if(v.getId() == R.id.Delebt){
                SparseBooleanArray checkitem = listview.getCheckedItemPositions();
                int count = adapter.getCount();


                Log.d("test","ErrorCheck");

                if(count > 0) {
                    for (int i = count - 1; i >= 0; i--) {
                        Log.d("test", "count : "+count+" checkitem"+checkitem.get(i));

                        if (checkitem.get(i)) {
                            Log.d("test", "크기"+checkitem.size()+"값"+checkitem.get(i));
                            adapter.deletItem(i);

                        }
                    }
                }
                listview.clearChoices();
                adapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQ_ADD_CONTACT) {
            if(resultCode == RESULT_OK) {
                int AddTime = 0;
                int AddMin = 0;
                AddTime = intent.getIntExtra("Time", 0);
                AddMin = intent.getIntExtra("Min", 0);
                TimeSave(AddTime,AddMin);


            }
        }
    }

    public void TimeSave(int time, int min){
        String Text;
        String memo;
        int atime, amin;

        if(time>12){
            atime = time - 12;
            amin = min;
            Text = "오후  "+atime+" : "+amin;
        }
        else{
            atime = time;
            amin = min;
            Text = "오전  "+atime+" : "+amin;
        }
        memo = "데이터메모";
        adapter.addItem(Text,memo);
        adapter.notifyDataSetChanged();
    }



}
