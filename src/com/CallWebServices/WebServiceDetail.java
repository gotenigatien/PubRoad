package com.CallWebServices;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.Object.PubRoadObjectDetail;

public class WebServiceDetail {
	JSONParser jParser = new JSONParser();
	public PubRoadObjectDetail pubroad = new PubRoadObjectDetail();
	private static String url_pubroad = "";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_Results = "Results";
	private static final String TAG_ID = "ID";
	private static final String TAG_NomComplet = "NomComplet";
	private static final String TAG_Tel = "Tel";
	private static final String TAG_Fax = "Fax";
	private static final String TAG_Email = "Email";
	private static final String TAG_SiteWeb = "SiteWeb";
	private static final String TAG_Entreprise = "Entreprise";
	private static final String TAG_Description = "Description";
	private static final String TAG_Adresse = "Adresse";
	private static final String TAG_Adresse2 = "Adresse2";
	private static final String TAG_Distance = "Distance";
	private static final String TAG_PlageHoraire = "Horaire";
	private static final String TAG_Photo = "Photo";
	private static final String TAG_IsParticulier = "isParticulier";
	public void seturl(String URL){
		
		url_pubroad=URL;
		
	}
	JSONArray Jpubroad ;
	
	public PubRoadObjectDetail getDetail(int id , String place){
		int success;
		try {

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("ID",String.valueOf(id)));
			params.add(new BasicNameValuePair("Place", place));

			JSONObject json = jParser.makeHttpRequest(
					url_pubroad, "GET", params);

			Log.d("List des result du details", json.toString());

			success = json.getInt(TAG_SUCCESS);
			if (success == 1) {
				
				JSONArray pubroadObj = json
						.getJSONArray(TAG_Results); 

				JSONObject result = pubroadObj.getJSONObject(0);
				PubRoadObjectDetail objpubroad = new PubRoadObjectDetail();
				
				objpubroad.setID(result.getInt(TAG_ID)); 
				objpubroad.setTel(result.getString(TAG_Tel)); 
				objpubroad.setFax(result.getString(TAG_Fax));
				objpubroad.setEmail(result.getString(TAG_Email));
				objpubroad.setSiteWeb(result.getString(TAG_SiteWeb));
				objpubroad.setIsParticulier(result.getBoolean(TAG_IsParticulier));
				if(objpubroad.getIsParticulier()) {
					Log.w("ahsina", "nooooo "+objpubroad.getIsParticulier());
					objpubroad.setNom(result.getString(TAG_NomComplet)); 
				} 
				else 
				{
					Log.w("ahsina", "yeeees");
					objpubroad.setNom(result.getString(TAG_Entreprise));
				}
				objpubroad.setDescription(result.getString(TAG_Description));
				objpubroad.setAdresse(result.getString(TAG_Adresse));
				objpubroad.setAdresse2(result.getString(TAG_Adresse2));
				objpubroad.setDistance(result.getInt(TAG_Distance));
				objpubroad.setPhoto(result.getString(TAG_Photo));
				objpubroad.setPlageHoraire(result.getString(TAG_PlageHoraire));
				Log.w("ahsina","fghfh");
				//Log.w("zeb","zbouba"+result.getString(TAG_Description));
				pubroad= objpubroad;
			}else{
				
				pubroad= null;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Log.w("fghgf","fghf"+pubroad.getNom());
		return pubroad;
		
	}
}
