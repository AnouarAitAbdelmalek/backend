package com.ensa.backend.entities;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="CLIENT")
@AttributeOverrides({
    @AttributeOverride(name = "id", column = @Column(name = "ID_CLIENT")),
    @AttributeOverride(name = "nom", column = @Column(name = "NOM_CLIENT")),
    @AttributeOverride(name = "prenom", column = @Column(name = "PRENOM_CLIENT")),
    @AttributeOverride(name = "cin", column = @Column(name = "CIN_CLIENT")),
    @AttributeOverride(name = "adresse", column = @Column(name = "ADRESSE_CLIENT")),
    @AttributeOverride(name = "telephone", column = @Column(name = "TELEPHONE_CLIENT")),
    @AttributeOverride(name = "email", column = @Column(name = "EMAIL_CLIENT")),
    @AttributeOverride(name = "username", column = @Column(name = "USERNAME_CLIENT")),
    @AttributeOverride(name = "password", column = @Column(name = "PASSWORD_CLIENT")),
    @AttributeOverride(name = "role", column = @Column(name = "ROLE_CLIENT"))
})
public class Client extends Utilisateur{
	
	
	@JoinColumn(name="COMPTE_CLIENT")
	@OneToOne(cascade=CascadeType.ALL)
	Compte compte;

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(Long id, String nom, String prenom, String cin, String adresse, String telephone, String email,
			String username, String password, boolean provisoire, String role) {
		super(id, nom, prenom, cin, adresse, telephone, email, username, password, provisoire, role);
		// TODO Auto-generated constructor stub
	}

	public Client(Long id, String nom, String prenom, String cin, String adresse, String telephone, String email,
			String username, String password, boolean provisoire, String role, Compte compte) {
		super(id, nom, prenom, cin, adresse, telephone, email, username, password, provisoire, role);
		this.compte = compte;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	
}
