package com.ensa.backend.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensa.backend.entities.Creance;
import com.ensa.backend.entities.Impaye;
import com.ensa.backend.exceptions.NotFoundException;
import com.ensa.backend.repositories.CreanceRepository;
import com.ensa.backend.repositories.ImpayeRepository;





@Service
public class ImpayeService {
	
	@Autowired
	ImpayeRepository rep;
	
	@Autowired
	CreanceRepository creanceRepository;
	
	
	public void addImpaye(Impaye impaye) {
		Creance creance = creanceRepository.findById(impaye.getCreance().getId())
				.orElseThrow(() -> new NotFoundException("Aucune créance avec l'id "+impaye.getCreance().getId()+" trouvée"));
		
		impaye.setCreance(creance);
		
		rep.save(impaye);
	}
	
	
	public Impaye getImpaye(Long id) {
		return rep.findById(id).orElseThrow(() -> new NotFoundException("Aucune impaye avec l'id "+id+" trouvée"));
				
	}
	
	public List<Impaye> getAll() {
		List<Impaye> impayes = rep.findAll();
		
		return impayes;
				
	}
	
	public List<Impaye> getByRefAndCreance(Long id, String reference) {
		
		Creance creance = creanceRepository.findById(id)
		.orElseThrow(() -> new NotFoundException("Aucune formulaire avec l'id "+id+" trouvée"));
		return rep.findAllByCreanceAndReference(creance, reference);
				
	}
	
	public void removeImpaye(Long id) {
		rep.delete(rep.findById(id).orElseThrow(() -> new NotFoundException("Aucun impayé avec l'id "+id+" trouvé")));
	}
	
}
