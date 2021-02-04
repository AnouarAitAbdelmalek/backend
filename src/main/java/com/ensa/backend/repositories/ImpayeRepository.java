package com.ensa.backend.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensa.backend.entities.Creance;
import com.ensa.backend.entities.Impaye;


public interface ImpayeRepository extends JpaRepository<Impaye, Long> {

	List<Impaye> findAllByCreanceAndReference(Creance creance, String reference);
	
	

}
