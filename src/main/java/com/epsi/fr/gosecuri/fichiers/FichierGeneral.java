package com.epsi.fr.gosecuri.fichiers;

import java.io.FileWriter;
import java.io.IOException;

public class FichierGeneral {
	
	String cheminFichier = "";
	
	public FichierGeneral(String nom, String cheminDossier, String extensionHtml, StringBuilder body) {
		
		cheminFichier = cheminDossier + "/" + nom + extensionHtml;
		
	    try {
	        FileWriter myWriter = new FileWriter(cheminFichier);
	        
	        myWriter.write(body.toString());
	        myWriter.close();
	        
	      } 
	      catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	}
}
