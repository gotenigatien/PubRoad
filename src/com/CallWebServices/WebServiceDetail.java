package com.CallWebServices;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.Object.AnnuaireObjectDetail;

public class WebServiceDetail {
	JSONParser jParser = new JSONParser();
	public AnnuaireObjectDetail Annuaire = new AnnuaireObjectDetail();
	private static String url_Annuaire = "";
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
		
		url_Annuaire=URL;
		
	}
	JSONArray JAnnuaire ;
	
	public AnnuaireObjectDetail getDetail(int id , String place){
		int success;
		try {

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("ID",String.valueOf(id)));
			params.add(new BasicNameValuePair("Place", place));

			JSONObject json = jParser.makeHttpRequest(
					url_Annuaire, "GET", params);

			Log.d("List des result du details", json.toString());

			success = json.getInt(TAG_SUCCESS);
			if (success == 1) {
				
				JSONArray AnnuaireObj = json
						.getJSONArray(TAG_Results); 

				JSONObject result = AnnuaireObj.getJSONObject(0);
				AnnuaireObjectDetail objAnnuaire = new AnnuaireObjectDetail();
				
				objAnnuaire.setID(result.getInt(TAG_ID)); 
				objAnnuaire.setTel(result.getString(TAG_Tel)); 
				objAnnuaire.setFax(result.getString(TAG_Fax));
				objAnnuaire.setEmail(result.getString(TAG_Email));
				objAnnuaire.setSiteWeb(result.getString(TAG_SiteWeb));
				objAnnuaire.setIsParticulier(result.getBoolean(TAG_IsParticulier));
				if(objAnnuaire.getIsParticulier()) {
					Log.w("ahsina", "nooooo "+objAnnuaire.getIsParticulier());
					objAnnuaire.setNom(result.getString(TAG_NomComplet)); 
				} 
				else 
				{
					Log.w("ahsina", "yeeees");
					objAnnuaire.setNom(result.getString(TAG_Entreprise));
				}
				objAnnuaire.setDescription(result.getString(TAG_Description));
				objAnnuaire.setAdresse(result.getString(TAG_Adresse));
				objAnnuaire.setAdresse2(result.getString(TAG_Adresse2));
				objAnnuaire.setDistance(result.getInt(TAG_Distance));
				objAnnuaire.setPhoto(result.getString(TAG_Photo));
				objAnnuaire.setPlageHoraire(result.getString(TAG_PlageHoraire));
				Log.w("ahsina","fils de pute");
				//Log.w("zeb","zbouba"+result.getString(TAG_Description));
				Annuaire= objAnnuaire;
			}else{
				
				Annuaire= null;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Log.w("zeb","zbouba"+Annuaire.getNom());
		return Annuaire;
		
	}
}
