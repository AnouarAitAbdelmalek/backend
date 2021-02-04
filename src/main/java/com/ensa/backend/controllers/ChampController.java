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

import com.ensa.backend.entities.Champ;
import com.ensa.backend.services.ChampService;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ChampController {
	
	@Autowired
	ChampService service;
	
	@PostMapping("/champ")
	@ResponseStatus(HttpStatus.CREATED)
	public void addChamp(@RequestBody Champ champ)
	{
		service.addChamp(champ);
	}
	
	
	@GetMapping("/champ")
	@ResponseStatus(HttpStatus.OK)
	public Champ getChamp(@RequestParam(name="id", required=false) Long id)
	{
		return service.getChamp(id);
	}
	
	
	@GetMapping("/champs")
	@ResponseStatus(HttpStatus.OK)
	public List<Champ> getAll()
	{
		return service.getAll();
	}
	
	
	@DeleteMapping("/champ/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void removeChamp(@PathVariable(name="id") Long id) {
		service.removeChamp(id);
	}
	
	@GetMapping("/formulaire/{id}/champs")
	@ResponseStatus(HttpStatus.OK)
	public List<Champ> getByFormulaire(@PathVariable(name="id") Long id )
	{
		return service.getByFormulaire(id);
	}
	
	
	
}
