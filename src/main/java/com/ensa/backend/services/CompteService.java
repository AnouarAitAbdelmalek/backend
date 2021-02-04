package com.ensa.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensa.backend.entities.Compte;
import com.ensa.backend.exceptions.AlreadyExistsException;
import com.ensa.backend.exceptions.NotFoundException;
import com.ensa.backend.repositories.CompteRepository;



@Service
public class CompteService {

	@Autowired
	CompteRepository rep;



	
	public Compte getById(Long id) throws NotFoundException
	{

		Compte compte = rep.findById(id).orElseThrow(() -> new NotFoundException("Ce compte n'existe pas"));
		
		return compte;
	}
	

	public void alimenterSolde(Long id, double montant) throws NotFoundException, AlreadyExistsException
	{
		Compte updated = rep.findById(id).orElseThrow(() -> new NotFoundException("Aucun compte avec l'id "+id+" trouvé"));

		updated.setSolde(updated.getSolde() + montant);

		rep.save(updated);

	}

	public void removeCompte(Long id) throws NotFoundException
	{

		//vérifier l'existence de l'compte
		Compte compte=rep.findById(id).orElseThrow(() -> new NotFoundException("Aucun compte avec l'id "+id+" n'est trouvé"));
		rep.delete(compte);

	}


}
