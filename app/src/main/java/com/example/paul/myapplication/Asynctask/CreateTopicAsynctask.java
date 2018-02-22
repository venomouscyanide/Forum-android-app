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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Paul on 01-06-2017.
 */
public class CreateTopicAsynctask extends AsyncTask {
    SharedPref n= new SharedPref();
    Context parent;
    String TAG="CreateTopicAsynctask";

    public CreateTopicAsynctask(Context context) {
        parent = context;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        String topic= (String) params[0];
        String section=n.getSection(parent);
        String name=n.getName(parent);

        name=name.replaceAll("\\s+","%20");
        topic=topic.replaceAll("\\s+","%20");
        section=section.replaceAll("\\s","%20");

        Log.i(TAG,"section trimmed? "+section);

        String link ="http://192.168.137.1/androidcreate.php?topic="+topic+"&section="+section+"&name="+name;



        try {

            HttpClient client= new DefaultHttpClient();
            HttpGet request= new HttpGet();
            request.setURI(new URI(link));
            HttpResponse response = client.execute(request);

            String receivedData = EntityUtils.toString(response.getEntity());

            Log.i(TAG, "receiveddata=" + receivedData);

            return receivedData;


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


        return null;

    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        String flag=o.toString();
        if (flag.equals("Success"))
        {
            Toast.makeText(parent,"Topic will be reviewed by admin soon",Toast.LENGTH_SHORT).show();
        }
    }
}
