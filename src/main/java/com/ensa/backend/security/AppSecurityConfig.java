package com.ensa.backend.security;



import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ensa.backend.entities.Admin;
import com.ensa.backend.exceptions.NotFoundException;
import com.ensa.backend.services.AdminService;


@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	AdminService adminService;
	
	UserPrincipalDetailsService service;
	
	@Autowired
	public AppSecurityConfig(UserPrincipalDetailsService service) {

		this.service = service;
	}
	
	@PostConstruct
	public void init() {
		List<Admin>  currentAdminList= new ArrayList<Admin>();
		try {
		currentAdminList = adminService.getAdmins();
		} catch (NotFoundException e) {
			Admin    admin    = new Admin();
	        admin.setUsername("admin");
	        admin.setPassword("admin");
	        admin.setEmail("ensa.backend@gmail.com");
	        admin.setRole("Admin");
	        adminService.addAdmin(admin);
			
		}

	    
	}

	@Bean
	public DaoAuthenticationProvider autProvider()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(service);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		
		return provider;
	}
	
	
//	@Bean
//	public CorsConfigurationSource corsConfigurationSource() {
//		final CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowedOrigins(ImmutableList.of("*"));
//		configuration.setAllowedMethods(ImmutableList.of("HEAD",
//				"GET", "POST", "PUT", "DELETE", "PATCH"));
//		configuration.setAllowedHeaders(ImmutableList.of("accept",
//				"accept-encoding",
//				"authorization",
//				"content-type",
//				"dnt",
//				"origin",
//				"user-agent",
//				"x-csrftoken",
//				"x-requested-with"));
//		// setAllowCredentials(true) is important, otherwise:
//		// The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
//		configuration.setAllowCredentials(true);
//		// setAllowedHeaders is important! Without it, OPTIONS preflight request
//		// will fail with 403 Invalid CORS request
//		configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));
//		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.cors()
			.and()
			.authorizeRequests()
			//ADMIN
			.antMatchers(HttpMethod.GET,"/admins").hasRole("Admin")		//afficher les admins
			.antMatchers(HttpMethod.GET,"/admin").hasRole("Admin")		//afficher un admin
			.antMatchers(HttpMethod.GET,"/admin/{username}").hasRole("Admin")		//admin par username
			.antMatchers(HttpMethod.POST,"/admin").hasRole("Admin")		//creer les admins
			.antMatchers(HttpMethod.DELETE,"/admin/{id}").hasRole("Admin")	//supprimer un admin
			
			//AGENT
			.antMatchers(HttpMethod.POST,"/agent").hasRole("Admin")		//creer agent
			.antMatchers(HttpMethod.DELETE,"/agent/{id}").hasRole("Admin")	//supprimer agent
			.antMatchers(HttpMethod.GET,"/agents").hasRole("Admin")	//afficher agents
			.antMatchers(HttpMethod.GET,"/agent/{username}").hasRole("Agent")		//agent par username
			.antMatchers(HttpMethod.GET,"/agent").hasRole("Admin")		//afficher agent
			.antMatchers(HttpMethod.PUT,"/agent/{id}").hasRole("Agent")	//modifier mot de passe
			
			//CLIENT
			.antMatchers(HttpMethod.POST,"/client").hasRole("Agent")		//creer client
			.antMatchers(HttpMethod.GET,"/clients").hasRole("Agent")		//afficher clients
			.antMatchers(HttpMethod.GET,"/client").hasRole("Agent")		//afficher client
			.antMatchers(HttpMethod.GET,"/client/{username}").hasRole("Client")		//client par username
			.antMatchers(HttpMethod.PUT,"/client/{id}").hasRole("Client")	//modifier mot de passe
			.antMatchers(HttpMethod.DELETE,"/client/{id}").hasRole("Agent")	//supprimer client
			
			//COMPTE
			.antMatchers(HttpMethod.GET,"/client/{id}/compte").hasAnyRole("Agent","Client")	//afficher compte
			.antMatchers(HttpMethod.POST,"/comptes").hasRole("Agent")	//creer compte
			.antMatchers(HttpMethod.PUT,"/compte/{id}").hasRole("Agent")	//alimenter compte
			.antMatchers(HttpMethod.DELETE,"/compte/{id}").hasRole("Agent")	//supprimer compte
			
			.and()
			.httpBasic()
			.and()
			.csrf().disable()
			;
			
		
		
		super.configure(http);
	}
	
	
	

}
