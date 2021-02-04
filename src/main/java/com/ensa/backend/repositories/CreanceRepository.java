package com.ensa.backend.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensa.backend.entities.Creance;
import com.ensa.backend.entities.Creancier;


public interface CreanceRepository extends JpaRepository<Creance, Long> {

	Optional<Creance> findByCode(String code);

	List<Creance> findAllByCreancier(Creancier creancier);
	
	

}
