package com.epsi.fr.gosecuri.models.personnes.listes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.epsi.fr.gosecuri.commun.Commun;

public class ListeEmployes {
	public static final String cheminListeAgents = Commun.cheminDossierGitGoSecuri + "\\AgentsList\\staff.txt";

	private List<String> listeEmployes = new ArrayList<String>();
	
	public ListeEmployes() {
		listeEmployes = recupListeEmployes(cheminListeAgents);
		
//        for (String identifiantPersonne : listeEmployes) {
//        	System.out.println("valeur: " + identifiantPersonne);
//        
//		}
		
	}
	
	public List<String> getListeEmployes() {
		return listeEmployes;
	}

	private List<String> recupListeEmployes(String cheminFichier) {
		
		List<String> listePersonnes = new ArrayList<String>();
               
//        System.out.println("cheminFichier : "+cheminFichier);
        
        try(BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) 
        {
            String line;
            while ((line = br.readLine()) != null) {
            	
            	line = line.replace(" ","");
            	
//            	System.out.println(line);
            	
            	listePersonnes.add(line);
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        return listePersonnes;
   
	}


}
