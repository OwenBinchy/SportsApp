package com.example.sportsapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

//public abstract class JSONfunctions extends AsyncTask<String, Integer, Long> {

//protected Long doInBackground(String url) {
//public static JSONObject getJSONfromURL(String url) {


public class JSONfunctions extends AsyncTask<String, Integer, Long> {
	static JSONObject JArray;
	@Override
	protected Long doInBackground(String... urls) {
		// TODO Auto-generated method stub
		InputStream is = null;
	    String result = "";
	    JSONObject jArray = null;

	    // Download JSON data from URL
	    try {
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost(urls[0]);
	        
	     // Request parameters and other properties.
//	        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
//	        params.add(new BasicNameValuePair("userID", "12345678"));
//	        params.add(new BasicNameValuePair("param-2", "Hello!"));
//	        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
	        
	     // Request parameters and other properties.
	        List<NameValuePair> params = new ArrayList<NameValuePair>();
	        params.add(new BasicNameValuePair("userID", "12345678"));
	        try {
	            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
	        } catch (UnsupportedEncodingException e) {
	            // writing error to Log
	            e.printStackTrace();
	        }
	        
	        HttpResponse response = httpclient.execute(httppost);
	        HttpEntity entity = response.getEntity();
	        is = entity.getContent();

	    } catch (Exception e) {
	        Log.e("log_tag", "Error in http connection " + e.toString());
	    }

	    // Convert response to string
	    try {
	        BufferedReader reader = new BufferedReader(new InputStreamReader(
	                is, "iso-8859-1"), 8);
	        StringBuilder sb = new StringBuilder();
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	            sb.append(line + "\n");
	        }
	        is.close();
	        result = sb.toString();
	    } catch (Exception e) {
	        Log.e("log_tag", "Error converting result " + e.toString());
	    }

	    try {

	        jArray = new JSONObject(result);
	    } catch (JSONException e) {
	        Log.e("log_tag", "Error parsing data " + e.toString());
	    }
//	    return jArray;
	    Log.d("log_tag","Reached end of thing...");
	    Log.d("log_tag","Message is: " + jArray.toString());
	    JArray = jArray;
		return null;
	}	
	
	public static JSONObject getJSONobject(){
		return JArray;
	}
	
	}