package com.ensa.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensa.backend.entities.Client;


public interface ClientRepository extends JpaRepository<Client, Long> {
	
	Optional<Client> findByUsername(String username);

	Optional<Client> findByCin(String username);

}
