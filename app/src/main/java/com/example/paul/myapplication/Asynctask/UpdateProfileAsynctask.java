package com.example.paul.myapplication.Asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.paul.myapplication.SharedPref;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Paul on 30-05-2017.
 */
public class UpdateProfileAsynctask extends AsyncTask{

    Context parent;
    String name,pass,email,age,add,picture;
    String TAG="UpdateProfileAsynctask";

    public UpdateProfileAsynctask(Context context) {
        parent=context;
    }

    @Override
    protected Object doInBackground(Object[] params) {

        Log.i(TAG, "started");
        name = (String) params[0];
        pass = (String) params[1];
        String link ="http://192.168.137.1/androidprofile.php?username="+name+"&password="+pass;


//                reg.add(new BasicNameValuePair("uname", name));
//                reg.add(new BasicNameValuePair("pass", pass));

        try {
            //URL url = new URL(link);
            HttpClient client= new DefaultHttpClient();
            HttpGet request= new HttpGet();
            request.setURI(new URI(link));
            HttpResponse response = client.execute(request);

        //            String receivedData = EntityUtils.toString(response.getEntity());


            String r = EntityUtils.toString(response.getEntity());
            Log.i(TAG,"r = " + r);

            JSONArray ja = new JSONArray(r);
            JSONObject jo = new JSONObject();

            jo = ja.getJSONObject(0);
            //JSONArray receivedData = EntityUtils.toByteArray(response.getEntity());

            email = jo.getString("Email");
            age = jo.getString("Age");
            add = jo.getString("Address");
            picture = jo.getString("Picture");


//            Log.i(TAG, "receiveddata=" + receivedData);
//            return receivedData;
//            //}

            SharedPref i = new SharedPref();
            i.setAddress(parent, add);
            i.setEmail(parent, email);
            i.setAge(parent, age);
            i.setPicture(parent,"http://192.168.137.1/"+picture);

            return "success";

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
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "fail";
    }
}
