package com.example.paul.myapplication.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.paul.myapplication.Activity.SectionActivity;
import com.example.paul.myapplication.R;

import java.util.List;

/**
 * Created by Paul on 01-06-2017.
 */
public class SectionAdapter extends BaseAdapter {
    Context context;
    List<String> list;

    public SectionAdapter(Context context, List<String> l) {
        this.context = context;
        list =l;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list,parent,false);
        }

        TextView tv = (TextView) convertView.findViewById(R.id.list);
        tv.setText(list.get(position));
        return convertView;
    }
}
