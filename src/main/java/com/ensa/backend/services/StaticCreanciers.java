package com.ensa.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensa.backend.entities.Creancier;

@Service
public class StaticCreanciers {
	
	@Autowired
	CreancierService creancierService;
	
	@Autowired
	CreanceService creanceService;
	
	@Autowired
	FormulaireService formulaireService;
	
	@Autowired
	ChampService champService;
	
	public void addCreanciers() {
		
		Creancier a1 = new Creancier();
		
		System.out.println("khaawi");
		
		
	}
}
