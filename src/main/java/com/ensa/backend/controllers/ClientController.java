package com.ensa.backend.controllers;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.ensa.backend.entities.Client;
import com.ensa.backend.entities.Compte;
import com.ensa.backend.exceptions.AlreadyExistsException;
import com.ensa.backend.exceptions.NotFoundException;
import com.ensa.backend.services.ClientService;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ClientController {
	
	
	ClientService service;
	
	@Autowired
	public ClientController(ClientService service) {
		
		this.service=service;
	}
	
	//GET
			@GetMapping("/clients")
			@ResponseStatus(HttpStatus.OK)
			public List<Client> getClients(@RequestParam(name="id", required=false) Long id) throws NotFoundException
			{
				return service.getClients();
			}
			
			
			@GetMapping("/client")
			@ResponseStatus(HttpStatus.OK)
			public Client getById(@RequestParam(name="id", required=false) Long id)
			{
				return service.getById(id);
			}
			
			@GetMapping("/client/{username}")
			@ResponseStatus(HttpStatus.OK)
			public Client getByUsername(@PathVariable(name="username") String username)
			{
				return service.getByUsername(username);
			}
			
			
			
			@GetMapping("/client/{id}/compte")
			@ResponseStatus(HttpStatus.OK)
			public Compte getComptes(@PathVariable(name="id") Long id) throws NotFoundException
			{
				return service.getById(id).getCompte();
			}
			
		
		
		//POST
			
			@PostMapping("/client")
			@ResponseStatus(HttpStatus.CREATED)
			public void addClient(@RequestBody Client client)  throws AlreadyExistsException
			{
				service.addClient(client);
			}
	
		
			
		//DELETE
			
			@DeleteMapping("/client/{id}")
			@ResponseStatus(HttpStatus.OK)
			public void deleteClient(@PathVariable(name="id") Long id) throws NotFoundException
			{
				service.removeClient(id);
			}
			
			
			@PutMapping("/client/{id}")
			public void newPassword(@PathVariable(name="id") Long id, @RequestBody String password) throws NotFoundException
			{
				service.newPassword(id, password);
				
			}
	

}

