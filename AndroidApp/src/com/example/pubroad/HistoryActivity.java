package com.example.pubroad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import com.Object.HistoryObject;
import com.Sqlite.MySQLiteHelper;
import com.Sqlite.WebServiceDataSource;
import com.example.pubroad.R;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

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

		Typeface brixton_book = Typeface.createFromAsset(getAssets(), "fonts/brixton_book.otf");
		TextView tv = (TextView) findViewById(R.id.title_header);
		tv.setTextColor(Color.rgb(255, 255, 255));
		tv.setTypeface(brixton_book);
		
		tv = (TextView) findViewById(R.id.delete);
		tv.setTextColor(Color.rgb(255, 255, 255));
		tv.setTypeface(brixton_book);


		tv = (TextView) findViewById(R.id.history_history);
		tv.setTypeface(brixton_book);
		
			h = new WebServiceDataSource(HistoryActivity.this);
			h.open();
			Cursor c=  h.getHistory();
			
			if (c.moveToFirst()){
				   do{
					   HistoryObject hi = new HistoryObject();
					   hi.setID(c.getInt(c.getColumnIndex(MySQLiteHelper.COLUMN_IDH)));
					   hi.setName(c.getString(c.getColumnIndex(MySQLiteHelper.COLUMN_Name)));
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
            lv.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView <? > parent, View view,
                    int position, long id) {
                    //Launching new Activity on selecting single List Item

                    Log.w("aaaaa", "yyoy");
                    final int position1 = parent.getPositionForView((View) view.getParent());

                    Intent i = new Intent(getApplicationContext(), Details_Item.class);
                    // sending data to new activity
                    View parentView = (View) view.getParent();
                    String selected = ((TextView) view.findViewById(R.id.txtID)).getText().toString();

                    i.putExtra("ID", selected);
                    startActivityForResult(i, 100);
                	}
            	});
            
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
