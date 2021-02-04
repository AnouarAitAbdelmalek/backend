package com.ensa.backend.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ensa.backend.entities.Creance;
import com.ensa.backend.entities.Impaye;
import com.ensa.backend.services.ImpayeService;




@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ImpayeController {
	

	@Autowired
	ImpayeService service;
	
	@PostMapping("/impaye")
	@ResponseStatus(HttpStatus.CREATED)
	public void addImpaye(@RequestBody Impaye impaye) {
		service.addImpaye(impaye);
	}
	
	
	@GetMapping("/impaye")
	@ResponseStatus(HttpStatus.OK)
	public Impaye getImpaye(@RequestParam(name="id") Long id) {
		
		return service.getImpaye(id);
	}
	
	
	@GetMapping("/impayes")
	@ResponseStatus(HttpStatus.OK)
	public List<Impaye> getAll() {
		
		return service.getAll();
	}
	
	
	@GetMapping("/creance/{id}/impayes")
	@ResponseStatus(HttpStatus.OK)
	public List<Impaye> getByRefAndCreance(@PathVariable(name="id") Long id, @RequestBody String reference) {
		
		return service.getByRefAndCreance(id, reference);
				
	}
	
	
	@DeleteMapping("/impaye/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void removeImpaye(@PathVariable(name="id") Long id) {
		
		service.removeImpaye(id);
	}
	
}
