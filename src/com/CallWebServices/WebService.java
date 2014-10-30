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
import com.Object.AnnuaireObject;
import com.Object.WebServiceObject;
import com.Sqlite.WebServiceDataSource;
import com.example.annuaire.MainActivity;



public class WebService{
	
	JSONParser jParser = new JSONParser();
	public ArrayList<AnnuaireObject> ListeAnnuaire = new ArrayList<AnnuaireObject>();
	private static String url_ListAnnuaire = "";
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
	

	
	JSONArray Annuaire ;
	
	public void seturl(String URL){
				
		url_ListAnnuaire=URL;
		
	}
	
	public ArrayList<AnnuaireObject> getListAnnuaire(String who, String where, String place ){
		
		int success;
		try {

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("Who", who));
			params.add(new BasicNameValuePair("Where", where));
			params.add(new BasicNameValuePair("Place", place));

			Log.w("ahsina", "seturl");
			JSONObject json = jParser.makeHttpRequest(
					url_ListAnnuaire, "GET", params);
			Log.w("ahsina", "link "+url_ListAnnuaire+"?who=" +who +"&where=" +where +"&place="+place);

			Log.d("List des result", json.toString());
			

			success = json.getInt(TAG_SUCCESS);
			if (success == 1) {

				JSONArray AnnuaireObj = json
						.getJSONArray(TAG_Results); 
				

				for (int i = 0; i < AnnuaireObj.length(); i++) {
				JSONObject result = AnnuaireObj.getJSONObject(i);
				AnnuaireObject objAnnuaire = new AnnuaireObject();
				Log.w("arsene", "arsene "+result.getInt(TAG_ID));
				objAnnuaire.setID(result.getInt(TAG_ID));
				objAnnuaire.setIsParticulier(result.getBoolean(TAG_IsParticulier));
				if(result.getBoolean(TAG_IsParticulier)){
				objAnnuaire.setNom(result.getString(TAG_NomComplet));
				}else{
					objAnnuaire.setNom(result.getString(TAG_Entreprise));
					
				}
				objAnnuaire.setTel(result.getString(TAG_Tel));
				objAnnuaire.setEmail(result.getString(TAG_Email));
				objAnnuaire.setSiteWeb(result.getString(TAG_SiteWeb));
				
				objAnnuaire.setAdresse(result.getString(TAG_Adresse));
				objAnnuaire.setDistance(result.getInt(TAG_Distance));
				objAnnuaire.setOccurance(result.getInt(TAG_Occurance));
				objAnnuaire.setIsParticulier(result.getBoolean(TAG_IsParticulier));

				ListeAnnuaire.add(objAnnuaire);
				
				}
				
			}else{
				ListeAnnuaire= null;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return ListeAnnuaire;
	}
	
}
