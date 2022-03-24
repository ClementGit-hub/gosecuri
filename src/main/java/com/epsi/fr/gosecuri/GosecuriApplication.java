package com.epsi.fr.gosecuri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.epsi.fr.gosecuri.fonctions.Recuperation;

@SpringBootApplication
public class GosecuriApplication {

	public static void main(String[] args) {
//		SpringApplication.run(GosecuriApplication.class, args);
		
		new Recuperation();
	}

}
