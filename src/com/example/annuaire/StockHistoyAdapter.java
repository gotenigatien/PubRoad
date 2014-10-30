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
import com.Object.HistoryObject;

import android.app.Activity;

public class StockHistoyAdapter extends ArrayAdapter {
	 private final Activity activity;
	    private final ArrayList<HistoryObject> ListHistory;
	 
	    public StockHistoyAdapter(Activity activity, ArrayList<HistoryObject> objects) {
	        
	    	super(activity, R.layout.historyitem , objects);
	        this.activity = activity;
	        this.ListHistory = objects;
	    }
	
	    @Override
	    public View getView(int position, final View convertView, ViewGroup parent) {
	        View rowView = convertView;
	        HistoryView sqView = null;
	 
	        if(rowView == null)
	        {
	        	Log.w("ahsina", "gatien");
	            // Get a new instance of the row layout view
	            LayoutInflater inflater = activity.getLayoutInflater();
	            rowView = inflater.inflate(R.layout.historyitem, null);
	 
	            // Hold the view objects in an object,
	            // so they don't need to be re-fetched
	            sqView = new HistoryView();
	            sqView.ID = (TextView) rowView.findViewById(R.id.txtID);
	            sqView.Nom = (TextView) rowView.findViewById(R.id.history_ex);
	            sqView.Adresse = (TextView) rowView.findViewById(R.id.history_ad);
	            // Cache the view objects in the tag,
	            // so they can be re-accessed later
	            rowView.setTag(sqView);
	        } else {
	            sqView = (HistoryView) rowView.getTag();
	        }
	 
	        // Transfer the stock data from the data object
	        // to the view objects
	        HistoryObject currentStock = ListHistory.get(position);
	        sqView.ID.setText(String.valueOf(currentStock.getID()));
	        sqView.Nom.setText(currentStock.getName());
	        sqView.Adresse.setText(currentStock.getAdresse1().toString()+" "+currentStock.getAdresse2().toString());
	        
			Typeface brixton_book = Typeface.createFromAsset(activity.getAssets(), "fonts/brixton_book.otf");
			Typeface brixton_light_oblique = Typeface.createFromAsset(activity.getAssets(), "fonts/brixton_light_oblique.otf");
				{
				sqView.Adresse.setTypeface(brixton_light_oblique);
				
				sqView.Nom.setTypeface(brixton_book);
		        
				}
	        return rowView;
	    }
	    
	    protected static class HistoryView {
	        protected TextView Nom;
	        protected TextView Adresse;
	        protected TextView ID;
	        
	    }
}
