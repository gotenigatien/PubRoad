package com.Object;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;


public class Utils{
	

	public ArrayList<AnnuaireObject> getOnlyParticulier(ArrayList<AnnuaireObject> Annuaire){
		ArrayList<AnnuaireObject> Result = new ArrayList<AnnuaireObject>();
		for (int i=0; i<Annuaire.size();i++){
			Boolean Entreprise=Annuaire.get(i).getIsParticulier() ;
			if (Entreprise){
				Result.add(Annuaire.get(i));
			}
		}
		return Result;
	}
	public ArrayList<AnnuaireObject> getOnlyEntreprise(ArrayList<AnnuaireObject> Annuaire){
		ArrayList<AnnuaireObject> Result = new ArrayList<AnnuaireObject>() ;
			for (int i=0; i<Annuaire.size();i++){
				Boolean Entreprise=Annuaire.get(i).getIsParticulier() ;
				if (!Entreprise){
					Result.add(Annuaire.get(i));
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
