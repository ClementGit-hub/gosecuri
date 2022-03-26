package com.epsi.fr.gosecuri.models.personnes.listes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epsi.fr.gosecuri.commun.Commun;
import com.epsi.fr.gosecuri.models.personnes.Agent;
import com.epsi.fr.gosecuri.models.personnes.documents.CarteIdentite;
import com.epsi.fr.gosecuri.models.personnes.materiels.Materiel;

public class Agents {
	
	public static final String cheminDossierAgentDetails = Commun.cheminDossierRessource + "\\AgentDetails";
	
	private Map<String, Materiel> listeMateriel = new ListeMateriel().getListeMateriel();
	private Map<String, Agent> listeAgentDetails = recupListeAgentsDetails();
//	private Map<String, ImageIcon> listeCartesIdentite = new ListeCartesIdentite(cheminDossierAgentDetails).getListeCartesIdentite();

	public Agents() {

	}
	
	public Map<String, Agent> recupListeAgentsDetails()
    { 
	
		Map<String, Agent> listeAgent = new HashMap<String, Agent>();
	
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
    	  
//    	  System.out.println("identifiantEmployeAvecExtension : "+identifiantEmployeAvecExtension);
    	  
// Test avec le staff ??
    	  
    	  String identifiantEmploye = identifiantEmployeAvecExtension;
    	  
//    	  System.out.println("identifiantEmploye : "+identifiantEmploye);
//    	  System.out.println("file.getAbsolutePath() : "+file.getAbsolutePath());
    	  
    	  listeAgent.put(identifiantEmploye, recupAgentsDetails(file.getAbsolutePath()));
      }
      return listeAgent;
    }
	
	public Map<String, Agent> getListeAgentDetails() {
		return listeAgentDetails;
	}

	public Map<String, Materiel> getListeMateriel() {
		return listeMateriel;
	}

//	public Map<String, ImageIcon> getListeCartesIdentite() {
//		return listeCartesIdentite;
//	}

	// A modifier image !!!!
	private Agent recupAgentsDetails(String cheminFichier) {
		
		String identifiantEmploye = "";
		String nom = "";
		String prenom = "";
		String poste = "";
		String motDePasse = "";
		
		List<Materiel> listeMaterielAgent = new ArrayList<>();
		
//		System.out.println("cheminFichier : "+cheminFichier);
		
		// Vraiment bofbof
		String nomFichier = new File(cheminFichier).getName();
		identifiantEmploye = nomFichier.substring(0, nomFichier.lastIndexOf('.'));
		
//		System.out.println("nom fichier : "+identifiantEmploye);
		
		//Per
		cheminFichier = cheminFichier.replace("/", File.separator);
		
		System.out.println("cheminFichier : "+cheminFichier);
		
//		String cheminCarteIdentite = "C:/Users/escan/eclipse-workspace/gosecuri/gosecuri/src/main/resources/static/fichiersTest/git/GoSecuri-master/Identities/" + identifiantEmploye + ".jpg";
		String cheminCarteIdentite = Commun.cheminDossierRessource + "\\Identities\\" + identifiantEmploye + ".jpg";
//		String cheminCarteIdentite = cheminFichier;
		
		//A revoir pour le moment aucune idée
//		BufferedImage myPicture = null;
//		try {
//			myPicture = ImageIO.read(new File(cheminCarteIdentite));
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		ImageIcon carteIdentite = new ImageIcon(myPicture);
		
		CarteIdentite carteIdentite = new CarteIdentite(cheminCarteIdentite);
		
        try(BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) 
        {
            String line;
            
            //A revoir
            int i = 0;
            
            while ((line = br.readLine()) != null) {
            	
//            	System.out.println(" line  : " + line);
//            	System.out.println(" index : "+i);
            	
            	if(line.replace(" ","").length() == 0) {
            		continue;
            	}
            	
//            	System.out.println("passe" );
            	
            	// Remplie les paramètres par rapport aux lignes
            	switch (i) {
					case 0:	nom = line;
						break;
						
					case 1:	prenom = line;
						break;
						
					case 2:	poste = line;
						break;
						
					case 3:	motDePasse = line.replace(" ","");
						break;

					default:	
//						System.out.println("i non pris en compte");
						break;
				}
            	
        		if(i > 3) {
        			
//        	        for (Entry<String, Materiel> mapentry : this.listeMateriel.entrySet()) {
//        	        	System.out.println("yop" + mapentry.getValue().toString());
//        	        
//        			}
        	        
//        	        System.out.println(this.listeMateriel.get(line));
        			
        			listeMaterielAgent.add(this.listeMateriel.get(line));
        			
        		}
            	
            	i ++;
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        Agent agent = new Agent(identifiantEmploye, nom, prenom, poste, motDePasse, carteIdentite, listeMaterielAgent);
        
//        System.out.println("Agent : " + agent.toString());
//        System.out.println("Agent : " + listeMaterielAgent.toString());
        
        return agent; 
   
	}
	

}
