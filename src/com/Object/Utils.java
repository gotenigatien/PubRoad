package com.Object;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;


public class Utils{
	

	public ArrayList<PubRoadObject> getOnlyParticulier(ArrayList<PubRoadObject> pubroad){
		ArrayList<PubRoadObject> Result = new ArrayList<PubRoadObject>();
		for (int i=0; i<pubroad.size();i++){
			Boolean Entreprise=pubroad.get(i).getIsParticulier() ;
			if (Entreprise){
				Result.add(pubroad.get(i));
			}
		}
		return Result;
	}
	public ArrayList<PubRoadObject> getOnlyEntreprise(ArrayList<PubRoadObject> pubroad){
		ArrayList<PubRoadObject> Result = new ArrayList<PubRoadObject>() ;
			for (int i=0; i<pubroad.size();i++){
				Boolean Entreprise=pubroad.get(i).getIsParticulier() ;
				if (!Entreprise){
					Result.add(pubroad.get(i));
				}
			}
		return Result;
	}

	public Bitmap getImage(String encodedImage){
		byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
		Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);  
		return decodedByte;
	}
}
