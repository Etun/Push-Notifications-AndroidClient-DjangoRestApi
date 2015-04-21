package com.longtailapps.tastypieclient;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


public class GcmMessageHandler extends IntentService{
    public GcmMessageHandler(){
        super("GcmBroadcastReceiver");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        String title = extras.getString("title");
        String text = extras.getString("text");
        String date = extras.getString("date");
        Log.d("title", title+" t");
        Log.d("text", text+" t");
        Log.d("date", date+" d");

        Intent intentData = new Intent("data");
        intentData.putExtra("title", title);
        intentData.putExtra("text", text);
        intentData.putExtra("date", date);
        if(title!=null) {
            sendBroadcast(intentData);
        }
    }
}
