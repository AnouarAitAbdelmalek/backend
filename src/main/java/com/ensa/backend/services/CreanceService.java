package com.ensa.backend.services;


import java.util.List;

import org.apache.catalina.valves.CrawlerSessionManagerValve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensa.backend.entities.Creance;
import com.ensa.backend.entities.Creancier;
import com.ensa.backend.exceptions.AlreadyExistsException;
import com.ensa.backend.exceptions.NotFoundException;
import com.ensa.backend.repositories.CreanceRepository;
import com.ensa.backend.repositories.CreancierRepository;




@Service
public class CreanceService {
	
	@Autowired
	CreanceRepository rep;
	
	@Autowired
	CreancierRepository creancierRepository;
	
	public void addCreance(Creance creance) {
		
		if(rep.findByCode(creance.getCode()).isPresent()) {
			throw new AlreadyExistsException("Veuillez choisir un autre Code");
		}
		
		Creancier creancier =  creancierRepository.findById(creance.getCreancier().getId())
				.orElseThrow(() -> new NotFoundException("Aucun créancier avec l'id "+creance.getCreancier().getId()+" trouvé"));
		creance.setCreancier(creancier);
		rep.save(creance);
	}
	
	public Creance getCreance(Long id)
	{
		return rep.findById(id).orElseThrow(() -> new NotFoundException("Aucune créance avec l'id "+id+" trouvé"));
	}
	
	public List<Creance> getAll()
	{
		List<Creance> creances = rep.findAll();
		return creances;
	}
	
	public void removeCreance(Long id) {
		rep.delete(rep.findById(id).orElseThrow(() -> new NotFoundException("Aucune créance avec l'id "+id+" trouvé")));
	}
	
	public Creance getByCode(String code)
	{
		return rep.findByCode(code).orElseThrow(() -> new NotFoundException("Aucune créance avec le code "+code+" trouvé"));
	}
	
	public List<Creance> getByCreancier(Long id)
	{
		Creancier creancier = creancierRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Aucun créancier avec l'id "+id+" trouvé"));
		return rep.findAllByCreancier(creancier);
	}
	
	
}
