package com.example.song.timerapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Song on 2017-03-27.
 */
public class DBCreate extends SQLiteOpenHelper {

    private Context context;

    public DBCreate(Context context){
        super(context, "Alarm", null, 1);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE AlARM(" +
                "Id int," +
                "Time int default 0, "+
                "Min int default 0, "+
                "Memo text)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS AlARM";
        db.execSQL(sql);
        onCreate(db);

    }

    public void insert(int Time, int Min, String Memo) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO AlARM VALUES(null, '" + Time + "', " + Min + ", '" + Memo + "');");
        db.close();
    }

    public void update(int Id, int Time, int Min) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정
        db.execSQL("UPDATE AlARM SET Id=" + Id + " WHERE Time='" + Time + "Min=" +Min +"';");
        db.close();
    }

    public void delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM AlARM WHERE Id='" + id + "';");
        db.close();
    }


    public String getResult() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM AlARM", null);
        while (cursor.moveToNext()) {
            result += cursor.getInt(1)
                    + " : "
                    + cursor.getInt(2)
                    + " |||"
                    + cursor.getString(3)
                    + "\n";

        }

        return result;
    }

    public List getCursor(){
        SQLiteDatabase db = getReadableDatabase();
        List alarmData = new ArrayList();
        Cursor cursor = db.rawQuery("SELECT * FROM AlARM", null);
        Alarm alarm = null;

        while(cursor.moveToNext()){
            alarm = new Alarm();
            alarm.set_Id(cursor.getInt(0));
            alarm.setTime(cursor.getInt(1));
            alarm.setMin(cursor.getInt(2));
            alarm.setMemo(cursor.getString(3));

            alarmData.add(alarm);
        }


        return alarmData;
    }


}
