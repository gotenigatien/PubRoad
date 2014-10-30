package com.Object;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.util.Log;

public class HistoryObject  implements Comparable<HistoryObject>  {
	private int ID;
	private String Name;
	private String Adresse1;
	private String Adresse2;
	private String DateAdd;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAdresse1() {
		return Adresse1;
	}
	public void setAdresse1(String adresse1) {
		Adresse1 = adresse1;
	}
	public String getAdresse2() {
		return Adresse2;
	}
	public void setAdresse2(String adresse2) {
		Adresse2 = adresse2;
	}
	public String getDateAdd() {
		return DateAdd;
	}
	public void setDateAdd(String dateAdd) {
		DateAdd = dateAdd;
	}
	public static Date stringToDate(String sDate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(sDate);
} 
	
	@SuppressLint("UseValueOf")
	@Override
	public int compareTo(HistoryObject another) {
		
		
		Date myDistance =new Date();
		try {
			myDistance = stringToDate(DateAdd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.w("hhh", "Date 1"+myDistance);
		Date myDistance2 =null;
		try {
			myDistance2 = stringToDate(another.DateAdd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myDistance.compareTo(myDistance2);
	}
	
}
