package com.Object;

import android.annotation.SuppressLint;


public class PubRoadObject implements Comparable<PubRoadObject> {

	private int ID;
	private String Nom;
	private String Tel;
	private String Email;
	private String SiteWeb;
	private String Entreprise;
	private String Adresse;
	private int Distance;
	private int Occurance;
	private Boolean IsParticulier; 

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

	public String getEntreprise() {
	
		return Entreprise;
	}

	public void setEntreprise(String nvEntreprise) {
	
		this.Entreprise= nvEntreprise;
	}

	public String getAdresse() {
	
		return Adresse;
	}

	public void setAdresse(String nvAdresse) {
	
		this.Adresse= nvAdresse;
	}

	public int getDistance() {
	
		return Distance;
	}

	public void setDistance(int nvDistance) {
	
		this.Distance= nvDistance;
	}

	public int getOccurance() {
	
		return Occurance;
	}

	public void setOccurance(int nvOccurance) {
	
		this.Occurance= nvOccurance;
	}

	public Boolean getIsParticulier() {
	
		return IsParticulier;
	}

	public void setIsParticulier(Boolean nvIsParticulier) {
	
		this.IsParticulier =nvIsParticulier;
	}
	
	@SuppressLint("UseValueOf")
	@Override
	public int compareTo(PubRoadObject another) {
	
		Integer myDistance = new Integer(Distance);
		Integer myDistance2 = new Integer(another.Distance);
		return myDistance.compareTo(myDistance2);
	}
	
	
	
}
