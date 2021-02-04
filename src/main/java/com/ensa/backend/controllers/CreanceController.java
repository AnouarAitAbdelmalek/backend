package com.ensa.backend.controllers;


import java.util.List;

import org.apache.catalina.valves.CrawlerSessionManagerValve;
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
import com.ensa.backend.services.CreanceService;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CreanceController {
	
	@Autowired
	CreanceService service;
	
	@PostMapping("/creance")
	@ResponseStatus(HttpStatus.CREATED)
	public void addCreance(@RequestBody Creance creance) {
		service.addCreance(creance);	
	}
	
	@GetMapping("/creance")
	@ResponseStatus(HttpStatus.OK)
	public Creance getCreance(@RequestParam(name="id", required=false)  Long id)
	{
		return service.getCreance(id);
	}
	
	@GetMapping("/creances")
	@ResponseStatus(HttpStatus.OK)
	public List<Creance> getAll()
	{
		return service.getAll();
	}
	
	@DeleteMapping("/creance/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void removeCreance(@PathVariable(name="id") Long id) {
		service.removeCreance(id);
	}
	
	@GetMapping("/creance/{code}")
	@ResponseStatus(HttpStatus.OK)
	public Creance getByCode(@PathVariable(name="code") String code)
	{
		return service.getByCode(code);
	}
	
	
	@GetMapping("/creancier/{id}/creances")
	@ResponseStatus(HttpStatus.OK)
	public List<Creance> getByCreancier(@PathVariable(name="id") Long id)
	{
		return service.getByCreancier(id);
	}
	
	
}
