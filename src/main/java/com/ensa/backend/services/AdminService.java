package com.ensa.backend.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ensa.backend.entities.Admin;
import com.ensa.backend.exceptions.AlreadyExistsException;
import com.ensa.backend.exceptions.NotFoundException;
import com.ensa.backend.repositories.AdminRepository;
import com.ensa.backend.repositories.UserRepository;



@Service
public class AdminService {
	
	@Autowired
	AdminRepository rep;
	
	
	@Autowired
	UserRepository userRep;
	
	
	Logger logger = LoggerFactory.getLogger(AdminService.class.getName());
	
	
	public Admin getByUsername(String username)
	{
		Admin admin = rep.findByUsername(username)
				.orElseThrow(() -> new NotFoundException("Aucun administrateur avec le username "+username+" trouvé"));
		return admin;
	}
	
	public Admin getById(Long id)
	{
		Admin admin = rep.findById(id)
				.orElseThrow(() -> new NotFoundException("Aucun administrateur avec l'id "+id+" trouvé"));
		return admin;
	}
	
	public List<Admin> getAdmins()
	{
		
		List<Admin> admins=rep.findAll();
		
		if(admins.isEmpty())  throw new NotFoundException("Aucun administrateur trouvé");
		return admins;
	}
	
	
	
	public void addAdmin(Admin admin)
	{
		
		if(userRep.findByUsername(admin.getUsername()).isPresent()) {
			throw new AlreadyExistsException("Veuillez choisir un autre Username");
		}
		
		if(rep.findByCin(admin.getCin()).isPresent()) {
			throw new AlreadyExistsException("Un administrateur avec le CIN "+admin.getCin()+" existe déjà");
		}
		
		admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
		admin.setRole("Admin");
				
		
		rep.save(admin);
		
	}
	
	

	public void removeAdmin(Long id)
	{
		
		//vérifier l'existence de l'administrateur
		Admin admin=rep.findById(id).orElseThrow(() -> new NotFoundException("Aucun administrateur avec l'id "+id+" n'est trouvé"));
		rep.delete(admin);
		
	}


	
	
}
