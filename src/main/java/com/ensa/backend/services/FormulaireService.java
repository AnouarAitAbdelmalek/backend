package com.ensa.backend.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensa.backend.entities.Creance;
import com.ensa.backend.entities.Formulaire;
import com.ensa.backend.exceptions.NotFoundException;
import com.ensa.backend.repositories.CreanceRepository;
import com.ensa.backend.repositories.FormulaireRepository;




@Service
public class FormulaireService {
	
	@Autowired
	CreanceRepository creanceRepository;
	
	@Autowired
	FormulaireRepository rep;
	
	public void addFormulaire(Formulaire formulaire) {
		
		Creance creance =  creanceRepository.findById(formulaire.getCreance().getId())
				.orElseThrow(() -> new NotFoundException("Aucune formulaire avec l'id "+formulaire.getCreance().getId()+" trouvée"));
		formulaire.setCreance(creance);;
		rep.save(formulaire);
	}
	
	public Formulaire getFormulaire(Long id)
	{
		return rep.findById(id).orElseThrow(() -> new NotFoundException("Aucune formulaire avec l'id "+id+" trouvé"));
	}
	
	public List<Formulaire> getAll()
	{
		List<Formulaire> Formulaires = rep.findAll();
		return Formulaires;
	}
	
	public void removeFormulaire(Long id) {
		rep.delete(rep.findById(id).orElseThrow(() -> new NotFoundException("Aucune formulaire avec l'id "+id+" trouvé")));
	}
	
	
	public List<Formulaire> getByCreance(Long id)
	{
		Creance creance = creanceRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Aucune formulaire avec l'id "+id+" trouvée"));
		return rep.findAllByCreance(creance);
	}
	
	
	
}
