package com.example.pubroad;

import java.util.ArrayList;

import com.CallWebServices.WebServiceDetail;
import com.CallWebServices.getWebServiceLink;
import com.Object.WebServiceObject;
import com.Sqlite.WebServiceDataSource;
import com.example.pubroad.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new DownloadFilesTask().execute("");

		Typeface brixton_light = Typeface.createFromAsset(getAssets(), "fonts/brixton_light.otf");
		Typeface brixton_book = Typeface.createFromAsset(getAssets(), "fonts/brixton_book.otf");
		Typeface brixton_light_oblique = Typeface.createFromAsset(getAssets(), "fonts/brixton_light_oblique.otf");
		{
		TextView tv = (TextView) findViewById(R.id.search_home);
        tv.setTypeface(brixton_book);
        
        tv = (TextView) findViewById(R.id.history_home);
        tv.setTypeface(brixton_book);
        
        tv = (TextView) findViewById(R.id.about_home);
        tv.setTypeface(brixton_book);
        
        tv = (TextView) findViewById(R.id.title_header);
        tv.setTextColor(Color.rgb(255,255,255)); 
        tv.setTypeface(brixton_book);
       	}
	final Button search_button = (Button) findViewById(R.id.search);
	search_button.setOnClickListener(new OnClickListener() {
			
	public void onClick(View v) {
		Intent intent = new Intent(MainActivity.this, SearchActivity.class);
		startActivity(intent);
		}
	});

	final Button about_button = (Button) findViewById(R.id.about);
	about_button.setOnClickListener(new OnClickListener() {
		
	public void onClick(View v) {
		Intent intent = new Intent(MainActivity.this, AboutActivity.class);
		startActivity(intent);
		}
	});
	final Button history_button = (Button) findViewById(R.id.history);
	history_button.setOnClickListener(new OnClickListener() {
		
	public void onClick(View v) {
		Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
		startActivity(intent);
		}
	});
	}

	private class DownloadFilesTask extends AsyncTask<String, Integer, Long> {
	    
		@Override
		protected Long doInBackground(String... urls) {
	    	WebServiceDataSource datasource = new WebServiceDataSource(MainActivity.this);
	 	    datasource.open();
	 	    WebServiceObject ws= datasource.getWebServiceByID(1);
	 	   WebServiceObject ws1= datasource.getWebServiceByID(2);
	 	  Log.w("hhh", "Stored " +ws.getLink());
	 	 Log.w("hhh", "Stored " +ws1.getLink());
	 	    getWebServiceLink getws = new getWebServiceLink();
	 	    Log.w("hhh", "msg " +ws1.getId());
	 	    ArrayList<WebServiceObject> result = getws.getDetail();
	 	    if(ws.getLink()==null ||ws.getLink()==null || ws1.getLink()==null ||ws1.getLink()==null ){
	 	    	
	 	    	for (int i=0; i<result.size();i++){
	 	    		datasource.createWS(result.get(i).getId(), result.get(i).getLink(),result.get(i).getDateAdd());
	 	    	}
	 	    }
	 	    else {
	 	    	if(!ws.getDateAdd().equals(result.get(0).getDateAdd())){
	 	    		datasource.deleteWs(1);
	 	    		datasource.deleteWs(2);
	 	    		for (int i=0; i<result.size();i++){
	 		    		datasource.createWS(result.get(i).getId(), result.get(i).getLink(),result.get(i).getDateAdd());
	 		    	}
	 	    	}
	 	    
	 	    }
	 	    datasource.close();
	         return (long) 1;
	     }

	     protected void onProgressUpdate(Integer... progress) {
	         setProgress(progress[0]);
	     }

	     protected void onPostExecute(Long result) {
	         
	     }

		
	 }

}
