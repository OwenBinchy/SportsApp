package com.example.sportsapp;

import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class DisplayProfileActivity extends ActionBarActivity {
	
	String UserID 		= null;
	String FirstName	= null;
	String LastName 	= null;
	String Dob 			= null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_profile);
		
		// Get the message from the intent
	    Intent intent = getIntent();
	    
	    UserID 		= intent.getStringExtra(MainActivity.EXTRA_PREFIX + "userID");
//	    String firstName 	= intent.getStringExtra(MainActivity.EXTRA_PREFIX + "firstName");
//	    String lastName 	= intent.getStringExtra(MainActivity.EXTRA_PREFIX + "lastName");
//	    String dob			= intent.getStringExtra(MainActivity.EXTRA_PREFIX + "dob");

    	TextView text_userID 	= (TextView) findViewById(R.id.userID);
    	TextView text_firstName = (TextView) findViewById(R.id.firstName);
    	TextView text_lastName 	= (TextView) findViewById(R.id.lastName);
    	TextView text_dob 		= (TextView) findViewById(R.id.dob);

    	if(getProfileData()){
	    	
	    	text_userID.setText(UserID);
	    	text_firstName.setText(FirstName);
	    	text_lastName.setText(LastName);
	    	text_dob.setText(Dob);
    	}else {
    		// TODO user profile fail...
    	}
	    

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // If your minSdkVersion is 11 or higher, instead use:
        // getActionBar().setDisplayHomeAsUpEnabled(true);

		
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_activity_actions, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	public boolean getProfileData() {
    	
    	String message 		= null;
    	int success 		= 0;
    	
    	JSONObject request = new JSONObject();
    	 
    	try {
			request.put("userID", UserID);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	Log.d("log_tag","Request: " + request.toString());
    	
    	JSONfunctions.setRequestObject(request);
    	
    	new JSONfunctions().execute("http://csiserver.ucd.ie/~09333541/sportsapp/profiles/checkProfileExists.php");

    	JSONObject response = null;
    	while (response == null){
    		response = JSONfunctions.getResponseObject();
    	}
    	
    	try {
    		success 	= response.getInt("success");
    		message 	= response.getString("message");
    	} catch (JSONException e) {
    		e.printStackTrace();
    	}
    	
    	Log.d("log_tag","Message: " + message);
    	
    	if(success == 1) // User profile exists, get data
    	{
    		try {
			FirstName 	= response.getString("firstName");
			LastName 	= response.getString("lastName");
			Dob		 	= response.getString("dob");
    		} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}else{
    		// TODO if user profile doesn't exist.
    		return false;
    	}
		return true;
	}
	
	public void editProfile() {
	    // Do nothing...
	    
	}
}
