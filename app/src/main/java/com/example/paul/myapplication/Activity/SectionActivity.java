package com.example.paul.myapplication.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.paul.myapplication.Asynctask.SectionAsynctask;
import com.example.paul.myapplication.Asynctask.VideoGamesAsynctask;
import com.example.paul.myapplication.R;
import com.example.paul.myapplication.CustomAdapter.SectionAdapter;
import com.example.paul.myapplication.SharedPref;

import java.util.ArrayList;
import java.util.List;

public class SectionActivity extends ActionBarActivity {
    Context context;
    ListView listView;
    List<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);

        Intent in = getIntent();
        final String[] content = in.getStringArrayExtra("strings");

        listView= (ListView) findViewById(R.id.listview);

        SectionAsynctask sec = new SectionAsynctask(context);
        //sec.execute();

        for(int i = 0; content[i] != null; i++) {
            list.add(content[i]);
            Log.i("section", "content[" + i + "] = " + content[i]);
        }

//        list.add("Movies");
//        list.add("Games");
        SectionAdapter sa= new SectionAdapter(context,list);
        listView.setAdapter(sa);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new SharedPref().setSection(context,content[position]);
//                Intent intent= new Intent(context,VideoGames.class);
//                startActivity(intent);
                new VideoGamesAsynctask(context).execute();


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_section, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_section) {
            Intent in = new Intent(context,Loggedin.class);
            startActivity(in);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
