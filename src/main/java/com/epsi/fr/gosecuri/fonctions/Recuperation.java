package com.epsi.fr.gosecuri.fonctions;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.springframework.batch.item.util.FileUtils;

import com.epsi.fr.gosecuri.commun.Commun;
import com.epsi.fr.gosecuri.fichiers.PageHtml;
import com.epsi.fr.gosecuri.models.personnes.Agent;
import com.epsi.fr.gosecuri.models.personnes.listes.Agents;
import com.epsi.fr.gosecuri.models.personnes.listes.ListeMateriel;
import com.epsi.fr.gosecuri.models.personnes.materiels.Materiel;

public class Recuperation {
	
	public List<String> listePersonnes = new ArrayList<String>();
	public Agents agents = new Agents();
	
	// La flemme de mettre tout en maj
	// Faudrait soit le mettre dans un fichier xml soit dans une classe ou interface dédié
    public static final String cheminDossierFichierHtml = "C:\\Users\\escan\\eclipse-workspace\\gosecuri\\gosecuri\\src\\main\\resources\\static\\fichiersTest\\html\\";
	
    //Type page
    public static final String ACCUEIL_STR = "ACCUEIL";
    
	public Recuperation() {
		
		String cheminFichier = "gosecuri/src/main/resources/static/fichiersTest/git/GoSecuri-master/";
		
		//Test chemin d'accès si / transformer en \\
		
		String nomFichier = "liste.txt";
		
//		String cheminMateriel = initChemRecupFichier(cheminFichier, cheminMateriel, nomFichier);
        
        String identifiantEmploye = "";
        // Non utilisé pour les tests/maintenance
        List<File> listeFileAgent = new ArrayList<>();
        
        for (Entry<String, Agent> mapentry : agents.getListeAgentDetails().entrySet()) {
        	
        	identifiantEmploye = mapentry.getValue().getIdentifiantEmploye();
        	
        	System.out.println("mapentry : " + identifiantEmploye);
        	
    		PageHtml pageAgent = new PageHtml(identifiantEmploye, cheminDossierFichierHtml);
    		pageAgent.setBody(this.bodyAgent(mapentry.getValue()));
    		listeFileAgent.add(pageAgent.creationFichierHtml());
        	
		}
//        
//		String nomAccueil = "acceuil";
//		
//		// pas le temps mais il faudrait faire une interface PageHTML puis des pages AccueilHTML, etc... qui l'implémente
//		PageHtml pageAccueil = new PageHtml(nomAccueil, cheminDossierFichierHtml);
//		pageAccueil.setBody(this.bodyAcceuil());
//		// Non utilisé pour les tests/maintenance
//		File pageAcceuil  = pageAccueil.creationFichierHtml();
		
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
			
			ph = "            <a href=\"agent.html\"><li>" + mapentry.getValue().nom + "</li></a>\r\n";
			modif.append(ph);
		}
		
		retour.append("<body>\r\n"
				+ "    <div class=\"background\">\r\n"
				+ "        <div class=\"shape\" ></div>\r\n"
				+ "        <div class=\"shape\"></div>\r\n"
				+ "    </div>\r\n"
				+ "    <div class=\"container-list\">\r\n"
				+ "        <div class=\"Logo\"><img src=\"Images/Logo.png\" /></div>\r\n"
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
		String ph = "";
		
//		String cheminImages  = "/gosecuri/src/main/resources/static/fichiersTest/git/GoSecuri-master/Images";
//		String cheminImages = agent.getCarteIdentite().getCheminAcces();
		String cheminImages = "/gosecuri/src/main/resources/static/fichiersTest/git/GoSecuri-master/Images/Logo.png";
		
		int i = 0;
		
		System.out.println("agent : " + agent.getNom());
		System.out.println("agent.getListeMateriel() : " + agent.getListeMateriel().toString());
		
		
		for (Entry<String, Materiel> mapentry : agents.getListeMateriel().entrySet()) {
			
//			System.out.println("materiel : " + mapentry.getValue().getNom());
			
//			System.out.println("Liste Materiel Agent : " + agent.listeMateriel.get(i));
			
			String nomMateriel =  mapentry.getValue().getNom();
			
			modif.append("<li>" + nomMateriel);

			if(bMaterielExiste(nomMateriel, agent)) {
				modif.append(" <img src=\\" + cheminImages + "/check-square-regular.svg\" alt=\"check\" class=\"check-icon\"> 1");
	
//				ph += " <img src=\"Images/check-square-regular.svg\" alt=\"check\" class=\"check-icon\"></li>";
			} else {
				modif.append(" <img src=\\" + cheminImages + "/check-square-regular.svg\" alt=\"check\" class=\"check-icon\"> 2");
				
//				ph += " <img src=\"Images/check-square-regular.svg\" alt=\"check\" class=\"check-icon\"></li>";
				
			}
			
			modif.append("</li>");
			
			i++;
		}
		
//		System.out.println("nom agent : " + agent.getPrenomNom());
//		System.out.println("agent.getIdentifiantEmploye() : " + agent.getIdentifiantEmploye());
		
		String cheminAccesCarteId = agent.getCarteIdentite().getCheminAcces();
//		String cheminAccesCarteId = "../../../../../src/main/resources/static/fichiersTest/git/GoSecuri-master/Identities/afoley.jpg";
		
//		String cheminAccesCarteId = "..\\gosecuri\\gosecuri\\src\\main\\resources\\static\\fichiersTest\\git\\GoSecuri-master\\Images\\Logo.png";
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
