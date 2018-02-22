package com.example.paul.myapplication.CustomAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paul.myapplication.Models.Reply;
import com.example.paul.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul on 02-06-2017.
 */
public class ForumAdapter extends BaseAdapter{

    String TAG = "ForumAdapter";
    Context con;
    List<Reply> reply= new ArrayList<Reply>() ;
    TextView replytopic,replysubmitter,replytime;
    ImageView replyimage;

    public ForumAdapter(Context context, int replylist, List<Reply> list) {
        con=context;
        reply=list;
    }

    @Override
    public int getCount() {
        return reply.size();
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

        Log.i(TAG, "inside getView");

        LayoutInflater inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.replylist,parent,false);

        replytopic= (TextView) convertView.findViewById(R.id.replytopic);
        replysubmitter= (TextView) convertView.findViewById(R.id.replysubmitter);
        replyimage= (ImageView) convertView.findViewById(R.id.replyimage);
        replytime= (TextView) convertView.findViewById(R.id.replytime);

        replytopic.setText(reply.get(position).getReplytopic());
        replytime.setText(reply.get(position).getTime());
        replysubmitter.setText(reply.get(position).getSubmitter());

        Log.i(TAG, "reply.get(" + position + ").getReplytopic() = " + reply.get(position).getReplytopic());

        return convertView;

    }
}
