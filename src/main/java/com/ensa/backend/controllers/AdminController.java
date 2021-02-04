package com.ensa.backend.controllers;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.ensa.backend.entities.Admin;
import com.ensa.backend.services.AdminService;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AdminController {
	
	
	AdminService service;
	
	@Autowired
	public AdminController(AdminService service) {
		
		this.service=service;
	}
	
	//GET
			@GetMapping("/admins")
			@ResponseStatus(HttpStatus.OK)
			public List<Admin> getAdmins()
			{
				return service.getAdmins();
			}
			
			@GetMapping("/admin")
			@ResponseStatus(HttpStatus.OK)
			public Admin getAdmin(@RequestParam(name="id", required=false) Long id)
			{
				return service.getById(id);
			}
			
			@GetMapping("/admin/{username}")
			@ResponseStatus(HttpStatus.OK)
			public Admin getByUsername(@PathVariable(name="username") String username)
			{
				return service.getByUsername(username);
			}
			
			

		
		//POST
			
			@PostMapping("/admin")
			@ResponseStatus(HttpStatus.CREATED)
			public void addAdmin(@RequestBody Admin admin)
			{
				service.addAdmin(admin);
			}
	
		
			
		//DELETE
			
			@DeleteMapping("/admin/{id}")
			@ResponseStatus(HttpStatus.OK)
			public void deleteAdmin(@PathVariable Long id)
			{
				service.removeAdmin(id);
			}
			
	

}

