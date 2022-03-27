package com.epsi.fr.gosecuri.models.personnes.listes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.epsi.fr.gosecuri.commun.Commun;

public class ListeCartesIdentite {
	
	public static final String cheminDossierCartesIdentites = Commun.cheminDossierGitGoSecuri + "/Identities";
	private String cheminDossierAgentDetails = "";
	
	private Map<String, ImageIcon> listeCartesIdentite = new HashMap<String, ImageIcon>();
	
	public ListeCartesIdentite(String cheminDossierAgentDetails) {
		this.cheminDossierAgentDetails = cheminDossierAgentDetails;
		
		listeCartesIdentite = recupListeCartesIdentite();
	}

	public Map<String, ImageIcon> getListeCartesIdentite() {
		return listeCartesIdentite;
	}

	public Map<String, ImageIcon> recupListeCartesIdentite()
    { 
      File dir  = new File(cheminDossierAgentDetails);
      File[] liste = dir.listFiles();
      for(File file : liste){
    	  
    	  String identifiantEmployeAvecExtension = file.getName();
    	  String identifiantEmploye = identifiantEmployeAvecExtension;
    	  
			BufferedImage myPicture = null;
			try {
				myPicture = ImageIO.read(file);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			ImageIcon carteIdentite = new ImageIcon(myPicture);
    	  
			listeCartesIdentite.put(identifiantEmploye, carteIdentite);
          
  		}
    	  
      return listeCartesIdentite;
    } 
}
