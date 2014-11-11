package com.example.sportsapp;

import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {
	
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	JSONObject jsonobject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
//    	jsonobject = JSONfunctions.getJSONfromURL("http://csiserver.ucd.ie/~09333541/sportsapp/profiles/checkProfileExists.php");
//        jsonobject = new JSONObject().put("JSON", "Hello, World!");
//        myString = new JSONObject().put("JSON", "Hello, World!").toString()
//        try {
//			jsonobject = new JSONObject().put("MESSAGE", "Hello, World!");
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        jsonobject = JSONfunctions.getJSONfromURL("http://csiserver.ucd.ie/~09333541/sportsapp/profiles/checkProfileExists.php");
        
        new JSONfunctions().execute("http://csiserver.ucd.ie/~09333541/sportsapp/profiles/checkProfileExists.php");
    }

    public void sendMessage(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	String message = editText.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }
    
    public void login(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	EditText username = (EditText) findViewById(R.id.username);
    	EditText password = (EditText) findViewById(R.id.password);
	
    	String message = username.getText().toString();

    	JSONObject jsonobject;
    	jsonobject = JSONfunctions.getJSONobject();
    	try {
			message = jsonobject.getString("message");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
        
        switch (item.getItemId()) {
        case R.id.action_search:
            openSearch();
            return true;
        case R.id.action_settings:
            openSettings();
            return true;
        default:
            return super.onOptionsItemSelected(item);
    }
    }
    

    private void openSettings() {
		// TODO Auto-generated method stub
		
	}


	private void openSearch() {
		// TODO Auto-generated method stub
		
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_actions, menu);
        return true;
    }
    
	
	
    
    
}
