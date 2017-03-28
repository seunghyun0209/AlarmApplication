package com.example.song.timerapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class Timer_Main extends AppCompatActivity {

    final ArrayList<ListViewItem> item = new ArrayList<>();
    static DBCreate dbHelper;
    final ListViewAdapter adapter = new ListViewAdapter(item);
    static final int REQ_ADD_CONTACT = 1 ;
    ListView listview;
    Button EditBt;
    Button DeleBt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer__main);

        DBList();

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
                int count = adapter.getCount(); //adapter에 있는 개수를 저장

                Intent intent = new Intent(Timer_Main.this, SelectTimerActivity.class); //시간을 설정하는 액티비티로 이동
                startActivityForResult(intent, REQ_ADD_CONTACT); //시간설정이 완료됨과 동시에 선택된 결과값을 가져옴
            }
            else if(v.getId() == R.id.Delebt){
                SparseBooleanArray checkitem = listview.getCheckedItemPositions();
                int count = adapter.getCount();

                Log.d("test","ErrorCheck");

                if(count > 0) {
                    for (int i = count - 1; i >= 0; i--) {
                        Log.d("test", "count : "+count+" checkitem"+checkitem.get(i));

                        if (checkitem.get(i)) {
                            Log.d("test", "크기"+checkitem.size()+"값"+checkitem.get(i)+" sdf" +checkitem.toString());
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
                int AddTime = 0, AddMin = 0;
                String memo;
                AddTime = intent.getIntExtra("Time", 0);
                AddMin = intent.getIntExtra("Min", 0);
                memo = intent.getStringExtra("Memo");
                TimeSave(AddTime,AddMin, memo);
                dbHelper.insert(AddTime, AddMin, memo);
                AlarmHATT alarm = new AlarmHATT(getApplicationContext(), AddTime, AddMin, memo);
                alarm.Alarm();

            }
        }
    }

    public void TimeSave(int time, int min, String m){
        String Text, memo;
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
        memo = m;
        adapter.addItem(Text,memo, true);
        adapter.notifyDataSetChanged();

    }

    public void DBList(){
        Log.d("Test", "Coming DBList Method");
        if(dbHelper == null) {
            Log.d("Test", "initalize of DataBase");
            dbHelper = new DBCreate(getApplicationContext());
        }
        else{
            Log.d("Test", "not initalize of DataBase");
            List DBdata = dbHelper.getCursor();
            Log.d("Test", "Reading Data of All");
            for (int i = 0; i < DBdata.size(); i++) {
                //Log.d("Test", "Read Data "+i);
                Alarm alarm = (Alarm) DBdata.get(i);
                TimeSave(alarm.getTime(), alarm.getMin(), alarm.getMemo());
            }
        }
    }

}
