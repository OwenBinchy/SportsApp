package com.example.sportsapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

//public abstract class JSONfunctions extends AsyncTask<String, Integer, Long> {

//protected Long doInBackground(String url) {
//public static JSONObject getJSONfromURL(String url) {


public class JSONfunctions extends AsyncTask<String, Integer, Long> {
	
	private static JSONObject Response = null;
	private static JSONObject Request  = null;
	
	
	@Override
	protected Long doInBackground(String... urls) {
		InputStream is = null;
	    String result = "";
	    JSONObject jArray = null;

	    // Download JSON data from URL
	    try {
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost(urls[0]);
	        
//	        StringEntity params =new StringEntity("details={\"name\":\"myname\",\"age\":\"20\"} ");
//	        StringEntity params =new StringEntity("{\"userID\":\"12345678\"} ");
	        
	        Log.d("log_tag","Attempting to http...");
	        
	        StringEntity params =new StringEntity(Request.toString());
	        httppost.addHeader("content-type", "application/x-www-form-urlencoded");
	        httppost.setEntity(params);
	        Log.d("log_tag","Params: " + params);
	        
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

	    Log.d("log_tag","Received object is: " + jArray.toString());
	    Response = jArray;
		return null;
	}	
	
	public static JSONObject getResponseObject(){
		// TODO Make this better... use the onPostExecute() function..
		JSONObject dummy = Response;
		Response = null;
		return dummy;
	}
	
	public static void setRequestObject(JSONObject request){
		Request = request;
		Log.d("log_tag","Request in class: " + Request.toString());
	}
	
	protected void onProgressUpdate(Integer... progress) {
//        setProgressPercent(progress[0]);
    }

    protected void onPostExecute(Long result) {
//        showDialog("Downloaded " + result + " bytes");
    	
    }
	
	
	}