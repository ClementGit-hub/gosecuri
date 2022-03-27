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
	
	public static final String cheminDossierAgentDetails = Commun.cheminDossierGitGoSecuri + "/AgentDetails";
	
	private Map<String, Materiel> listeMateriel = new ListeMateriel().getListeMateriel();
	private Map<String, Agent> listeAgentDetails = recupListeAgentsDetails();

	public Agents() {

	}
	
	public Map<String, Agent> recupListeAgentsDetails()
    { 
	
		Map<String, Agent> listeAgent = new HashMap<String, Agent>();
	
      File dir  = new File(cheminDossierAgentDetails);
      File[] liste = dir.listFiles();
      
      for(File file : liste){
    	  
    	  String identifiantEmploye = getFileNameWithoutExtension(file.getName());
    	  
    	  System.out.println("identifiantEmploye : "+ identifiantEmploye);

    	  listeAgent.put(identifiantEmploye, recupAgentsDetails(file.getAbsolutePath()));
      }
      return listeAgent;
    }
	
	public static String getFileNameWithoutExtension(String fileName) {
		
		if (fileName.indexOf(".") > 0) {
			return fileName.substring(0, fileName.lastIndexOf("."));
			
		} else {
		   return fileName;
		}
	}
	

	
	public Map<String, Agent> getListeAgentDetails() {
		return listeAgentDetails;
	}

	public Map<String, Materiel> getListeMateriel() {
		return listeMateriel;
	}

	private Agent recupAgentsDetails(String cheminFichier) {
		
		String identifiantEmploye = "";
		String nom = "";
		String prenom = "";
		String poste = "";
		String motDePasse = "";
		
		List<Materiel> listeMaterielAgent = new ArrayList<>();
		
		String nomFichier = new File(cheminFichier).getName();
		identifiantEmploye = nomFichier.substring(0, nomFichier.lastIndexOf('.'));
		
		String cheminCarteIdentite = Commun.cheminDossierGitGoSecuri + "/Identities/" + identifiantEmploye + ".jpg";
		
		System.out.println("cheminCarteIdentite : " + cheminCarteIdentite);
		
		CarteIdentite carteIdentite = new CarteIdentite(cheminCarteIdentite);
		
        try(BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) 
        {
            String line;
            
            int i = 0;
            
            while ((line = br.readLine()) != null) {
            	
            	if(line.replace(" ","").length() == 0) {
            		continue;
            	}
            	
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
						break;
				}
            	
        		if(i > 3) {
        			
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
        
        return agent; 
   
	}
	

}
