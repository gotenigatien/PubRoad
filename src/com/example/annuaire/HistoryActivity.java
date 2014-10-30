package com.example.annuaire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import com.Object.HistoryObject;
import com.Sqlite.MySQLiteHelper;
import com.Sqlite.WebServiceDataSource;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HistoryActivity extends Activity {
	public ArrayList<HistoryObject> arr1=new ArrayList<HistoryObject>();
	public WebServiceDataSource h;
	public ListView lv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
		lv =(ListView) findViewById(R.id.lvListe);
		View empty = findViewById(R.id.emptyView);
       		
				
			h = new WebServiceDataSource(HistoryActivity.this);
			h.open();
			Log.w("test", "dimanche ");
			Log.w("test", "dimanche ");
			//Log.w("test", "dimanche "+h.getHistory().size());
			Cursor c=  h.getHistory();
			
			if (c.moveToFirst()){
				   do{
					   HistoryObject hi = new HistoryObject();
					   hi.setID(c.getInt(c.getColumnIndex(MySQLiteHelper.COLUMN_IDH)));
					   hi.setName(c.getString(c.getColumnIndex(MySQLiteHelper.COLUMN_Name)));
					   Log.w("test", "dimanche apres"+c.getString(c.getColumnIndex(MySQLiteHelper.COLUMN_Adresse2)));
					   hi.setAdresse1(c.getString(c.getColumnIndex(MySQLiteHelper.COLUMN_Adresse1)));
					   hi.setAdresse2(c.getString(c.getColumnIndex(MySQLiteHelper.COLUMN_Adresse2)));
					   hi.setDateAdd(c.getString(c.getColumnIndex(MySQLiteHelper.COLUMN_DateAdd)));
					  
				     arr1.add(hi);
				      // do what ever you want here
				   }while(c.moveToNext());
				}
				c.close();
			h.close();
			if (arr1!=null){
			
			lv.setAdapter(new StockHistoyAdapter(HistoryActivity.this,arr1));
			RelativeLayout rl1 = (RelativeLayout) findViewById(R.id.emptyView);
            rl1.setVisibility(View.INVISIBLE);
			}
			else {
				lv.setEmptyView(empty);
				
			}
			
	       	
			final Button back_history_button = (Button) findViewById(R.id.back_history);
			back_history_button.setOnClickListener(new OnClickListener()
			{
			public void onClick(View v)
			{
			 HistoryActivity.this.finish();
			}
			}
			);
			final Button Delete_button = (Button) findViewById(R.id.delete);
			Delete_button.setOnClickListener(new OnClickListener()
			{
			public void onClick(View v)
			{
				h.open();
			 h.deleteHistorique();
				h.close();
			 arr1.clear();
			 ((StockHistoyAdapter) lv.getAdapter()).notifyDataSetChanged();
			}
			}
			);
			
    } 
}