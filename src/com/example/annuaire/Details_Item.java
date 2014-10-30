package com.example.annuaire;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import com.CallWebServices.WebServiceDetail;

import com.Object.AnnuaireObjectDetail;
import com.Object.WebServiceObject;

import com.Sqlite.WebServiceDataSource;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;


import com.CallWebServices.WebServiceDetail;
import com.Object.AnnuaireObjectDetail;
import com.Object.WebServiceObject;
import com.Sqlite.WebServiceDataSource;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.AsyncTask;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
public class Details_Item extends FragmentActivity {
	private GoogleMap mMap;
	public AnnuaireObjectDetail arr= null;
	public WebServiceDataSource wsd=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details__item);
		wsd = new WebServiceDataSource(Details_Item.this);
		try {
			arr=new DownloadFilesTask().execute("").get();
			Button btn =((Button)findViewById(R.id.back_about));
			Log.w("ahsina", "arraylist "+arr.getID());
			btn.setOnClickListener(new OnClickListener()
			{
			public void onClick(View v)
			{
			 Details_Item.this.finish();
			}
			}
			);
Typeface brixton_book = Typeface.createFromAsset(getAssets(), "fonts/brixton_book.otf");
			
			TextView tv = (TextView) findViewById(R.id.title_header);
	        tv.setTypeface(brixton_book);
			
			Log.w("haha", "mama");
			TextView txtNom = (TextView) findViewById(R.id.txtNom);
			TextView txtAdresse = (TextView) findViewById(R.id.txtAdresse);
			TextView txtAdresse2 = (TextView) findViewById(R.id.txtAdresse2);
			TextView txtTel = (TextView) findViewById(R.id.txtTel);
			TextView txtSiteWeb = (TextView) findViewById(R.id.txtSiteWeb);
			TextView txtEmail = (TextView) findViewById(R.id.txtEmail);
			TextView txtDescription = (TextView) findViewById(R.id.txtDescription);
			TextView txtHoraire = (TextView) findViewById(R.id.txtHoraire);
			ImageView txtPhoto = (ImageView) findViewById(R.id.txtPhoto);
			
			Log.w("haha", "mama");
			
			txtNom.setText(arr.getNom());
			txtAdresse.setText(arr.getAdresse());
			txtAdresse2.setText(arr.getAdresse2());
			txtTel.setText(arr.getTel());
			txtSiteWeb.setText(arr.getSiteWeb());
			txtEmail.setText(arr.getEmail());
			txtDescription.setText(arr.getDescription());
			txtHoraire.setText(arr.getPlageHoraire());
			
			String fulladdress_gmap = new String (arr.getAdresse()+arr.getAdresse2());
			String name = new String(arr.getNom());
			setUpMapIfNeeded(fulladdress_gmap,name);
			WebServiceDataSource h = new WebServiceDataSource(Details_Item.this);
			h.open();
Log.w("ahsina", "detail item "+Integer.parseInt(getIntent().getExtras().getString("ID")));
Calendar c = Calendar.getInstance();
			h.AddHistory(Integer.parseInt(getIntent().getExtras().getString("ID")), txtNom.getText().toString(), txtAdresse.getText().toString(), txtAdresse2.getText().toString(),c.getTime().toString());
			h.close();
			Bitmap bm = null;
		    try {
		         URL aURL = new URL("http://annuaire.gzed.fr/services/Image/"+arr.getPhoto()); 
		         URLConnection conn = aURL.openConnection();
		         conn.connect(); 
		         InputStream is = conn.getInputStream();
		         BufferedInputStream bis = new BufferedInputStream(is);
		         bm = BitmapFactory.decodeStream(bis);
		         bis.close();
		         is.close(); 
		    }catch (Exception e) {
		        // TODO: handle exception
		}
		    txtPhoto.setImageBitmap(bm);
			//txtPhoto.setImageBitmap(arr.getPhoto());
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	protected void onResume(String add,String name) {
        super.onResume();
        setUpMapIfNeeded(add,name);
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not have been
     * completely destroyed during this process (it is likely that it would only be stopped or
     * paused), {@link #onCreate(Bundle)} may not be called again so we should call this method in
     * {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded(String add,String name) {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.gmap)).getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap(add,name);
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap(String add, String name) {
    	
    	String strAddress = new String(add);
    	
    	Geocoder coder = new Geocoder(this);
    	    	
    	    	List<Address> address = null;
    	    	try {
    				address = coder.getFromLocationName(strAddress,5);
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	        
    	        Address location = address.get(0);
    	        location.getLatitude();
    	        location.getLongitude();

    	        

    	        Location location1 = getCurrentLocation();
    	        //---------------------------------------------------------------------------------------------
    	        
    	        // Getting Current Location
    	        
    	        float locationa = (float) (location1.getLatitude());
    	        float locationb = (float) (location1.getLongitude());
    	        
    	        float addressa = (float) (location.getLatitude());
    	        float addressb = (float) (location.getLongitude());
    	        mMap.addMarker(new MarkerOptions().position(new LatLng(addressa, addressb)).title(name).icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_single)));
    	        mMap.addMarker(new MarkerOptions().position(new LatLng(locationa, locationb)).title("Vous"));
    	        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(addressa,addressb), 16));
        
    }
    private String getLocation_from_Address(){
    	
    	
    	Geocoder coder = new Geocoder(this);
    	Location loc = getCurrentLocation();

        float loclat = (float) (loc.getLatitude());
        float loclng = (float) (loc.getLongitude());
        List<Address> addresses = null ;
    	Geocoder geocoder = new Geocoder(this, Locale.getDefault());
    	try {
			addresses = geocoder.getFromLocation(loclat, loclng, 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Address add = addresses.get(0);
    	String addressText = String.format("%s, %s, %s",
                add.getMaxAddressLineIndex() > 0 ? add.getAddressLine(0) : "",
                add.getLocality(),
                add.getCountryName());
    	
    	return addressText;
    }
    private Location getCurrentLocation(){
    	   //----------------------------------------------------------------------------------------------
        
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        LocationProvider provider =locationManager.getProvider(LocationManager.NETWORK_PROVIDER);
     // Retrieve a list of location providers that have fine accuracy, no monetary cost, etc
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        List<String> lProviders = locationManager.getProviders(false);
        for(int i=0;i<lProviders.size();i++){
            Log.d("LocationActivity", lProviders.get(i));
        }
        String providerName = locationManager.getBestProvider(criteria, true);
        	
        // If no suitable provider is found, null is returned.
        if (providerName != null) {
        }
        Location location1 = locationManager.getLastKnownLocation(providerName);
	     
        return location1;
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_details__item, menu);
		return true;
	}
	private class DownloadFilesTask extends AsyncTask<String, Integer, AnnuaireObjectDetail> {
		AnnuaireObjectDetail annu=null;
			@Override
			protected AnnuaireObjectDetail doInBackground(String... urls) {
				wsd.open();
				WebServiceObject object =wsd.getWebServiceByID(2);
				wsd.close();
				
				WebServiceDetail ws = new WebServiceDetail();
				ws.seturl(object.getLink());
				Log.w("ahsina", "g4 "+object.getLink());
				Log.w("ahsina", "g4 "+Integer.parseInt(getIntent().getExtras().getString("ID")));
		         return ws.getDetail( Integer.parseInt(getIntent().getExtras().getString("ID")), "22 avenue chevreul 92600");
		     }

		     protected void onProgressUpdate(Integer... progress) {
		         setProgress(progress[0]);
		     }

		     protected void onPostExecute(Long result) {
		         
		     }

			
		 }
	

}
