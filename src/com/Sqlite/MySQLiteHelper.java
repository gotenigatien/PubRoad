package com.Sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	  public static final String TABLE_WebServices = "webservice";
	  public static final String TABLE_History = "History";
	  
	  
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_Link = "Link";
	  public static final String COLUMN_DateAdd = "DateADD";
	  
	  public static final String COLUMN_IDH = "_id";
	  public static final String COLUMN_Name = "Nom";
	  public static final String COLUMN_Adresse1 = "Adresse1";
	  public static final String COLUMN_Adresse2 = "Adresse2";
	  public static final String COLUMN_Date = "DateADD";

	  private static final String DATABASE_NAME = "Annuaire.db";
	  private static final int DATABASE_VERSION = 5;

	  // Database creation sql statement
	  private static final String WebService_CREATE = "create table "
	      + TABLE_WebServices + "(" + COLUMN_ID
	      + " integer , " + COLUMN_Link
	      + " text not null, "+ COLUMN_DateAdd +" text not null);";
	  
	  private static final String History_CREATE = "create table "
		      + TABLE_History + "(" + COLUMN_IDH
		      + " integer , " + COLUMN_Name
		      + " text , "+ COLUMN_Adresse1
		      + " text , "+ COLUMN_Adresse2
		      + " text , "+ COLUMN_DateAdd +" text);";

	  public MySQLiteHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }

	  @Override
	  public void onCreate(SQLiteDatabase database) {
	    database.execSQL(WebService_CREATE);
	    database.execSQL(History_CREATE);
	  }

	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(MySQLiteHelper.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_WebServices);
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_History);
	    onCreate(db);
	  }

	} 