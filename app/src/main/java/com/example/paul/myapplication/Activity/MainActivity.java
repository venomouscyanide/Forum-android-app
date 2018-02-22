package com.example.paul.myapplication.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.paul.myapplication.Asynctask.SigninAsynctask;
import com.example.paul.myapplication.R;
import com.example.paul.myapplication.SharedPref;

public class MainActivity extends ActionBarActivity {

    //Button clear= (Button) findViewById(R.id.clear);
    EditText username,password;
    Context context;
    TextView newuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        username= (EditText) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.password);
        Button submit= (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=username.getText().toString();
                String pass=password.getText().toString();

                SigninAsynctask sign= (SigninAsynctask) new SigninAsynctask(context).execute(name, pass);


            }
        });

        newuser = (TextView) findViewById(R.id.newuser);

        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,NewUserActivity.class);
                startActivity(intent);
            }
        });
    }


    public void clearb(View v)
    {
        username.setText("");
        password.setText("");
    }
}
