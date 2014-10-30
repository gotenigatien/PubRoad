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
import com.Object.WebServiceObject;



public class getWebServiceLink {
	JSONParser jParser = new JSONParser();
	public ArrayList<WebServiceObject> Annuaire = new ArrayList<WebServiceObject>();
	private static String url_Annuaire = "http://annuaire.gzed.fr/services/getServices.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_Results = "Results";
	private static final String TAG_ID = "ID";
	private static final String TAG_LINK = "LINK";
	private static final String TAG_DATEADD = "DATEADD";
	
JSONArray JAnnuaire ;
	
	public ArrayList<WebServiceObject> getDetail(){
		int success;
		try {
			ArrayList<WebServiceObject> ws = new ArrayList<WebServiceObject>();
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("",""));
			JSONObject json = jParser.makeHttpRequest(url_Annuaire, "GET", params);

			Log.d("List des result du details", json.toString());

			success = json.getInt(TAG_SUCCESS);
			if (success == 1) {
				
				JSONArray AnnuaireObj = json
						.getJSONArray(TAG_Results); 
				for (int i = 0; i < AnnuaireObj.length(); i++) {
				JSONObject result = AnnuaireObj.getJSONObject(i);
				WebServiceObject wsObj = new WebServiceObject();
				
				wsObj.setId(result.getInt(TAG_ID)); 
				wsObj.setLink(result.getString(TAG_LINK)); 
				wsObj.setDateAdd(result.getString(TAG_DATEADD));
				ws.add(wsObj);
				}
				//Log.w("zeb","zbouba"+result.getString(TAG_Description));
				Annuaire= ws;
			}else{
				
				Annuaire= null;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return Annuaire;
		
	}
	

}
