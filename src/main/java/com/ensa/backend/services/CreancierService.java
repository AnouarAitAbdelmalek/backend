package com.ensa.backend.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensa.backend.entities.Creance;
import com.ensa.backend.entities.Creancier;
import com.ensa.backend.exceptions.AlreadyExistsException;
import com.ensa.backend.exceptions.NotFoundException;
import com.ensa.backend.repositories.CreancierRepository;




@Service
public class CreancierService {
	
	@Autowired
	CreancierRepository rep;
	
	public void addCreancier(Creancier creancier) {
		if(rep.findByCode(creancier.getCode()).isPresent()) {
			throw new AlreadyExistsException("Veuillez choisir un autre Code");
		}
		
		rep.save(creancier);
	}
	
	public Creancier getCreancier(Long id)
	{
		return rep.findById(id).orElseThrow(() -> new NotFoundException("Aucun créancier avec l'id "+id+" trouvé"));
	}
	
	public List<Creancier> getAll()
	{
		List<Creancier> creanciers = rep.findAll();
		return creanciers;
	}
	
	public void removeCreancier(Long id) {
		rep.delete(rep.findById(id).orElseThrow(() -> new NotFoundException("Aucun créancier avec l'id "+id+" trouvé")));
	}
	
	public Creancier getByCode(String code)
	{
		return rep.findByCode(code).orElseThrow(() -> new NotFoundException("Aucune créance avec le code "+code+" trouvé"));
	}
	
}
