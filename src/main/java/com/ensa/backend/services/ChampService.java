package com.ensa.backend.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensa.backend.entities.Champ;
import com.ensa.backend.entities.Creance;
import com.ensa.backend.entities.Formulaire;
import com.ensa.backend.exceptions.NotFoundException;
import com.ensa.backend.repositories.ChampRepository;
import com.ensa.backend.repositories.FormulaireRepository;




@Service
public class ChampService {
	
	@Autowired
	ChampRepository rep;
	
	@Autowired
	FormulaireRepository formulaireRepository;
	
	
	public void addChamp(Champ champ)
	{
		Formulaire formulaire = formulaireRepository.findById(champ.getFormulaire().getId())
				.orElseThrow(() -> new NotFoundException("Aucun formulaire avec l'id "+champ.getFormulaire().getId()+" trouvé" ));
		
		champ.setFormulaire(formulaire);
		
		rep.save(champ);
	}
	
	
	public Champ getChamp(Long id)
	{
		return rep.findById(id).orElseThrow(() -> new NotFoundException("Aucun champ avec l'id "+id+" trouvé"));
	}
	
	public List<Champ> getAll()
	{
		List<Champ> Champs = rep.findAll();
		return Champs;
	}
	
	public void removeChamp(Long id) {
		rep.delete(rep.findById(id).orElseThrow(() -> new NotFoundException("Aucun champ avec l'id "+id+" trouvé")));
	}
	
	
	public List<Champ> getByFormulaire(Long id)
	{
		Formulaire formulaire = formulaireRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Aucun formulaire avec l'id "+id+" trouvé" ));
		return rep.findAllByFormulaire(formulaire);
	}
	
	
	
}
