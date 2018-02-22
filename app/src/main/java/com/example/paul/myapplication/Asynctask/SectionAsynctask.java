package com.example.paul.myapplication.Asynctask;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.paul.myapplication.Activity.SectionActivity;

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
 * Created by Paul on 01-06-2017.
 */
public class SectionAsynctask extends AsyncTask {
    Context parent;
    //String name,pass,email,age,add,picture;
    String TAG="SectionAsynctask";
    String[] contents = new String[100];

    public SectionAsynctask(Context context) {
        parent=context;
    }

    @Override
    protected Object doInBackground(Object[] params) {

        Log.i(TAG, "started");
        //name = (String) params[0];
        //pass = (String) params[1];
        String link ="http://192.168.137.1/androidsection.php";

        try {
            HttpClient client= new DefaultHttpClient();
            HttpGet request= new HttpGet();
            request.setURI(new URI(link));
            HttpResponse response = client.execute(request);

            //            String receivedData = EntityUtils.toString(response.getEntity());

            String r = EntityUtils.toString(response.getEntity());
            Log.i(TAG,"r = " + r);

            JSONArray ja = new JSONArray();
            JSONObject jo = new JSONObject(r);
            Log.i(TAG, "jo = " + jo);

            //JSONArray receivedData = EntityUtils.toByteArray(response.getEntity());

            //contents = new String[100];


            Log.i(TAG, "Length = " + jo.getString("Length"));
            int l = Integer.parseInt(jo.getString("Length"));
            Log.i(TAG, "l = " + l);

            for(int i=1;i <= l; i++){
                //jo = ja.getJSONObject(i);

                JSONObject job = new JSONObject();
                job = jo.getJSONObject("" + i);

                //JSONObject job = new JSONObject("" + i);
                Log.i(TAG,"Json data :");
                Log.i(TAG,"job.getString(Section) : " + job.getString("Section"));

                contents[i - 1]=job.getString("Section");
                //Log.i(TAG, "contents[" + i + "] = " + contents[i]);
            }

//            Log.i(TAG, "receiveddata=" + receivedData);
//            return receivedData;
//            //}

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

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        Intent in = new Intent(parent, SectionActivity.class);
        in.putExtra("strings", contents);
        parent.startActivity(in);

    }
}
