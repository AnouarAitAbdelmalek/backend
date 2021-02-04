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

import com.ensa.backend.entities.Creancier;
import com.ensa.backend.services.CreancierService;




@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CreancierController {
	
	
	@Autowired
	CreancierService service;
	
	
	@PostMapping("/creancier")
	@ResponseStatus(HttpStatus.CREATED)
	public void addCreancier(@RequestBody Creancier creancier) {
		service.addCreancier(creancier);
	}
	
	@GetMapping("/creancier")
	@ResponseStatus(HttpStatus.OK)
	public Creancier getCreancier(@RequestParam(name="id") Long id)
	{
		return service.getCreancier(id);
	}
	
	
	@GetMapping("/creanciers")
	@ResponseStatus(HttpStatus.OK)
	public List<Creancier> getAll()
	{
		return service.getAll();
	}
	
	
	@DeleteMapping("/creancier/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void removeCreancier(@PathVariable(name="id") Long id) {
		service.removeCreancier(id);
	}
	
	
	@GetMapping("/creancier/{code}")
	@ResponseStatus(HttpStatus.OK)
	public Creancier getByCode(@PathVariable(name="code") String code)
	{
		return service.getByCode(code);
	}
	
}
