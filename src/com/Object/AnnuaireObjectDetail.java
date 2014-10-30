package com.Object;

import android.graphics.Bitmap;

public class AnnuaireObjectDetail {
	private int ID;
	private String Nom;
	private String Tel;
	private String Fax;
	private String Email;
	private String SiteWeb;
	private String Description;
	private String Adresse;
	private String Adresse2;
	private int Distance;
	private String PlageHoraire;
	private String Photo;
	private Boolean IsParticulier; 

	public String getAdresse2() {
		return Adresse2;
	}

	public void setAdresse2(String adresse2) {
		Adresse2 = adresse2;
	}
	
	public int getID() {
		
		return ID;
	}

	public void setID(int NvID) {
		
		this.ID=NvID;
	}

	public String getNom() {
		
		return Nom;
	}

	public void setNom(String nvNom) {
		
		this.Nom=nvNom;
	}

	public String getTel() {
		
		return Tel;
	}

	public void setTel(String nvTel) {
		
		this.Tel=nvTel;
	}
	
	public String getFax() {
		
		return Fax;
	}
	
	public void setFax(String nvFax) {
		
		this.Fax=nvFax;
	}

	public String getEmail() {
		
		return Email;
	}

	public void setEmail(String nvEmail) {
		
		this.Email= nvEmail;
	}

	public String getSiteWeb() {
		
		return SiteWeb;
	}

	public void setSiteWeb(String nvSiteWeb) {
		
		this.SiteWeb=nvSiteWeb;
	}
	
	
	public String getAdresse() {
		
		return Adresse;
	}

	public void setAdresse(String nvAdresse) {
		
		this.Adresse= nvAdresse;
	}
	
	public String getDescription() {
		
		return Description;
	}

	public void setDescription(String nvDescription) {
		
		this.Description= nvDescription;
	}

	public int getDistance() {
		
		return Distance;
	}

	public void setDistance(int nvDistance) {
		
		this.Distance= nvDistance;
	}

	public String getPlageHoraire() {
		
		return PlageHoraire;
	}
	
	public void setPlageHoraire(String nvPlageHoraire) {
		
		this.PlageHoraire=nvPlageHoraire;
	}
	public String getPhoto() {
		
		return Photo;
	}
	
	public void setPhoto(String nvPhoto) {
		this.Photo=nvPhoto;
	}
	
	public Boolean getIsParticulier() {
		
		return IsParticulier;
	}

	public void setIsParticulier(Boolean nvIsParticulier) {

		this.IsParticulier =nvIsParticulier;
	}
}
