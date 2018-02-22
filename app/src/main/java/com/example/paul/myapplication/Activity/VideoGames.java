package com.example.paul.myapplication.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.paul.myapplication.Asynctask.CreateTopicAsynctask;
import com.example.paul.myapplication.Asynctask.VideoGamesTopicAsynctask;
import com.example.paul.myapplication.R;
import com.example.paul.myapplication.CustomAdapter.SectionAdapter;
import com.example.paul.myapplication.SharedPref;

import java.util.ArrayList;
import java.util.List;

public class VideoGames extends ActionBarActivity {
    TextView heading;
    Context context;

    Button create;
    String m_Text;
    ListView open,closed;
    List<String> list = new ArrayList<String>();
    List<String> list1 = new ArrayList<String>();
    String TAG="VideoGames";
    String[] open1;
    String[] closed1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context= this;
        super.onCreate(savedInstanceState);
        SharedPref o = new SharedPref();
        String section= o.getSection(context);
        setContentView(R.layout.activity_video_games);
        heading= (TextView) findViewById(R.id.heading);
        heading.setText(section);
        Intent in=getIntent();
        open1=in.getStringArrayExtra("open");
        closed1=in.getStringArrayExtra("closed");

        open= (ListView) findViewById(R.id.open);
        closed=(ListView) findViewById(R.id.closed);


        for(int i=0;open1[i]!=null;i++)
        {list.add(open1[i]);
         Log.i(TAG, "Open values" + open1[i]);
        }

        if(open1[0]==null) {
            list.add("No Topics Yet");

        }
            SectionAdapter sa = new SectionAdapter(context, list);
            open.setAdapter(sa);

        if(closed1[0]==null)
        {
            list1.add("No Topics Yet");
        }

        for(int i=0;closed1[i]!=null;i++)
        {list1.add(closed1[i]);

        }
        SectionAdapter sb= new SectionAdapter(context,list1);
        closed.setAdapter(sb);

        create= (Button) findViewById(R.id.create);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Enter the topic");

                // Set up the input
                final EditText input = new EditText(context);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_Text = input.getText().toString();
                        if(m_Text!=null)
                        {
                            CreateTopicAsynctask a = (CreateTopicAsynctask) new CreateTopicAsynctask(context).execute(m_Text);
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();


                }

           });

        open.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //new SharedPref().setSection(context, open1[position]);

                new VideoGamesTopicAsynctask(context).execute(open1[position],"open");
                Log.i(TAG, "open1[" + position + "]= " + open1[position]);


            }
        });

        closed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //new SharedPref().setSection(context, open1[position]);

                new VideoGamesTopicAsynctask(context).execute(closed1[position],"closed");
                Log.i(TAG,"close1[" + position + "]= " + closed1[position]);


            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_video_games, menu);
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
