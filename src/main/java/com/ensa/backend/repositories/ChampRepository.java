package com.ensa.backend.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensa.backend.entities.Champ;
import com.ensa.backend.entities.Formulaire;


public interface ChampRepository extends JpaRepository<Champ, Long> {

	List<Champ> findAllByFormulaire(Formulaire formulaire);
	
	

}
