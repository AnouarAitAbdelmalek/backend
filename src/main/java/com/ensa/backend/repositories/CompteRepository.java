package com.ensa.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensa.backend.entities.Client;
import com.ensa.backend.entities.Compte;



public interface CompteRepository extends JpaRepository<Compte, Long> {

	

}
