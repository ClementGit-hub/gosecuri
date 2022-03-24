package com.epsi.fr.gosecuri.models.personnes.listes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.epsi.fr.gosecuri.commun.Commun;
import com.epsi.fr.gosecuri.models.personnes.materiels.Materiel;

public class ListeMateriel {
	
	public static final String cheminMateriel = Commun.cheminDossierRessource + "\\Materials\\liste.txt";
	
	private Map<String, Materiel> listeMateriel = new HashMap<String, Materiel>();

	public ListeMateriel() {
		
		listeMateriel = this.recupMateriel(cheminMateriel);
		
		if(listeMateriel == null) {
			System.out.println("Not OK");
		}
		System.out.println("OK");
	}
	
	public Map<String, Materiel> getListeMateriel() {
		return listeMateriel;
	}

	private Map<String, Materiel> recupMateriel(String cheminFichier) {
		
		Map<String, Materiel> listeMateriel = new HashMap<String, Materiel>();
		
//        System.out.println("cheminFichier : "+cheminFichier);
//        
//        cheminFichier.replace("/", "\\");
//        
//        System.out.println("cheminFichier : "+cheminFichier);
        
        try(BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) 
        {
            String line;
            while ((line = br.readLine()) != null) {
            	
            	int indicePremiereMajuscule = indicePremiereMajuscule(line);
            	
            	if(indicePremiereMajuscule == -1) {
            		System.out.println("Erreur : indicePremiereMajuscule");
//            		throw new Exception("indicePremiereMajuscule"); ???
            		continue;
            	}
            	
            	String nomMateriel = line.substring(0, indicePremiereMajuscule).strip();
            	String typeMateriel = line.substring(indicePremiereMajuscule, line.length());
            	
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
