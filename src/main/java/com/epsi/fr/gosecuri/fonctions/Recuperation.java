package com.epsi.fr.gosecuri.fonctions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.epsi.fr.gosecuri.commun.Commun;
import com.epsi.fr.gosecuri.fichiers.PageHtml;
import com.epsi.fr.gosecuri.models.personnes.Agent;
import com.epsi.fr.gosecuri.models.personnes.listes.Agents;
import com.epsi.fr.gosecuri.models.personnes.materiels.Materiel;

public class Recuperation {
	
	public List<String> listePersonnes = new ArrayList<String>();
	public Agents agents = new Agents();
	
	// La flemme de mettre tout en maj
	// Faudrait soit le mettre dans un fichier xml soit dans une classe ou interface dédié
//    public static final String cheminDossierFichierHtml = "C:\\Users\\escan\\eclipse-workspace\\gosecuri\\gosecuri\\src\\main\\resources\\static\\fichiersTest\\html\\";
	
    //Type page
    public static final String ACCUEIL_STR = "ACCUEIL";
    
	public Recuperation() {
		
        String identifiantEmploye = "";
        
        // Non utilisé pour les tests/maintenance
        List<File> listeFileAgent = new ArrayList<>();
        
        for (Entry<String, Agent> mapentry : agents.getListeAgentDetails().entrySet()) {
        	
        	identifiantEmploye = mapentry.getValue().getIdentifiantEmploye();
        	
        	System.out.println("mapentry : " + identifiantEmploye);
        	
    		PageHtml pageAgent = new PageHtml(identifiantEmploye, Commun.cheminDossierHtml);
    		pageAgent.setBody(this.bodyAgent(mapentry.getValue()));
    		listeFileAgent.add(pageAgent.creationFichierHtml());
    		
		}
        
		String nomAccueil = "acceuil";
		
		// pas le temps mais il faudrait faire une interface PageHTML puis des pages AccueilHTML, etc... qui l'implémente
		PageHtml pageAccueil = new PageHtml(nomAccueil, Commun.cheminDossierHtml);
		pageAccueil.setBody(this.bodyAcceuil());
		
		// Non utilisé pour les tests/maintenance
		File pageAcceuil  = pageAccueil.creationFichierHtml();
		
		StringBuilder body = new StringBuilder();
		
        for (Entry<String, Agent> mapentry : agents.getListeAgentDetails().entrySet()) {
        	
        	body.append(mapentry.getKey() + ":" + mapentry.getValue().getMotdepasse());
        	body.append("\n");
        }
        
        File fichierMdp = creationFichier(Commun.cheminDossierMdp, "", ".htpasswd", body);
		
		
	}
	
	public File creationFichier(String cheminDossier, String nom, String extensionHtml, StringBuilder body) {
		
		String chemin = cheminDossier + "\\" + nom + extensionHtml;
		
	    try {
	        FileWriter myWriter = new FileWriter(chemin);
	        
	        myWriter.write(body.toString());
	        myWriter.close();
	        
	        return new File(chemin);
	        
	      } 
	      catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	    
		return null;
		
	}
	
//	private File creationFichierAgent(Agent agent) {
//		
//		String identifiantEmploye = agent.getIdentifiantEmploye();
//		String extensionHtml = ".html";
//		
//		System.out.println("ch : "+cheminDossierFichierHtml + identifiantEmploye + extensionHtml);
//		
//	    try {
//	        FileWriter myWriter = new FileWriter(cheminDossierFichierHtml + identifiantEmploye + extensionHtml);
//	        
//	        PageHtml pageAgent = new PageHtml(extensionHtml, extensionHtml);
//	        
//	        myWriter.write(pageAgent.toString());
//	        myWriter.close();
//	        
//	      } 
//	      catch (IOException e) {
//	        System.out.println("An error occurred.");
//	        e.printStackTrace();
//	      }
//		
//		return null;
//	}
	
	// Localisation ???? -> dans classe PageAccueilHTML
	public String bodyAcceuil() {
		
		StringBuffer retour = new StringBuffer();
		StringBuffer modif = new StringBuffer();
		String ph = "";

		for (Entry<String, Agent> mapentry : agents.getListeAgentDetails().entrySet()) {
			
			System.out.println("mapentry.getKey() : " + mapentry.getKey());
			
			ph = "            <a href=\"" + mapentry.getKey() +".html\"><li>" + mapentry.getValue().nom + "</li></a>\r\n";
			modif.append(ph);
		}
		
		String cheminLogo = Commun.cheminDossierGitGoSecuri + "\\Images\\Logo.png";
//		+ "        <div class=\"Logo\"><img src=\"Images/Logo.png\" /></div>\r\n"
		
		cheminLogo = "../gosecuri/src/main/resources/static/fichiersTest/git/GoSecuri-master/Images/Logo.png";
		
		System.out.println("cheminLogo : " + cheminLogo);
		
		retour.append("<body>\r\n"
				+ "    <div class=\"background\">\r\n"
				+ "        <div class=\"shape\" ></div>\r\n"
				+ "        <div class=\"shape\"></div>\r\n"
				+ "    </div>\r\n"
				+ "    <div class=\"container-list\">\r\n"
				+ "        <div class=\"Logo\"><img src=" + cheminLogo + " /></div>\r\n"
				+ "        <ul class=\"agents-list\">\r\n"
				+ modif
				+ "        </ul>\r\n"
				+ "    </div>\r\n"
				+ "</body>");
		
		return retour.toString();
	}
	
	// Même que l'accueil a changé !!!!
	public String bodyAgent(Agent agent) {
		
		StringBuffer retour = new StringBuffer();
		StringBuffer modif = new StringBuffer();
		
		String imageSquareCheck = Commun.cheminDossierGitGoSecuri + "\\Images\\check-square-regular.svg";
		
		System.out.println("imageSquareCheck : " + imageSquareCheck);
		
//		imageSquareCheck = "C:\\Users\\escan\\eclipse-workspace\\gosecuri\\gosecuri\\src\\main\\resources\\static\\fichiersTest\\git\\GoSecuri-master\\Images\\check-square-regular.svg";
//		
//		System.out.println("imageSquareCheck : " + imageSquareCheck);
		
		System.out.println("agent : " + agent.getNom());
		System.out.println("agent.getListeMateriel() : " + agent.getListeMateriel().toString());
		
		for (Entry<String, Materiel> mapentry : agents.getListeMateriel().entrySet()) {
			
			String nomMateriel =  mapentry.getValue().getNom();
			
			modif.append("<li>" + nomMateriel);

			if(bMaterielExiste(nomMateriel, agent)) {
				modif.append(" <img src=" + imageSquareCheck + " height=\"20\" width=\"20\"> 1");

			} else {
				modif.append(" <img src=" + imageSquareCheck + " height=\"20\" width=\"20\"> 2");
				
			}
			
			modif.append("</li>");
		}
		
		String cheminAccesCarteId = agent.getCarteIdentite().getCheminAcces();
		System.out.println("chemin carte id : " + cheminAccesCarteId);
		
		retour.append("<body>"
					+ "<div class==\"background=\">\">\r\n"
					+ "<div class=\"shape-agent\" ></div>"
					+ "<div class=\"shape-agent\"></div>"
					+ "</div>"
					+ "<div class=\"container-agent\">"
					+ "<p class=\"agent-name\">"
					+ agent.getPrenomNom()
					+ "</p>"
					+ "<ul class=\"materials-list\">"
					+ modif
					+ "</ul>"
					+ "<div class=\"frame-img\">"
//					+ "<img src=\"Images/" + cheminAccesCarteId + "\" alt=\"Id card\" class=\"id-card\">"
//					+ "<img src=" + cheminAccesCarteId + "\" alt=\"Id card\" class=\"id-card\">"
					+ "<img src=" + cheminAccesCarteId + ">"
					+ "</div>"
					+ "</div>"
					+ "</body>");

		
		return retour.toString();
	}
	
	public boolean bMaterielExiste(String nomMateriel, Agent agent) {
		
		for(Materiel materiel : agent.getListeMateriel()) {
			
			if(nomMateriel.equals(materiel.getNom())) {
				return true;
			}
		}
		
		return false;
	}
	
}
