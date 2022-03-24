package com.epsi.fr.gosecuri.models.personnes;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.epsi.fr.gosecuri.models.personnes.documents.CarteIdentite;
import com.epsi.fr.gosecuri.models.personnes.materiels.Materiel;


public class Agent extends Employe {
	
	public String nom;
	public String prenom;
	public String poste;
	public String motdepasse;
	public CarteIdentite carteIdentite;
	
	public List<Materiel> listeMateriel = new ArrayList<Materiel>();

	public Agent(String identifiantEmploye, String nom, String prenom, String poste, String motdepasse,
			CarteIdentite carteIdentite, List<Materiel> listeMateriel) {
		
		super(identifiantEmploye);
		this.nom = nom;
		this.prenom = prenom;
		this.poste = poste;
		this.motdepasse = motdepasse;
		this.carteIdentite = carteIdentite;
		this.listeMateriel = listeMateriel;
	}
	
	// Changer le toString
	
	public StringBuffer toStringHtml() {
		StringBuffer sb = new StringBuffer();
		
		
		
		return sb;
	}
	
	public String getPrenomNom() {
		return prenom + " " + nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPoste() {
		return poste;
	}

	public void setPoste(String poste) {
		this.poste = poste;
	}

	public String getMotdepasse() {
		return motdepasse;
	}

	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}

	public CarteIdentite getCarteIdentite() {
		return carteIdentite;
	}

	public void setCarteIdentite(CarteIdentite carteIdentite) {
		this.carteIdentite = carteIdentite;
	}

	public List<Materiel> getListeMateriel() {
		return listeMateriel;
	}

	public void setListeMateriel(List<Materiel> listeMateriel) {
		this.listeMateriel = listeMateriel;
	}

	

}
