package com.example.annuaire;




import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchActivity extends Activity {

    private static final ScheduledExecutorService worker =    Executors.newSingleThreadScheduledExecutor();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        View loader = (View) findViewById(R.id.loaderlayout);
        loader.setVisibility(View.INVISIBLE);
       // progress = (View)findViewById(R.id.progress);
		Typeface brixton_book = Typeface.createFromAsset(getAssets(), "fonts/brixton_book.otf");
		Typeface brixton_light_oblique = Typeface.createFromAsset(getAssets(), "fonts/brixton_light_oblique.otf");
			{
			TextView tv = (TextView) findViewById(R.id.destination_search);
	        tv.setTypeface(brixton_book);
	        
	        tv = (TextView) findViewById(R.id.editText1);
	        tv.setTypeface(brixton_light_oblique);
	        tv = (TextView) findViewById(R.id.editText2);
	        tv.setTypeface(brixton_light_oblique);
	        
	        tv = (TextView) findViewById(R.id.msg_warning);
	        tv.setTypeface(brixton_book);
	        
	        tv = (TextView) findViewById(R.id.exemple_search);
	        tv.setTypeface(brixton_book);
	        
	        tv = (TextView) findViewById(R.id.msg1_search);
	        tv.setTypeface(brixton_book);
	        
	        tv = (TextView) findViewById(R.id.msg2_search);
	        tv.setTypeface(brixton_book);
	        
	        tv = (TextView) findViewById(R.id.msg3_search);
	        tv.setTypeface(brixton_book);
	        
	        tv = (TextView) findViewById(R.id.msg4_search);
	        tv.setTypeface(brixton_book);
	        
	        tv = (TextView) findViewById(R.id.title_header);
	        tv.setTextColor(Color.rgb(255,255,255)); 
	        tv.setTypeface(brixton_book);
	        tv = (TextView) findViewById(R.id.rechercher);
	        tv.setTextColor(Color.rgb(255,255,255)); 
	        tv.setTypeface(brixton_book);
	       	}
			final Button back_search_button = (Button) findViewById(R.id.back_search);
			back_search_button.setOnClickListener(new OnClickListener()
			{
				public void onClick(View v)
				{
				 SearchActivity.this.finish();
				}
				}
				);
			final Button Recherche_button = (Button) findViewById(R.id.rechercher);
			Recherche_button.setOnClickListener(new OnClickListener() {

        public void onClick(View v) {
      	Intent intent = new Intent(SearchActivity.this, Liste.class);
      	EditText whoEditText = (EditText) findViewById(R.id.editText1);
      	String who = whoEditText.getText().toString();
      	EditText whereEditText = (EditText) findViewById(R.id.editText2);
      	String where = whereEditText.getText().toString();

      	if (who.matches("") || who.equals("Qui ? Quoi ?") || where.matches("") || where.equals("Ville, CP")) {


      		AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this);
            builder.setTitle("Attention")
            .setMessage("Vous devez renseigner tous les champs")
            .setCancelable(false)
            .setNegativeButton("Fermer",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        } 

      	
      	else{
      		View loader = (View) findViewById(R.id.loaderlayout);
            loader.setVisibility(View.VISIBLE);
            
            someMethod();
      	intent.putExtra("Who", who);
      	intent.putExtra("Where", where);
      	startActivity(intent);
      	}
      	
      	}
      });
			
    
    
    }
   private void someMethod() {
    	 
    	  Runnable task = new Runnable() {
    	    public void run() {
    	    	View loader = (View) findViewById(R.id.loaderlayout);
    	    	loader.setVisibility(View.INVISIBLE);
    	    }
    	  };
    	  worker.schedule(task, 1, TimeUnit.SECONDS);
    	}
   
}
