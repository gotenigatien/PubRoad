package com.example.annuaire;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import com.CallWebServices.WebService;
import com.CallWebServices.WebServiceDetail;
import com.CallWebServices.getWebServiceLink;
import com.Object.AnnuaireObject;
import com.Object.Utils;
import com.Object.WebServiceObject;

import com.Sqlite.WebServiceDataSource;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.content.Context;
import android.view.MotionEvent;

public class Liste extends FragmentActivity {
	public ArrayList<AnnuaireObject> arr= null;
	public WebServiceDataSource wsd=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.liste);
		Typeface brixton_book = Typeface.createFromAsset(getAssets(), "fonts/brixton_book.otf");
		TextView tv = (TextView) findViewById(R.id.title_header);
        tv.setTypeface(brixton_book);
        Log.w("test", "14785"+ getAddress_from_Location());
        tv = (TextView) findViewById(R.id.textcount);
        tv.setTypeface(brixton_book);
        
        tv = (TextView) findViewById(R.id.liste_proch);
        tv.setTypeface(brixton_book);
        
        tv = (TextView) findViewById(R.id.particulier);
        tv.setTypeface(brixton_book);
        
        tv = (TextView) findViewById(R.id.entreprise);
        tv.setTypeface(brixton_book);
        
		ListView lv =(ListView) findViewById(R.id.lvListe);
		View empty = findViewById(R.id.emptyView);
		wsd = new WebServiceDataSource(Liste.this);

		
		try {
			arr=new DownloadFilesTask().execute("").get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (arr!=null){
		lv.setAdapter(new StockAnnuaireAdapter(Liste.this,arr));
		TextView txtcount= (TextView)findViewById(R.id.textcount);
		txtcount.setText(String.valueOf(arr.size()));
		//setListAdapter(new StockAnnuaireAdapter(this, ws.getListAnnuaire("ah", "92", "39 rue de moscou paris")));
		Button btn =((Button)findViewById(R.id.back_liste));
		// Nous paramétrons un écouteur sur l’événement ‘click’ de ce bouton
		btn.setOnClickListener(new OnClickListener()
		{
		public void onClick(View v)
		{
		 Liste.this.finish();
		 
		}
		}
		);
		lv.setOnItemClickListener(new OnItemClickListener() {
	          public void onItemClick(AdapterView<?> parent, View view,
	              int position, long id) {
	            //Launching new Activity on selecting single List Item
	        	  final int position1 = parent.getPositionForView((View) view.getParent());
	        	 
	             Intent i = new Intent(getApplicationContext(), Details_Item.class);
	             // sending data to new activity
	             View parentView = (View) view.getParent();
	             String selected = ((TextView) view.findViewById(R.id.txtID)).getText().toString();
	               
	              i.putExtra("ID", selected);
	             startActivityForResult(i, 100);
	             
	          }
	        });
		
		
		Button btn_Desc =((Button)findViewById(R.id.tri_desc));
		btn_Desc.setOnClickListener(new OnClickListener()
		{
		public void onClick(View v)
		{
			Collections.sort(arr,Collections.reverseOrder());
			ListView lv =(ListView) findViewById(R.id.lvListe);
			lv.setAdapter(new StockAnnuaireAdapter(Liste.this,arr));
			TextView txt =(TextView) findViewById(R.id.liste_proch);
			txt.setText("Le plus loin");
		}
		}
		);
		Button btn_Asc =((Button)findViewById(R.id.tri_asc));
		btn_Asc.setOnClickListener(new OnClickListener()
		{
		public void onClick(View v)
		{
			Collections.sort(arr);
			ListView lv =(ListView) findViewById(R.id.lvListe);
			lv.setAdapter(new StockAnnuaireAdapter(Liste.this,arr));
			TextView txt =(TextView) findViewById(R.id.liste_proch);
			txt.setText("Le plus proche");
		}
		}
		);
		Button btn_Particulier =((Button)findViewById(R.id.particulier));
		btn_Particulier.setOnClickListener(new OnClickListener()
		{
		public void onClick(View v)
		{
			ArrayList<AnnuaireObject> arr1=arr;
			Utils utl = new Utils();
			arr1=utl.getOnlyParticulier(arr1);
			ListView lv =(ListView) findViewById(R.id.lvListe);
			lv.setAdapter(new StockAnnuaireAdapter(Liste.this,arr1));
			
		}
		}
		);
		
		Button btn_Entreprise =((Button)findViewById(R.id.entreprise));
		btn_Entreprise.setOnClickListener(new OnClickListener()
		{
		public void onClick(View v)
		{
			ArrayList<AnnuaireObject> arr1=arr;
			Utils utl = new Utils();
			arr1=utl.getOnlyEntreprise(arr1);
			ListView lv =(ListView) findViewById(R.id.lvListe);
			lv.setAdapter(new StockAnnuaireAdapter(Liste.this,arr1));
		 
		}
		}
		);
		
		
		}
		else {
			lv.setEmptyView(empty);
			
		}
	}

	
private class DownloadFilesTask extends AsyncTask<String, Integer, ArrayList<AnnuaireObject>> {
	ArrayList<AnnuaireObject> annu=null;
	
		@Override
		protected ArrayList<AnnuaireObject> doInBackground(String... urls) {
			wsd.open();
			WebServiceObject object =wsd.getWebServiceByID(1);
			wsd.close();
			
			
			WebService ws = new WebService();
			ws.seturl(object.getLink());
			
			Log.w("who ", "Who "+object.getLink());
			Log.w("who ", "Where "+getIntent().getExtras().getString("Where"));
			
	         return ws.getListAnnuaire(getIntent().getExtras().getString("Who"), getIntent().getExtras().getString("Where"), "22 avenue chevreul 92600");
	     }

	     protected void onProgressUpdate(Integer... progress) {
	         setProgress(progress[0]);
	         
	     }

	     protected void onPostExecute(Long result) {
	         
	     }

		
	 }
public String getAddress_from_Location(){
	
	
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
public Location getCurrentLocation(){
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


}


