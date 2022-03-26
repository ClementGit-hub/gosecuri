package com.epsi.fr.gosecuri.fichiers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// voir ce github pour l'améliorer
//https://github.com/xmlet/HtmlFlow
public class PageHtml {
	
	// Base
//	private String head = "head";
	private String titrePage = "GOsecuri";
//	private String linkCss = "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css";
	private String body = "Hello world";
//	private String heading = "heading";
//	private String titreCorps = "Title: ";
//	private String description = "description";
//	private String priority = "priority";
	
	// Creation html
	private String nom = "";
	private String cheminDossierFichierHtml = "";
	private String extensionHtml = ".html";
	
	public PageHtml(String nomFichierHtml, String cheminDossierFichierHtml) {
		this.nom = nomFichierHtml;
		this.cheminDossierFichierHtml = cheminDossierFichierHtml;
	}

	public String toString() {
		
		StringBuilder maquette = new StringBuilder();
		
		maquette.append("<!DOCTYPE html>");
		maquette.append("\n");
		maquette.append("<html lang=\"en\" >");
		maquette.append("\n");
		maquette.append("<head>");
		maquette.append("\n");
		maquette.append("<meta charset=\"UTF-8\">");
		maquette.append("\n");
//		maquette.append("<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap\"/>");
//		maquette.append("<link rel=\"icon\" type=\"image/png\" href=\"Images/Logo.png\" />");
		maquette.append("<title>"+titrePage+"</title>");
		maquette.append("\n");
		maquette.append("</head>");
		maquette.append("\n");
		maquette.append(body);
		maquette.append("\n");
		maquette.append("</html>");
//		maquette.append("<link rel=\"stylesheet\" href=\"CSS.css\">");
		
		return maquette.toString();
	}
	
	public File creationFichierHtml() {
		
		String chemin = cheminDossierFichierHtml + "\\" + nom + extensionHtml;
		
	    try {
	        FileWriter myWriter = new FileWriter(chemin);
	        
	        myWriter.write(this.toString());
	        myWriter.close();
	        
	        return new File(chemin);
	        
	      } 
	      catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	    
		return null;
		
	}

	public void setBody(String body) {
		this.body = body;
	}

}
