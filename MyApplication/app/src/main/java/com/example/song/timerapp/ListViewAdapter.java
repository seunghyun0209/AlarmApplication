package com.example.song.timerapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Song on 2017-03-09.
 */

public class ListViewAdapter extends BaseAdapter {

    private ArrayList<ListViewItem> listviewitemlist = new ArrayList<ListViewItem>();

    public ListViewAdapter(ArrayList<ListViewItem> list) {
        this.listviewitemlist = list;
    }

    @Override
    public int getCount() {
        return listviewitemlist.size();
    }

    @Override
    public Object getItem(int position) {
        return listviewitemlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_layout, parent, false);
        }

        TextView TitleView = (TextView) convertView.findViewById(R.id.textView1);
        TextView DescView = (TextView) convertView.findViewById(R.id.textView2);

        ListViewItem listviewitem = listviewitemlist.get(pos);

        TitleView.setText(listviewitem.getTitle());
        DescView.setText(listviewitem.getDesc());

        return convertView;
    }

    public void addItem(String t, String d){
        ListViewItem item = new ListViewItem(t, d);

        listviewitemlist.add(item);
    }

    public void deletItem(int position){
        ListViewItem item = listviewitemlist.get(position);
        Log.d("test",""+item.getTitle());
        listviewitemlist.remove(position);
    }

    public boolean getsw(int position){
        //boolean setting;
        ListViewItem item = listviewitemlist.get(position);
        return item.getSw();

    }

    public void setsw(int position){
        ListViewItem item = listviewitemlist.get(position);
        item.setSw(!item.getSw());
    }


}
