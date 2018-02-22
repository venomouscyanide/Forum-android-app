package com.example.paul.myapplication.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.paul.myapplication.CustomAdapter.ForumAdapter;
import com.example.paul.myapplication.Models.Reply;
import com.example.paul.myapplication.R;
import com.example.paul.myapplication.SharedPref;

import java.util.ArrayList;
import java.util.List;

public class VideoGames1 extends ActionBarActivity {
    Context context;
    Button reply;
    String[] details=new String[3];
    String TAG="VideoGames1";
    TextView topic,submitter,section,time;
    SharedPref o=new SharedPref();
    ListView replylist;
    List<Reply> list=new ArrayList<Reply>();

    int length=0;
    String[] submitter1 = new String[100];
    String[] reply1=new String[100];
    String[] image1=new String[100];
    String[] time1=new String[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.activity_video_games1);

        Intent in= getIntent();
        details=in.getStringArrayExtra("details");
        length=in.getIntExtra("length", 0);
        submitter1=in.getStringArrayExtra("submitter");
        image1=in.getStringArrayExtra("image");
        time1=in.getStringArrayExtra("time");
        reply1=in.getStringArrayExtra("reply");

        topic= (TextView) findViewById(R.id.topic);
        submitter= (TextView) findViewById(R.id.submitter);
        time= (TextView) findViewById(R.id.time);
        section= (TextView) findViewById(R.id.section);
        reply= (Button) findViewById(R.id.replybutton);

        topic.setText("Topic : "+details[3]);
        submitter.setText("Submitted by : "+details[0]);
        section.setText(o.getSection(context) + " Section");
        time.setText("At Time : " + details[1]);

        if(details[4].equals("closed"))
        {
            reply.setVisibility(View.INVISIBLE);
        }

        Log.i(TAG, "Details" + details[0] + details[1] + details[2]);

        replylist= (ListView) findViewById(R.id.replylist);

        Log.i(TAG, "length = " + length);
        for(int j=0;j<length;j++)
        {
            Log.i(TAG, "adding " + j + "to list");
            list.add(new Reply(reply1[j],submitter1[j],image1[j],time1[j]));
        }

        replylist.setAdapter(new ForumAdapter(context, R.layout.replylist,list));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_video_games1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
