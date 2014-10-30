package com.Sqlite;

import java.util.ArrayList;

import com.CallWebServices.WebServiceDetail;
import com.Object.HistoryObject;
import com.Object.WebServiceObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class WebServiceDataSource {
	// Database fields
	  private SQLiteDatabase database;
	  private MySQLiteHelper dbHelper;
	  private String[] allColumnsHistory = { MySQLiteHelper.COLUMN_ID,MySQLiteHelper.COLUMN_Name,MySQLiteHelper.COLUMN_Adresse1,
		      MySQLiteHelper.COLUMN_Adresse2,MySQLiteHelper.COLUMN_DateAdd };
	  private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
	      MySQLiteHelper.COLUMN_Link,MySQLiteHelper.COLUMN_DateAdd };

	  public WebServiceDataSource(Context context) {
	    dbHelper = new MySQLiteHelper(context);
	  }

	  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }

	  public void createWS(int ID,String Link,String DateAdd) {
	    ContentValues values = new ContentValues();
	    values.put(MySQLiteHelper.COLUMN_ID, ID);
	    values.put(MySQLiteHelper.COLUMN_Link, Link);
	    values.put(MySQLiteHelper.COLUMN_DateAdd, DateAdd);
	    long insertId = database.insert(MySQLiteHelper.TABLE_WebServices, null,
	        values);

	 
	  }
	  
	  

	  public WebServiceObject getWebServiceByID(int ID){
		 
		  Cursor cursor = database.query(MySQLiteHelper.TABLE_WebServices,
			        allColumns, MySQLiteHelper.COLUMN_ID + "=" + ID, null,
			        null, null, null);
		  
			    cursor.moveToFirst();
			    WebServiceObject newWeb = cursorToWebService(cursor);
			    cursor.close();
			    return newWeb;
	  }
	  
	  public void deleteWs(long id) {
	    
	    System.out.println("Comment deleted with id: " + id);
	    database.delete(MySQLiteHelper.TABLE_WebServices, MySQLiteHelper.COLUMN_ID
	        + " = " + id, null);
	  }


	  private WebServiceObject cursorToWebService(Cursor cursor) {
		  WebServiceObject ws = new WebServiceObject();
		  try {
			  
			  ws.setId(cursor.getInt(0));
			  ws.setLink(cursor.getString(1));
			  ws.setDateAdd(cursor.getString(2));
		} catch (Exception e) {
			
		}
	
	    return ws;
	  }
	  
	  public void AddHistory(int ID,String Name,String Adresse1,String Adresse2,String DatAdd) {
		  if(!VerifID(ID)){
			  ContentValues values = new ContentValues();
			  	
			    values.put(MySQLiteHelper.COLUMN_ID, ID);
			    values.put(MySQLiteHelper.COLUMN_Name, Name);
			    values.put(MySQLiteHelper.COLUMN_Adresse1, Adresse1);
			    values.put(MySQLiteHelper.COLUMN_Adresse2, Adresse2);
			    values.put(MySQLiteHelper.COLUMN_DateAdd, DatAdd);
			    
			    long insertId =database.insert(MySQLiteHelper.TABLE_History, null,
			        values);
			    Log.w("hhh","insertID "+insertId);
		  }
		  Log.w("hhh","easy "+VerifID(ID));
	  }
	  
	  

	  public Cursor getHistory(){
		 
		  Cursor cursor = database.query(MySQLiteHelper.TABLE_History,
			        allColumnsHistory, null, null,
			        null, null, null);
		  Log.w("hhh", "cursor count"+cursor.getCount());
		  	
			    	    return cursor;
	  }
	  
	  public Boolean VerifID(int ID){

		  Cursor cursor = database.query(MySQLiteHelper.TABLE_History,
			        allColumnsHistory, MySQLiteHelper.COLUMN_IDH + "=" + ID, null,
			        null, null, null);
		  		
		  if(cursor.getCount()==0)
		  {
			  
			  return false;
		  }
		  else 
		  {
			  
			  return true;
		  }
	  }
	  
	  public void deleteHistorique() {
	    
	    
	    database.delete(MySQLiteHelper.TABLE_History,null, null);
	  }


	  private HistoryObject cursorToHistory(Cursor cursor) {
		  HistoryObject ws = new HistoryObject();
		
			  
			  ws.setID(cursor.getInt(0));
			  
			  ws.setName(cursor.getString(1));
			  ws.setAdresse1(cursor.getString(2));
			  ws.setAdresse2(cursor.getString(3));
			  
			  

	    return ws;
	  }
}
