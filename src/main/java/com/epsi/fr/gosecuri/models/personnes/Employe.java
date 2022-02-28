package com.epsi.fr.gosecuri.models.personnes;

public class Employe {

	protected String identifiantEmploye;

	public Employe(String identifiantEmploye) {
		this.identifiantEmploye = identifiantEmploye;
	}

	public String getIdentifiantEmploye() {
		return identifiantEmploye;
	}

	public void setIdentifiantEmploye(String identifiantEmploye) {
		this.identifiantEmploye = identifiantEmploye;
	}
	
}
