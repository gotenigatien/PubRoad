package com.example.pubroad;




import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.example.pubroad.R;

import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
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

	private static final ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		View loader = (View) findViewById(R.id.loaderlayout);
		loader.setVisibility(View.INVISIBLE);
		// progress = (View)findViewById(R.id.progress);
		Typeface brixton_book = Typeface.createFromAsset(getAssets(), "fonts/brixton_book.otf");
		Typeface brixton_light_oblique = Typeface.createFromAsset(getAssets(), "fonts/brixton_light_oblique.otf"); {
			TextView tv = (TextView) findViewById(R.id.destination_search);
			tv.setTypeface(brixton_book);

			tv = (TextView) findViewById(R.id.editText1);
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
			tv.setTextColor(Color.rgb(255, 255, 255));
			tv.setTypeface(brixton_book);
			tv = (TextView) findViewById(R.id.rechercher);
			tv.setTextColor(Color.rgb(255, 255, 255));
			tv.setTypeface(brixton_book);
		}
		final Button back_search_button = (Button) findViewById(R.id.back_search);
		back_search_button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				SearchActivity.this.finish();
			}
		});
		final Button Recherche_button = (Button) findViewById(R.id.rechercher);
		Recherche_button.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(SearchActivity.this, Liste.class);
				EditText whoEditText = (EditText) findViewById(R.id.editText1);
				String who = whoEditText.getText().toString();
				//EditText whereEditText = (EditText) findViewById(R.id.editText2);
				String where = "";
				//whereEditText.getText().toString();
				
				LocationManager lm = null;
				 boolean gps_enabled = false;
				    if(lm==null)
				        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
				    try{
				    gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
				    }catch(Exception ex){}
				    
				    if(gps_enabled){
				    	where=getCity_from_Location();
				if (who.matches("") || who.equals("Quoi ?") || where.matches("") || where.equals("Ville, CP")) {


					AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this);
					builder.setTitle("Attention")
						.setMessage("Vous devez renseigner tous les champs")
						.setCancelable(false)
						.setNegativeButton("Fermer", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
						}
					});
					AlertDialog alert = builder.create();
					alert.show();
				} else {
					View loader = (View) findViewById(R.id.loaderlayout);
					loader.setVisibility(View.VISIBLE);

					someMethod();
					intent.putExtra("Who", who);
					intent.putExtra("Where", where);
					startActivity(intent);
				}
			}
			else{
		    	AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this);
				builder.setTitle("Attention")
					.setMessage("Vous devez activer votre GPS")
					.setCancelable(false)
					.setNegativeButton("Fermer", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
				AlertDialog alert = builder.create();
				alert.show();
		    }

			}
		});



	}

    public String getCity_from_Location() {
        Location loc = getCurrentLocation();
        
        List < Address > addresses = null;
        Geocoder geocoder = new Geocoder(this.getApplicationContext(), Locale.getDefault());
         try {
            addresses = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Address add = addresses.get(0);
        String addressText = add.getLocality();
        return addressText;
    }
    public Location getCurrentLocation() {
        //----------------------------------------------------------------------------------------------

        LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);  
        getSystemService(Context.LOCATION_SERVICE);
        LocationProvider provider = locationManager.getProvider(LocationManager.GPS_PROVIDER);
        // Retrieve a list of location providers that have fine accuracy, no monetary cost, etc
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        List < String > lProviders = locationManager.getProviders(false);
        for (int i = 0; i < lProviders.size(); i++) {
            Log.d("LocationActivity", lProviders.get(i));
        }
        String providerName = locationManager.getBestProvider(criteria, true);

        // If no suitable provider is found, null is returned.
        if (providerName != null) {}
        Location location1 = locationManager.getLastKnownLocation(providerName);
        return location1;
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