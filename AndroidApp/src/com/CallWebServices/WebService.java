package com.CallWebServices;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.util.Log;
import com.Object.PubRoadObject;
import com.Object.WebServiceObject;
import com.Sqlite.WebServiceDataSource;
import com.example.pubroad.MainActivity;



public class WebService{
	
	JSONParser jParser = new JSONParser();
	public ArrayList<PubRoadObject> Listepubroad = new ArrayList<PubRoadObject>();
	private static String url_Listpubroad = "";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_Results = "Results";
	private static final String TAG_ID = "ID";
	private static final String TAG_NomComplet = "NomComplet";
	private static final String TAG_Tel = "Tel";
	private static final String TAG_Email = "Email";
	private static final String TAG_SiteWeb = "SiteWeb";
	private static final String TAG_Entreprise = "Entreprise";
	private static final String TAG_Adresse = "Adresse";
	private static final String TAG_Distance = "Distance";
	private static final String TAG_Occurance = "Occurance";
	private static final String TAG_IsParticulier = "isParticulier";
	

	
	JSONArray pubroad ;
	
	public void seturl(String URL){
				
		url_Listpubroad=URL;
		
	}
	
	public ArrayList<PubRoadObject> getListpubroad(String who, String where, String place ){
		
		int success;
		try {

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("Who", who));
			params.add(new BasicNameValuePair("Where", where));
			params.add(new BasicNameValuePair("Place", place));
			JSONObject json = jParser.makeHttpRequest(
					url_Listpubroad, "GET", params);
			//Log.w("ahsina", "link "+url_Listpubroad+"?who=" +who +"&where=" +where +"&place="+place);

			//Log.d("List des result", json.toString());
			

			success = json.getInt(TAG_SUCCESS);
			if (success == 1) {

				JSONArray pubroadObj = json
						.getJSONArray(TAG_Results); 
				

				for (int i = 0; i < pubroadObj.length(); i++) {
				JSONObject result = pubroadObj.getJSONObject(i);
				PubRoadObject objpubroad = new PubRoadObject();
				Log.w("arsene", "arsene "+result.getInt(TAG_ID));
				objpubroad.setID(result.getInt(TAG_ID));
				objpubroad.setIsParticulier(result.getBoolean(TAG_IsParticulier));
				if(result.getBoolean(TAG_IsParticulier)){
				objpubroad.setNom(result.getString(TAG_NomComplet));
				}else{
					objpubroad.setNom(result.getString(TAG_Entreprise));
					
				}
				objpubroad.setTel(result.getString(TAG_Tel));
				objpubroad.setEmail(result.getString(TAG_Email));
				objpubroad.setSiteWeb(result.getString(TAG_SiteWeb));
				
				objpubroad.setAdresse(result.getString(TAG_Adresse));
				objpubroad.setDistance(result.getInt(TAG_Distance));
				objpubroad.setOccurance(result.getInt(TAG_Occurance));
				objpubroad.setIsParticulier(result.getBoolean(TAG_IsParticulier));

				Listepubroad.add(objpubroad);
				
				}
				
			}else{
				Listepubroad= null;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return Listepubroad;
	}
	
}
