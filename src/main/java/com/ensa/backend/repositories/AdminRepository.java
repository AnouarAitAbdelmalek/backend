package com.ensa.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensa.backend.entities.Admin;


public interface AdminRepository extends JpaRepository<Admin, Long> {
	
	Optional<Admin> findByUsername(String username);

	Optional<Admin> findByCin(String cin);

}
