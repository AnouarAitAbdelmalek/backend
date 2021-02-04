package com.ensa.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="COMPTE")
public class Compte {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_COMPTE")
	Long id;
	
	@Column(name="TYPE_COMPTE")
	String type;
	
	@Column(name="SOLDE_COMPTE")
	double solde;
	
	@Column(name="NUMERO_COMPTE")
	String numeroCompte;
		
	
	@JoinColumn(name="PROPRIETAIRE_COMPTE")
	@OneToOne(mappedBy="compte")
	@JsonIgnore
	Client proprietaire;


	public Compte() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Compte(Long id, String type, double solde, Client proprietaire) {
		super();
		this.id = id;
		this.type = type;
		this.solde = solde;
		this.proprietaire = proprietaire;
	}



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public double getSolde() {
		return solde;
	}


	public void setSolde(double solde) {
		this.solde = solde;
	}


	public Client getProprietaire() {
		return proprietaire;
	}


	public void setProprietaire(Client proprietaire) {
		this.proprietaire = proprietaire;
	}
	
	

}
