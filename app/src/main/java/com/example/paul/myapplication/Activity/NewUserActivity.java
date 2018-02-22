package com.example.paul.myapplication.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.paul.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class NewUserActivity extends ActionBarActivity {
    EditText name,email,password,address;
    Spinner age;
    Button signup;
    Context context;
    RadioButton male,female;
    RadioGroup gender;

    String age1,gender1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        context=this;

        name= (EditText) findViewById(R.id.name);
        email= (EditText) findViewById(R.id.email);
        password= (EditText) findViewById(R.id.password);
        address= (EditText) findViewById(R.id.address);
        signup = (Button) findViewById(R.id.signup);

        male= (RadioButton) findViewById(R.id.male);
        female= (RadioButton) findViewById(R.id.female);
        gender= (RadioGroup) findViewById(R.id.gender);

        age= (Spinner) findViewById(R.id.age);
        List<String> list=new ArrayList<String>();

        for(int i =18;i<=80;i++)
        {
            list.add("" + i);
        }

        ArrayAdapter<String> ob= new ArrayAdapter<String>(context,android.R.layout.simple_spinner_dropdown_item,list);

        age.setAdapter(ob);

        age.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                age1 = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equals("")||password.getText().toString().equals("")||email.getText().toString().equals("")||address.getText().toString().equals(""))
                {
                    Toast.makeText(context,"Cannot be blank",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(male.isChecked())
                    {
                        gender1=male.getText().toString();
                        female.setChecked(false);
                        //Toast.makeText(context,"male",Toast.LENGTH_SHORT).show();
                    }
                    else if(female.isChecked())
                    {
                        gender1=female.getText().toString();
                        male.setChecked(false);
                        //Toast.makeText(context,"female",Toast.LENGTH_SHORT).show();
                    }

                    Toast.makeText(context,"Successfully created new user",Toast.LENGTH_SHORT).show();
                   // Toast.makeText(context,"Gender="+gender1,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context,MainActivity.class);
                    startActivity(intent);
                }
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_user, menu);
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
