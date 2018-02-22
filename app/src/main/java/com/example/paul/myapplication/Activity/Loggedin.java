package com.example.paul.myapplication.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paul.myapplication.Asynctask.UpdateAsynctask;
import com.example.paul.myapplication.R;
import com.example.paul.myapplication.SharedPref;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Loggedin extends ActionBarActivity {
    TextView name,email,address ;
    ImageView image;
    Button update;
    EditText age;
    String TAG = "Loggedin";
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggedin);

        context=this;

        name = (TextView) findViewById(R.id.ename);
        email = (TextView) findViewById(R.id.email);
        address = (TextView) findViewById(R.id.eaddress);
        age= (EditText) findViewById(R.id.eage);

        final SharedPref temp = new SharedPref();

        image = (ImageView) findViewById(R.id.image);

        final String name1=temp.getName(context);
        final String pass1=temp.getPass(context);
        name.setText(name1);
        address.setText(temp.getAddress(context));
        email.setText(temp.getEmail(context));
        age.setText(temp.getAge(context));
        Log.i("Pic loc", temp.getPicture(context));
       // image.setImageBitmap(util.getBitmapFromURL(temp.getPicture(context)));
        String picture = temp.getPicture(context);
        String pic = picture.replaceAll("\\s+", "%20");
        Log.i("pic loc", "picture = " + picture);
        Log.i("pic loc", "pic = " + pic);
        new ImageLoadTask(pic,image).execute();
        update = (Button) findViewById(R.id.update);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String age1= age.getText().toString();
                new UpdateAsynctask(context).execute(name1,pass1,age1);
                temp.setAge(context,age1);
            }
        });

//        Log.i(TAG, "call asynctask");
//        new UpdateProfileAsynctask(context).execute(name1, pass1);

//        address.setText(temp.getAddress(context));
//        email.setText(temp.getEmail(context));
//        age.setText(temp.getAge(context));
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        SharedPref temp = new SharedPref();
//        address.setText(temp.getAddress(context));
//        email.setText(temp.getEmail(context));
//        age.setText(temp.getAge(context));
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_loggedin, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}



class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

    private String url;
    private ImageView imageView;

    public ImageLoadTask(String url, ImageView imageView) {
        this.url = url;
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        try {
            URL urlConnection = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlConnection
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (Exception e) {
            Log.e("pic loc", "ERROR : " + e.getLocalizedMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);

        if (result == null) {
            imageView.setImageResource(R.drawable.favicon);
        } else {
            imageView.setImageBitmap(result);
        }
    }

}
