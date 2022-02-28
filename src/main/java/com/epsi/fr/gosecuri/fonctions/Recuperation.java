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

import com.epsi.fr.gosecuri.fichiers.PageHtml;
import com.epsi.fr.gosecuri.models.personnes.Agent;
import com.epsi.fr.gosecuri.models.personnes.materiels.Materiel;

public class Recuperation {
	
	public Map<String, Materiel> listeMateriel = new HashMap<String, Materiel>();

	public List<String> listePersonnes = new ArrayList<String>();
	
	public Map<String, Agent> listeAgentDetails = new HashMap<String, Agent>();
	
	public Map<String, ImageIcon> listeCarteIdentite = new HashMap<String, ImageIcon>();
	
	public Recuperation(String cheminFichier) {
		
		//StringBuffre, Stream
		
		cheminFichier = "gosecuri/src/main/resources/static/fichiersTest/git/GoSecuri-master/";
				
		String cheminMateriels = "Materials";
		
		//Test chemin d'accès si / transformer en \\
		
		String nomFichier = "liste.txt";
		
		String cheminMateriel = initChemRecupFichier(cheminFichier, cheminMateriels, nomFichier);
		
		cheminMateriel = "..\\gosecuri\\src\\main\\resources\\static\\fichiersTest\\git\\GoSecuri-master\\Materials\\liste.txt";
				
		listeMateriel = recupMateriel(cheminMateriel);
		
        for (Entry<String, Materiel> mapentry : listeMateriel.entrySet()) {
        	System.out.println("clé: "+mapentry.getKey() + " | valeur: " + mapentry.getValue().getNom()+ " | valeur: " + mapentry.getValue().getType());
        
		}
        
        String cheminListeAgents = "..\\gosecuri\\src\\main\\resources\\static\\fichiersTest\\git\\GoSecuri-master\\AgentsList\\staff.txt";
        
        listePersonnes = recupListeEmployes(cheminListeAgents);
		
        for (String identifiantPersonne : listePersonnes) {
        	System.out.println("valeur: " + identifiantPersonne);
        
		}
        
        String cheminDossierAgentDetails = "..\\gosecuri\\src\\main\\resources\\static\\fichiersTest\\git\\GoSecuri-master\\AgentDetails";
        
        listeAgentDetails = recupListeAgentsDetails(cheminDossierAgentDetails);
		
        for (String identifiantPersonne : listePersonnes) {
        	System.out.println("valeur: " + identifiantPersonne);
        
		}
        
        String cheminDossierCartesIdentites = "..\\gosecuri\\src\\main\\resources\\static\\fichiersTest\\git\\GoSecuri-master\\Identities";
        
        listeCarteIdentite = recupListeCartesIdentite(cheminDossierCartesIdentites);
	
        String cheminDossierFichierHtml = "C:\\Users\\escan\\eclipse-workspace\\gosecuri\\gosecuri\\src\\main\\resources\\static\\fichiersTest\\html\\";
        
        for (Entry<String, Agent> mapentry : listeAgentDetails.entrySet()) {
        	
        	System.out.println("mapentry : "+mapentry.getValue().getIdentifiantEmploye());
        	
        	creationFichierHtml(cheminDossierFichierHtml, mapentry.getValue());
        	
		}
        
        //Page d'acceuil
        PageHtml pageHtml = new PageHtml();
        pageHtml.bodyAcceuil();
        
        
	}
	
	
	private String initChemRecupFichier(String cheminFichier, String cheminMateriels, String nomFichier) {
		return "";
	}
	
	private File creationFichierHtml(String chemin, Agent agent) {
		
		String identifiantEmploye = agent.getIdentifiantEmploye();
		String extensionHtml = ".html";
		
		System.out.println("ch : "+chemin + identifiantEmploye + extensionHtml);
		
	    try {
	        FileWriter myWriter = new FileWriter(chemin + identifiantEmploye + extensionHtml);
	        
	        PageHtml pageAgent = new PageHtml();
	        
	        myWriter.write(pageAgent.toString());
	        myWriter.close();
	        
	      } 
	      catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
		
		return null;
		
	}
	
	//A revoir
	public Map<String, ImageIcon> recupListeCartesIdentite(String cheminDossierAgentDetails)
    { 
      File dir  = new File(cheminDossierAgentDetails);
      File[] liste = dir.listFiles();
      for(File file : liste){
    	  
    	  String identifiantEmployeAvecExtension = file.getName();
    	  
    	  System.out.println("c identifiantEmployeAvecExtension : "+identifiantEmployeAvecExtension);
    	  
    	  String identifiantEmploye = identifiantEmployeAvecExtension;
    	  
    	  System.out.println("c identifiantEmploye : "+identifiantEmploye);
    	  
    	  System.out.println("c file.getAbsolutePath() : "+file.getAbsolutePath());
    	  
			BufferedImage myPicture = null;
			try {
				myPicture = ImageIO.read(file);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			ImageIcon carteIdentite = new ImageIcon(myPicture);
    	  
    	  listeCarteIdentite.put(identifiantEmploye, carteIdentite);
          
  		}
    	  
      return listeCarteIdentite;
    } 
	
	
	
	
	
	public Map<String, Agent> recupListeAgentsDetails(String cheminDossierAgentDetails)
    { 
      File dir  = new File(cheminDossierAgentDetails);
      File[] liste = dir.listFiles();
      for(File file : liste){
//        if(item.isFile())
//        { 
//          System.out.format("Nom du fichier: %s%n", item.getName()); 
//        } 
//        else if(item.isDirectory())
//        {
//          System.out.format("Nom du répertoir: %s%n", item.getName()); 
//        } 
    	  
    	  String identifiantEmployeAvecExtension = file.getName();
    	  
    	  System.out.println("identifiantEmployeAvecExtension : "+identifiantEmployeAvecExtension);
    	  
//  afoley.txt -> afoley  
    	  
// Test avec le staff ??
    	  
    	  String identifiantEmploye = identifiantEmployeAvecExtension;
    	  
    	  System.out.println("identifiantEmploye : "+identifiantEmploye);
    	  
    	  
    	  
    	  System.out.println("file.getAbsolutePath() : "+file.getAbsolutePath());
    	  
    	  listeAgentDetails.put(identifiantEmploye, recupAgentsDetails(file.getAbsolutePath()));
      }
      return listeAgentDetails;
    } 
	
	private Agent recupAgentsDetails(String cheminFichier) {
		
		String identifiantEmploye = "afoley";
		String nom = null;
		String prenom = null;
		String poste = null;
		String motDePasse = null;
		
		List<Materiel> listeMaterielAgent = new ArrayList<>();
		
		System.out.println("cheminFichier : "+cheminFichier);
		
		//Per
		cheminFichier = cheminFichier.replace("/", File.separator);
		
		System.out.println("cheminFichier : "+cheminFichier);
		
		String cheminCarteIdentite = "..\\gosecuri\\src\\main\\resources\\static\\fichiersTest\\git\\GoSecuri-master\\Identities\\afoley.jpg";
		
//		String cheminCarteIdentite = cheminFichier;
		
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File(cheminCarteIdentite));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ImageIcon carteIdentite = new ImageIcon(myPicture);
		
        try(BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) 
        {
            String line;
            
            //A revoir
            int i = 0;
            
            while ((line = br.readLine()) != null) {
            	
            	System.out.println(" i : "+i);
            	
            	if(line.replace(" ","").length() == 0) {
            		continue;
            	}
            	
            	switch (i) {
					case 0:	nom = line;
					i ++;
						continue;
						
					case 1:	prenom = line;
					i ++;
						continue;
						
					case 2:	poste = line;
					i ++;
						continue;
						
					case 3:	motDePasse = line.replace(" ","");
					i ++;
						continue;

					default:	System.out.println("i non pris en compte");
						break;
				}
            	

        		listeMaterielAgent.add(listeMateriel.get(line));
            	
            	i ++;
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        //A enlever !!
        System.out.println(" Agent.toString"+new Agent(identifiantEmploye, nom, prenom, poste, motDePasse, carteIdentite, listeMaterielAgent).toString());
        
        return new Agent(identifiantEmploye, nom, prenom, poste, motDePasse, carteIdentite, listeMaterielAgent);
   
	}
	
	private List<String> recupListeEmployes(String cheminFichier) {
		
		List<String> listePersonnes = new ArrayList<String>();
               
        System.out.println("cheminFichier : "+cheminFichier);
        
        try(BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) 
        {
            String line;
            while ((line = br.readLine()) != null) {
            	
            	line = line.replace(" ","");
            	
            	System.out.println(line);
            	
            	listePersonnes.add(line);
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        return listePersonnes;
   
	}
	
	private Map<String, Materiel> recupMateriel(String cheminFichier) {
		
		Map<String, Materiel> listeMateriel = new HashMap<String, Materiel>();
        
//        System.out.println("cheminFichier : "+cheminFichier);
//        
//        cheminFichier.replace("/", "\\");
//        
        System.out.println("cheminFichier : "+cheminFichier);
        
        try(BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) 
        {
            String line;
            while ((line = br.readLine()) != null) {
            	
            	System.out.println(line);
            	
            	int indicePremiereMajuscule = indicePremiereMajuscule(line);
            	
            	System.out.println("indicePremiereMajuscule : "+indicePremiereMajuscule);
            	
            	if(indicePremiereMajuscule == -1) {
            		System.out.println("Erreur : indicePremiereMajuscule");
//            		throw new Exception("indicePremiereMajuscule"); ???
            		continue;
            	}
            	
            	String nomMateriel = line.substring(0, indicePremiereMajuscule);
            	String typeMateriel = line.substring(indicePremiereMajuscule, line.length());
            	
            	System.out.println("nomMateriel : "+nomMateriel);
            	System.out.println("typeMateriel : "+typeMateriel);
            	
            	listeMateriel.put(nomMateriel, new Materiel(nomMateriel, typeMateriel));
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        return listeMateriel;
   
	}
	
	
	
	
	public int indicePremiereMajuscule(String chaine) {
		
		for (int indiceChar = 0; indiceChar < chaine.length(); indiceChar++) {
			if(Character.isUpperCase(chaine.charAt(indiceChar))) {
				return indiceChar;
			}	
		}
		
		return -1;
	}

}
