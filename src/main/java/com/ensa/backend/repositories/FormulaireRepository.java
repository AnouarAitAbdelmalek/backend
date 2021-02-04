package com.ensa.backend.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensa.backend.entities.Creance;
import com.ensa.backend.entities.Formulaire;


public interface FormulaireRepository extends JpaRepository<Formulaire, Long> {

	List<Formulaire> findAllByCreance(Creance creance);
	
	

}
