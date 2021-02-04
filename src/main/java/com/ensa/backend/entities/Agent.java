package com.ensa.backend.entities;

import java.io.File;
import java.time.LocalDateTime;

import javax.persistence.*;


@Entity
@Table(name="AGENT")
@AttributeOverrides({
    @AttributeOverride(name = "id", column = @Column(name = "ID_AGENT")),
    @AttributeOverride(name = "nom", column = @Column(name = "NOM_AGENT")),
    @AttributeOverride(name = "prenom", column = @Column(name = "PRENOM_AGENT")),
    @AttributeOverride(name = "cin", column = @Column(name = "CIN_AGENT")),
    @AttributeOverride(name = "adresse", column = @Column(name = "ADRESSE_AGENT")),
    @AttributeOverride(name = "telephone", column = @Column(name = "TELEPHONE_AGENT")),
    @AttributeOverride(name = "email", column = @Column(name = "EMAIL_AGENT")),
    @AttributeOverride(name = "username", column = @Column(name = "USERNAME_AGENT")),
    @AttributeOverride(name = "password", column = @Column(name = "PASSWORD_AGENT")),
    @AttributeOverride(name = "role", column = @Column(name = "ROLE_AGENT"))
})
public class Agent extends Utilisateur{
	
	@Column(name="PATENTE_AGENT")
	String patente;
	
	@Column(name="IMMATRICULATION_AGENT")
	String immatriculation;
	
	@Column(name="DATE_NAISSANCE_AGENT")
	LocalDateTime dateNaissance;
	
	@Column(name="FILES_AGENT")
	File files[];

	public Agent(Long id, String nom, String prenom, String cin, String adresse, String telephone, String email,
			String username, String password, boolean provisoire, String role, String patente, String immatriculation,
			File[] files) {
		super(id, nom, prenom, cin, adresse, telephone, email, username, password, provisoire, role);
		this.patente = patente;
		this.immatriculation = immatriculation;
		this.files = files;
	}

	public Agent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Agent(Long id, String nom, String prenom, String cin, String adresse, String telephone, String email,
			String username, String password, boolean provisoire, String role) {
		super(id, nom, prenom, cin, adresse, telephone, email, username, password, provisoire, role);
		// TODO Auto-generated constructor stub
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}	
	
	
}
