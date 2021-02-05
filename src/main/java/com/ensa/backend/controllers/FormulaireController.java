package com.ensa.backend.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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
import com.ensa.backend.entities.Formulaire;
import com.ensa.backend.services.FormulaireService;



@CrossOrigin(origins = "*")
@RestController
public class FormulaireController {
	
	@Autowired
	FormulaireService service;
	
	
	@PostMapping("/formulaire")
	@ResponseStatus(HttpStatus.CREATED)
	public void addFormulaire(@RequestBody Formulaire formulaire) {
		
		service.addFormulaire(formulaire);
	}
	
	@GetMapping("/formulaire")
	@ResponseStatus(HttpStatus.OK)
	public Formulaire getFormulaire(@RequestParam(name="id") Long id)
	{
		return service.getFormulaire(id);
	}
	
	
	@GetMapping("/formulaires")
	@ResponseStatus(HttpStatus.OK)
	public List<Formulaire> getAll()
	{
		return service.getAll();
	}
	
	
	@DeleteMapping("/formulaire/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void removeFormulaire(@PathVariable(name="id") Long id) {
		service.removeFormulaire(id);
	}
	
	@GetMapping("/creance/{id}/formulaires")
	@ResponseStatus(HttpStatus.OK)
	public List<Formulaire> getByCreance(@PathVariable(name="id") Long id)
	{
		return service.getByCreance(id);
	}
	
	
	
}
