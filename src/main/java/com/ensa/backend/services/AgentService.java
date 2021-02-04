package com.ensa.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ensa.backend.entities.Agent;
import com.ensa.backend.entities.Client;
import com.ensa.backend.exceptions.AlreadyExistsException;
import com.ensa.backend.exceptions.NotFoundException;
import com.ensa.backend.repositories.AgentRepository;
import com.ensa.backend.repositories.UserRepository;


@Service
public class AgentService {
	
	@Autowired
	AgentRepository rep;
	
	@Autowired
	UserRepository userRep;
	
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	JavaMailSender emailSender;

	
	
	public List<Agent> getAgents()  throws NotFoundException
	{
		
		List<Agent> agents= new ArrayList<Agent>();	
		
		agents=rep.findAll();
		
		if(agents.isEmpty())  throw new NotFoundException("Aucun agent trouvé");
		return agents;
	}
	
	public Agent getById(Long id)
	{
		Agent agent = rep.findById(id)
				.orElseThrow(() -> new NotFoundException("Aucun administrateur avec l'id "+id+" trouvé"));
		return agent;
	}
	
	public Agent getByUsername(String username)
	{
		return rep.findByUsername(username).orElseThrow(() -> new NotFoundException("Aucun agent avec le username "+username+" trouvé"));
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
	
	
	
	
	public String addAgent(Agent agent) throws AlreadyExistsException
	{
		if(userRep.findByUsername(agent.getUsername()).isPresent()) {
			throw new AlreadyExistsException("Veuillez choisir un autre Username");
		}
		
		
		if(rep.findByCin(agent.getCin()).isPresent()) {
			throw new AlreadyExistsException("Un agent avec le CIN "+agent.getCin()+" existe déjà");
		}
		
		String password = alphaNumericString(10);
		agent.setUsername(agent.getTelephone());
		
		agent.setPassword(new BCryptPasswordEncoder().encode(password));
		agent.setRole("Agent");
		agent.setProvisoire(true);
		
		rep.save(agent);
		
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(agent.getEmail()); 
        message.setSubject("Nom d'utilisateur et mot de passe");
       
        message.setText(
        		"Cher "+agent.getRole()+", \n"
        		+"Vous trouvez ci-dessous vos coordonnées d'identification dans notre application :\n"
        		+"\nUsername : "+agent.getUsername()
        		+"\nPassword : "+password
        		+"\nCordialement."
        		);
        emailSender.send(message);
		
		return password;
		
	}
	
	

	public void removeAgent(Long id) throws NotFoundException
	{
		
		//vérifier l'existence de l'agent
		Agent agent=rep.findById(id).orElseThrow(() -> new NotFoundException("Aucun agent avec l'id "+id+" n'est trouvé"));
		rep.delete(agent);
		
	}
	
	public void newPassword(Long id, String password) throws NotFoundException
	{
		
		//vérifier l'existence du client
		Agent agent=rep.findById(id).orElseThrow(() -> new NotFoundException("Aucun client avec l'id "+id+" n'est trouvé"));
		agent.setPassword(new BCryptPasswordEncoder().encode(password));
		agent.setProvisoire(false);
		rep.save(agent);
		
	}

}
