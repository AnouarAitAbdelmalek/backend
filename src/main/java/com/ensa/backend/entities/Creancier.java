package com.ensa.backend.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="CREANCIER")
public class Creancier {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_CREANCIER")
	Long id;
	
	
	@Column(name="CATEGORIE_CREANCIER")
	String categorie;
	
	@Column(name="NOM_CREANCIER")
	String nom;
	
	
	@Column(name="CREANCES_CREANCIER")
	@OneToMany(mappedBy="creancier",cascade=CascadeType.ALL)
	@JsonIgnore
	List<Creance> creances;


	public Creancier() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Creancier(Long id, String categorie, String nom, List<Creance> creances) {
		super();
		this.id = id;
		this.categorie = categorie;
		this.nom = nom;
		this.creances = creances;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCategorie() {
		return categorie;
	}


	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public List<Creance> getCreances() {
		return creances;
	}


	public void setCreances(List<Creance> creances) {
		this.creances = creances;
	}
	
	
	
	

}
