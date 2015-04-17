package com.longtailapps.tastypieclient;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class Requests {

    static final int TIMEOUT = 10000;

    public static String sendPostRequest(String url, String urlParameters) throws Exception{

        URL urlObj=new URL(url);
        HttpURLConnection connection=(HttpURLConnection)urlObj.openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(TIMEOUT);
        connection.setDoInput(true);

        DataOutputStream dataOutputStream=new DataOutputStream(connection.getOutputStream());

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(dataOutputStream, "UTF-8"));
        writer.write(urlParameters);
        writer.close();
        dataOutputStream.flush();
        dataOutputStream.close();

        int responseCode = connection.getResponseCode();
        Log.d("responseCode", responseCode+"  responseCode");
        if(!(responseCode==200 || responseCode == 204 || responseCode == 201)){
            throw  new Exception("Server not response");
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine=bufferedReader.readLine())!=null){
            response.append(inputLine);
        }
        bufferedReader.close();
        return response.toString();
    }


    public  static void sendGetRequest(){

    }

}
