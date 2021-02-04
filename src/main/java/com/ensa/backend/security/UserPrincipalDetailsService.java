package com.ensa.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ensa.backend.entities.Utilisateur;
import com.ensa.backend.repositories.UserRepository;


@Service
public class UserPrincipalDetailsService implements UserDetailsService {

	@Autowired
	UserRepository rep;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Utilisateur utilisateur=rep.findByUsername(username).get();
		UserPrincipal userPrincipal= new UserPrincipal(utilisateur);
		return userPrincipal;
	}

}
