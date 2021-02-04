package com.ensa.backend.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensa.backend.entities.Creancier;


public interface CreancierRepository extends JpaRepository<Creancier, Long> {

	Optional<Creancier> findByCode(String code);
	
	

}
