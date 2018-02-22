package com.example.paul.myapplication.Asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Objects;

/**
 * Created by Paul on 31-05-2017.
 */
public class UpdateAsynctask extends AsyncTask {
    String name,age,pass;
    Context parent;
    String message;
    JSONObject jo=new JSONObject();
    JSONArray ja=new JSONArray();
    OutputStream os = null;
    InputStream is = null;
    String queryResult = "";
    HttpURLConnection conn = null;
    public UpdateAsynctask(Context context) {
        parent = context;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        name= (String) params[0];
        pass = (String) params[1];
        age= (String) params[2];


        try {
            jo.put("name",name);
            jo.put("password", pass);
            jo.put("age", age);
            ja.put(jo);

            URL url = new URL("http://192.168.137.1/androidupdate.php");
             message = jo.toString();

            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout( 10000 /*milliseconds*/ );
            conn.setConnectTimeout( 15000 /* milliseconds */ );
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setFixedLengthStreamingMode(message.getBytes().length);

            //make some HTTP header nicety
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");

            //open
            conn.connect();

            //setup send
            os = new BufferedOutputStream(conn.getOutputStream());
            os.write(message.getBytes());
            //clean up
            os.flush();

            //do somehting with response
            is = conn.getInputStream();

            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayBuffer baf = new ByteArrayBuffer(50);
            int read = 0;
            int bufSize = 512;
            byte[] buffer = new byte[bufSize];
            while(true){
                read = bis.read(buffer);
                if(read==-1){
                    break;
                }
                baf.append(buffer, 0, read);
            }
            queryResult = new String(baf.toByteArray());






        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Toast.makeText(parent, "" + ja,Toast.LENGTH_SHORT).show();
        Log.i("Update1", is.toString() + "\n" + message + "\n" + queryResult);

        Toast.makeText(parent,queryResult,Toast.LENGTH_SHORT).show();

    }
}
