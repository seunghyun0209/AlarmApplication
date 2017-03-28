package com.example.song.timerapp;

/**
 * Created by Song on 2017-03-28.
 */

public class Alarm {
    int _id;
    int time;
    int min;
    String memo;

    void set_Id(int id){
        this._id = id;
    }
    void setTime(int t){
        this.time = t;
    }
    void setMin(int m){
        this.min = m;
    }
    void setMemo(String m){
        this.memo = m;
    }
    int get_id(){
        return this._id;
    }
    int getTime(){
        return this.time;
    }
    int getMin(){
        return this.min;
    }
    String getMemo(){
        return this.memo;
    }

}
