package com.longtailapps.tastypieclient;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class MainActivity extends Activity {

    private SharedPreferences sharedPreferences;
    private GoogleCloudMessaging gcm;
    private static String GCM_PROJECT_NUMBER = "769582780261";
    private String regid;
    private static final String url = "http://tastypieapi-info.herokuapp.com/device/gcm/?json";
    private TextView titleTextView;
    private TextView textTextView;
    private TextView dateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        registerReceiver(broadcastReceiver, new IntentFilter("data"));
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        getRegId();
        titleTextView = (TextView)findViewById(R.id.title);
        textTextView = (TextView)findViewById(R.id.text);
        dateTextView =(TextView)findViewById(R.id.date);
    }

    public void getRegId(){
        new AsyncTask<Void, Void, String>(){

            @Override
            protected String doInBackground(Void... params) {
                String msg;
                try{
                    if(gcm==null){
                        gcm= GoogleCloudMessaging.getInstance(getApplicationContext());
                    }
                    regid=gcm.register(GCM_PROJECT_NUMBER);
                    msg=regid;

                }catch(IOException ex){
                    msg="Error :"+ex.getMessage();
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg){
                Log.d("id", msg+" id");
                sharedPreferences.edit().putString("deviceID", msg).commit();
                new SendID().execute(url, msg);
            }

        }.execute(null, null, null);
    }


    class SendID extends AsyncTask<String, Void, Void>{

        String response = null;


        @Override
        protected Void doInBackground(String... params) {

            JSONObject jsonObject=new JSONObject();

            try {
                jsonObject.put("registration_id", params[1]);
                response = Requests.sendPostRequest(params[0], jsonObject.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }


    private BroadcastReceiver broadcastReceiver= new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String title = intent.getStringExtra("title");
            String text = intent.getStringExtra("text");
            String date = intent.getStringExtra("date");
            titleTextView.setText(title);
            textTextView.setText(text);
            dateTextView.setText(date);
            Log.d("received", "received"+ title);
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }
}
