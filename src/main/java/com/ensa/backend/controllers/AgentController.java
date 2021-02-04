package com.ensa.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.ensa.backend.entities.Agent;
import com.ensa.backend.exceptions.AlreadyExistsException;
import com.ensa.backend.exceptions.NotFoundException;
import com.ensa.backend.services.AgentService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AgentController {
	
	
	AgentService service;
	
	@Autowired
	public AgentController(AgentService service) {
		
		this.service=service;
	}
	
	//GET
			
			@GetMapping("/agents")
			@ResponseStatus(HttpStatus.OK)
			public List<Agent> getAgents() throws NotFoundException
			{
				return service.getAgents();
			}
			
			
			@GetMapping("/agent")
			@ResponseStatus(HttpStatus.OK)
			public Agent getById(@RequestParam(name="id", required=false) Long id) throws NotFoundException
			{
				return service.getById(id);
			}
			
			
			@GetMapping("/agent/{username}")
			@ResponseStatus(HttpStatus.OK)
			public Agent getByUsername(@PathVariable(name="username") String username)
			{
				return service.getByUsername(username);
			}
			
			
			
		
		
		//POST
			
			@PostMapping("/agent")
			@ResponseStatus(HttpStatus.CREATED)
			public String addAgent(@RequestBody Agent agent)  throws AlreadyExistsException
			{
				return service.addAgent(agent);
			}
		
	
		
			
		//DELETE
			
			@DeleteMapping("/agent/{id}")
			@ResponseStatus(HttpStatus.OK)
			public void deleteAgent(@PathVariable Long id) throws NotFoundException
			{
				service.removeAgent(id);
			}
			
			
			@PutMapping("/agent/{id}")
			@ResponseStatus(HttpStatus.OK)
			public void newPassword(@PathVariable(name="id") Long id, @RequestBody String password) throws NotFoundException
			{
				service.newPassword(id, password);
				
			}
	

}

