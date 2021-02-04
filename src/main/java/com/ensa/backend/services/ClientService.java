package com.ensa.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ensa.backend.entities.Client;
import com.ensa.backend.entities.Compte;
import com.ensa.backend.exceptions.AlreadyExistsException;
import com.ensa.backend.exceptions.NotFoundException;
import com.ensa.backend.repositories.ClientRepository;
import com.ensa.backend.repositories.CompteRepository;
import com.ensa.backend.repositories.UserRepository;


@Service
public class ClientService {
	
	@Autowired
	ClientRepository rep;
	
	@Autowired
	UserRepository userRep;
	
	@Autowired
	CompteRepository compteRep;
	
	@Autowired
	JavaMailSender emailSender;
	
	
	
	public List<Client> getClients()  throws NotFoundException
	{
		
		List<Client> clients= new ArrayList<Client>();	
		
		clients=rep.findAll();
		
		if(clients.isEmpty())  throw new NotFoundException("Aucun client trouvé");
		return clients;
	}
	
	
	public Client getById(Long id)  throws NotFoundException
	{
		
		return rep.findById(id).orElseThrow(() -> new NotFoundException("Aucun client avec l'id "+id+" trouvé"));
	}
	
	
	public Client getByUsername(String username)
	{
		return rep.findByUsername(username).orElseThrow(() -> new NotFoundException("Aucun client avec le username "+username+" trouvé"));
	}
	
	
	public static String alphaNumericString(int len) {
	    String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    Random rnd = new Random();

	    StringBuilder sb = new StringBuilder(len);
	    for (int i = 0; i < len; i++) {
	        sb.append(AB.charAt(rnd.nextInt(AB.length())));
	    }
	    return sb.toString();
	}
	
	public String addClient(Client client) throws AlreadyExistsException
	{
			
		
		String password= alphaNumericString(10);
		client.setProvisoire(true);
		client.setUsername(client.getTelephone());
		
		client.setPassword(new BCryptPasswordEncoder().encode(password));
		client.setRole("Client");
		client.setProvisoire(true);
		
		Compte compte = client.getCompte();
		
		
		
		client = rep.save(client);
		
		compte.setProprietaire(client);
		
		compteRep.save(compte);
		
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(client.getEmail()); 
        message.setSubject("Nom d'utilisateur et mot de passe");
       
        message.setText(
        		"Cher "+client.getRole()+", \n"
        		+"Vous trouvez ci-dessous vos coordonnées d'identification dans notre application :\n"
        		+"\nUsername : "+client.getUsername()
        		+"\nPassword : "+password
        		+"\nCordialement."
        		);
        emailSender.send(message);
		
		return password;
		
	}
	
	

	public void removeClient(Long id) throws NotFoundException
	{
		
		//vérifier l'existence du client
		Client client=rep.findById(id).orElseThrow(() -> new NotFoundException("Aucun client avec l'id "+id+" n'est trouvé"));
		rep.delete(client);
		
	}
	
	public void newPassword(Long id, String password) throws NotFoundException
	{
		
		//vérifier l'existence du client
		Client client=rep.findById(id).orElseThrow(() -> new NotFoundException("Aucun client avec l'id "+id+" n'est trouvé"));
		client.setPassword(new BCryptPasswordEncoder().encode(password));
		client.setProvisoire(false);
		rep.save(client);
		
	}
	
	
	
	
}
