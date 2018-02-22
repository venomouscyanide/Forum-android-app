package com.example.paul.myapplication.Asynctask;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.paul.myapplication.Activity.VideoGames;
import com.example.paul.myapplication.SharedPref;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Paul on 01-06-2017.
 */
public class VideoGamesAsynctask extends AsyncTask {
    String TAG="VideoGamesAsynctask";
    SharedPref o = new SharedPref();
    Context parent;
    String[] open = new String[100];
    String[] closed = new String[100];

    public VideoGamesAsynctask(Context context) {
        parent=context;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        String section=o.getSection(parent);


        String link ="http://192.168.137.1/androidvideogames.php?section="+section;
        link= link.replaceAll("\\s+","%20");

//                reg.add(new BasicNameValuePair("uname", name));
//                reg.add(new BasicNameValuePair("pass", pass));

        try {
            //URL url = new URL(link);
            HttpClient client= new DefaultHttpClient();
            HttpGet request= new HttpGet();
            request.setURI(new URI(link));
            HttpResponse response = client.execute(request);

            String r = EntityUtils.toString(response.getEntity());

            Log.i(TAG, "receiveddata=" + r);

            JSONObject jo = new JSONObject(r);
            Log.i(TAG, "jo = " + jo);

            //JSONArray receivedData = EntityUtils.toByteArray(response.getEntity());

            //contents = new String[100];


            Log.i(TAG, "Length = " + jo.getString("Length"));
            int l = Integer.parseInt(jo.getString("Length"));
            Log.i(TAG, "l = " + l);
            int o=0,c=0;

            for(int i=0;i < l; i++){
                //jo = ja.getJSONObject(i);

                JSONObject job = new JSONObject();
                job = jo.getJSONObject("" + i);

                //JSONObject job = new JSONObject("" + i);
                Log.i(TAG,"Json data :");
                Log.i(TAG,"job.getString(Topic) : " + job.getString("Topic"));

                if(job.getInt("Closed") == 0)
                {
                    closed[c]=job.getString("Topic");
                    Log.i(TAG, "closed[" + c + "] = " + closed[c] + " : closed");
                    c++;
                }
                else
                {
                    open[o]=job.getString("Topic");
                    Log.i(TAG, "open[" + o + "] = " + open[o] + " : open");
                    o++;
                }


                //contents[i - 1]=job.getString("Section");

            }



            return r;
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
        } catch (JSONException e) {
            e.printStackTrace();
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
        super.onPostExecute(o);

        Intent intent= new Intent(parent,VideoGames.class);
        intent.putExtra("open",open);
        intent.putExtra("closed",closed);
        parent.startActivity(intent);

    }
}
