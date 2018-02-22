package com.example.paul.myapplication.Asynctask;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.paul.myapplication.Activity.Loggedin;
import com.example.paul.myapplication.Activity.MainActivity;
import com.example.paul.myapplication.Activity.SectionActivity;
import com.example.paul.myapplication.SharedPref;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Paul on 30-05-2017.
 */
public class SigninAsynctask extends AsyncTask
{
    MainActivity main = new MainActivity();
    Context parent;
    String  TAG = "SigninAsynctask";
    String name,pass;
    public SigninAsynctask(Context context) {
        parent = context;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        name = (String) params[0];
        pass = (String) params[1];
        String link ="http://192.168.137.1/android.php?username="+name+"&password="+pass;


//                reg.add(new BasicNameValuePair("uname", name));
//                reg.add(new BasicNameValuePair("pass", pass));

        try {
            //URL url = new URL(link);
            HttpClient client= new DefaultHttpClient();
            HttpGet request= new HttpGet();
            request.setURI(new URI(link));
            HttpResponse response = client.execute(request);
            //BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

//                    StringBuffer sb= new StringBuffer("");
//                    String line="";



//                    if (response.getStatusLine().getStatusCode() != 200) {
//                        Log.e("connectivity","Invalid status code" + response.getStatusLine().getStatusCode());
//                    }
//                    else {
            String receivedData = EntityUtils.toString(response.getEntity());

            Log.i(TAG, "receiveddata=" + receivedData);

            SharedPref shared = new SharedPref();
            shared.setName(parent, name);
            shared.setPass(parent, pass);


            return receivedData;
            //}

        } catch (ClientProtocolException e) {
            e.printStackTrace();
            Log.e(TAG, e.getLocalizedMessage());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.e(TAG, e.getLocalizedMessage());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, e.getLocalizedMessage());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            Log.e(TAG, e.getLocalizedMessage());
        }

//                if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
//                    Toast.makeText(MainActivity.this, "Logged in !", Toast.LENGTH_SHORT).show();
//                } else
//
//                {
//                    Toast.makeText(MainActivity.this, "Log in error", Toast.LENGTH_SHORT).show();
//                }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        String receivedData = (String) o;
        SharedPref shared = new SharedPref();

        if (receivedData.contains("Success")) {
            Toast.makeText(parent, "Logged in !" + receivedData, Toast.LENGTH_SHORT).show();
            shared.setFlag(parent, "Success");

            try {
//                Intent intent = new Intent(parent, Loggedin.class);
//                startActivity(main,intent,null);
                Log.i(TAG, "call asynctask");
                new UpdateProfileAsynctask(parent).execute(name, pass);
                new SectionAsynctask(parent).execute();

//                parent.startActivity(new Intent(parent, SectionActivity.class));
            } catch (Exception e) {
                Log.e(TAG, e.getLocalizedMessage());
            }

//            SharedPref shared = new SharedPref();
//            shared.setName(parent, name);
//            shared.setPass(parent, pass);

        }
        else {
            Toast.makeText(parent, "Log in error" + receivedData, Toast.LENGTH_SHORT).show();
            shared.setFlag(parent, "Failure");
        }

        super.onPostExecute(o);
    }
}