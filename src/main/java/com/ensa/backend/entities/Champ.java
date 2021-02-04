package com.ensa.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="CHAMP")
public class Champ {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_CHAMP")
	Long id;
	
	
	@JoinColumn(name="FORMULAIRE_CHAMP")
	@ManyToOne
	Formulaire formulaire;
	
	@Column(name="NOM_CHAMP")
	String nom;
	
	@Column(name="VALEUR_CHAMP")
	String valeur;

	public Champ() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Champ(Long id, Formulaire formulaire, String nom, String valeur) {
		super();
		this.id = id;
		this.formulaire = formulaire;
		this.nom = nom;
		this.valeur = valeur;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Formulaire getFormulaire() {
		return formulaire;
	}

	public void setFormulaire(Formulaire formulaire) {
		this.formulaire = formulaire;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	

}
