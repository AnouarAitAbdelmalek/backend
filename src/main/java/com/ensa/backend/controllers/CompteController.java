package com.ensa.backend.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ensa.backend.entities.Compte;
import com.ensa.backend.entities.Impaye;
import com.ensa.backend.exceptions.AlreadyExistsException;
import com.ensa.backend.exceptions.NotFoundException;
import com.ensa.backend.services.CompteService;


@CrossOrigin(origins = "*")
@RestController
public class CompteController {
	
	
	CompteService service;
	
	@Autowired
	public CompteController(CompteService service) {
		
		this.service=service;
	}
			
			
			@GetMapping("/compte/{numero}")
			@ResponseStatus(HttpStatus.OK)
			public Compte getById(@RequestParam(name="id", required=false) Long id)
			{
				return service.getById(id);
			}
		
		
		
		//PUT
			
			@PutMapping("/compte/{id}")
			@ResponseStatus(HttpStatus.OK)
			public void alimenterCompte(@PathVariable Long id , @RequestBody(required=false) double montant)  throws NotFoundException, AlreadyExistsException
			{
				service.alimenterSolde(id, montant);
			}
	
		
			
		//DELETE
			
			@DeleteMapping("/compte/{id}")
			@ResponseStatus(HttpStatus.OK)
			public void deleteCompte(@PathVariable Long id) throws NotFoundException
			{
				service.removeCompte(id);
			}
			
			@PutMapping("/compte/{id}/payer")
			@ResponseStatus(HttpStatus.OK)
			public void payerImpayes(@PathVariable Long id, @RequestBody List<Impaye> impayes)
			{
				service.payerImpayes(id, impayes);
			}
			
	

}

