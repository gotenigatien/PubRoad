package com.example.annuaire;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.Object.AnnuaireObject;
import android.app.Activity;

public class StockAnnuaireAdapter extends ArrayAdapter {
	 private final Activity activity;
	    private final ArrayList<AnnuaireObject> ListAnnuaire;
	 
	    public StockAnnuaireAdapter(Activity activity, ArrayList<AnnuaireObject> objects) {
	        
	    	super(activity, R.layout.affichageitem , objects);
	        this.activity = activity;
	        this.ListAnnuaire = objects;
	    }
	
	    @Override
	    public View getView(int position, final View convertView, ViewGroup parent) {
	        View rowView = convertView;
	        AnnuaireView sqView = null;
	        AnnuaireObject currentStock = ListAnnuaire.get(position);
	        if(rowView == null)
	        {
	        	Log.w("ahsina", "gatien");
	            // Get a new instance of the row layout view
	            LayoutInflater inflater = activity.getLayoutInflater();
	            rowView = inflater.inflate(R.layout.affichageitem, null);
	 
	            // Hold the view objects in an object,
	            // so they don't need to be re-fetched
	            sqView = new AnnuaireView();
	            sqView.Nom = (TextView) rowView.findViewById(R.id.txtNom);
	            sqView.Adresse = (TextView) rowView.findViewById(R.id.txtAdresse);
	            sqView.Distance= (TextView) rowView.findViewById(R.id.txtDistance);
	            sqView.SiteWeb= (Button) rowView.findViewById(R.id.txtWebSite);
	            sqView.Tel= (TextView) rowView.findViewById(R.id.txtTel);
	            sqView.ID= (TextView) rowView.findViewById(R.id.txtID);
	            
		        Log.w("ahsina","layla "+ String.valueOf(currentStock.getID()));
		        sqView.ID.setText(String.valueOf(currentStock.getID()));
		        sqView.Nom.setText(currentStock.getNom());
		        sqView.Adresse.setText(currentStock.getAdresse().toString());
		        float Distanc= (float)currentStock.getDistance()/1000;
		        sqView.Distance.setText(String.valueOf(Distanc)+" Km");
		        sqView.SiteWeb.setText("SITE WEB");

		        sqView.Tel.setText(currentStock.getTel().toString().replaceFirst("(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d+)", "$1.$2.$3.$4.$5"));
	            // Cache the view objects in the tag,
	            // so they can be re-accessed later
	            rowView.setTag(sqView);
	        } else {
	            sqView = (AnnuaireView) rowView.getTag();
	        }
	        final String url =currentStock.getSiteWeb();
	        final String Telephone =currentStock.getTel();
	        // Transfer the stock data from the data object
	        // to the view objects
	       
	        sqView.SiteWeb.setOnClickListener(new OnClickListener() { 
	            @Override 
	            public void onClick(View v) { 
	                
	            	activity.startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://"+url)));
	            } 
	        });
	        sqView.Tel.setOnClickListener(new OnClickListener() { 
	            @Override 
	            public void onClick(View v) { 
	                
	            	activity.startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:"+Telephone)));
	            } 
	        });
			Typeface brixton_book = Typeface.createFromAsset(activity.getAssets(), "fonts/brixton_book.otf");
			Typeface brixton_light_oblique = Typeface.createFromAsset(activity.getAssets(), "fonts/brixton_light_oblique.otf");
				{
				sqView.Adresse.setTypeface(brixton_light_oblique);

				sqView.Distance.setTypeface(brixton_light_oblique);
				
				sqView.Nom.setTypeface(brixton_book);
		        
		        sqView.SiteWeb.setTypeface(brixton_book);
		        
		        sqView.Tel.setTypeface(brixton_book);
		        
				}
	        return rowView;
	    }
	    
	    protected static class AnnuaireView {
	        protected TextView Nom;
	        protected TextView Adresse;
	        protected TextView Distance;
	        protected TextView Tel;
	        protected Button SiteWeb;
	        protected TextView ID;
	        
	    }
}
