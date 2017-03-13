package com.example.song.timerapp;

import android.widget.Switch;

/**
 * Created by Song on 2017-03-09.
 */

public class ListViewItem {

    private String Title;
    private String Desc;
    private boolean swit;

    public ListViewItem(String title, String desc, boolean sw){
        this.Title = title;
        this.Desc = desc;
        this.swit = sw;
    }

    public void setTitle(String title){
        this.Title = title;
    }

    public void setDesc(String desc){
        this.Desc = desc;
    }

    public String getTitle(){
        return this.Title;
    }

    public String getDesc(){
        return this.Desc;
    }

    public void setSw(boolean s){
        this.swit = s;
    }

    public boolean getSw(){
        return this.swit;
    }

}
