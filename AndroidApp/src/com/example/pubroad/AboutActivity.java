package com.example.pubroad;

import com.example.pubroad.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
         setContentView(R.layout.about);
         Typeface brixton_light = Typeface.createFromAsset(getAssets(), "fonts/brixton_light.otf");
 		Typeface brixton_book = Typeface.createFromAsset(getAssets(), "fonts/brixton_book.otf");
 			{
 			
 			TextView tv = (TextView) findViewById(R.id.about_about);
 	        tv.setTypeface(brixton_book);
 	        
 	        tv = (TextView) findViewById(R.id.msg1_about);
 	        tv.setTypeface(brixton_book);
 	        
 	        tv = (TextView) findViewById(R.id.msg2_about);
 	        tv.setTypeface(brixton_book);
 	        
 	        tv = (TextView) findViewById(R.id.msg3_about);
 	        tv.setTypeface(brixton_book);
 	        
 	        tv = (TextView) findViewById(R.id.msg4_about);
 	        tv.setTypeface(brixton_book);
 	        
 	        tv = (TextView) findViewById(R.id.msg5_about);
 	        tv.setTypeface(brixton_light);
 	        
 	        tv = (TextView) findViewById(R.id.names_about);
 	        tv.setTypeface(brixton_light);
 	        
 	        tv = (TextView) findViewById(R.id.title_header);
 	        tv.setTextColor(Color.rgb(255,255,255)); 
 	        tv.setTypeface(brixton_book);
 	        
 	       	}
 			
 			final Button back_about_button = (Button) findViewById(R.id.back_about);
 			back_about_button.setOnClickListener(new OnClickListener()
			{
				public void onClick(View v)
				{
				 AboutActivity.this.finish();
				}
				}
				);
 			
    } 
}
