package com.example.paul.myapplication.Asynctask;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.paul.myapplication.Activity.VideoGames1;
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
 * Created by Paul on 02-06-2017.
 */
public class VideoGamesTopicAsynctask extends AsyncTask{
    int i;
    Context parent;
    String topic,section;
    SharedPref o= new SharedPref();
    String TAG= "VideoGamesTopicAsynctask";
    String[] details=new String[5];
    String flag;
    //String[][] replylist= new String[100][5];
    String[] submitter = new String[100];
    String[] reply=new String[100];
    String[] image=new String[100];
    String[] time=new String[100];

    public VideoGamesTopicAsynctask(Context context) {
        parent=context;
    }

    @Override
    protected Object doInBackground(Object[] params) {

        topic= (String) params[0];
        flag= (String) params[1];

        section=o.getSection(parent);

        section=section.replaceAll("\\s+","%20");
        String topic1=topic.replaceAll("\\s+","%20");

        String link ="http://192.168.137.1/androidforum.php?section="+section+"&topic="+topic1;
        Log.i(TAG,"Link :"+link);

        try {
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

            JSONArray ja=new JSONArray(receivedData);
            JSONObject jo=new JSONObject();
            jo=ja.getJSONObject(0);

            details[0]=jo.getString("Submitter");
            details[1]=jo.getString("Time");
            details[2]=jo.getString("Image");
            details[3]=topic;
            details[4]=flag;

            for( i=0;ja.getJSONObject(i+1)!=null;i++)
            {

                jo=ja.getJSONObject(i + 1);

                if (jo.has("Length")) {
                    Log.i(TAG, "BREAK at " + i);
                    break;
                }
//                    if(jo.getString("Length")!=null) {
//                        break;
//                    }
                reply[i]=jo.getString("Reply");
                submitter[i]=jo.getString("Submitter");
                image[i]=jo.getString("Image");
                time[i]=jo.getString("Time");

                Log.i(TAG,"reply[" + i + "] = "+reply[i]);
                Log.i(TAG,"submitter[" + i + "] = "+submitter[i]);
                Log.i(TAG,"image[" + i + "] = "+image[i]);
                Log.i(TAG,"time[" + i + "] = "+time[i]);
            }


            return "successfull";


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
            Log.e(TAG, e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {

        super.onPostExecute(o);
        Intent intent=new Intent(parent,VideoGames1.class);
        intent.putExtra("details",details);
        intent.putExtra("reply",reply);
        intent.putExtra("time",time);
        intent.putExtra("submitter",submitter);
        intent.putExtra("image",image);
        intent.putExtra("length",i);

        parent.startActivity(intent);

    }
}
