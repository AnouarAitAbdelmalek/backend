package com.ensa.backend.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="CREANCE")
public class Creance {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_CREANCE")
	Long id;
	
	@Column(name="CODE_CREANCE")
	String code;
	
	@Column(name="NOM_CREANCE")
	String nom;
	
	@JoinColumn(name="CREANCIER_CREANCE")
	@ManyToOne
	Creancier creancier;
	
	@JoinColumn(name="FORMULAIRE_CREANCE")
	@OneToOne(cascade=CascadeType.ALL)
	Formulaire formulaire;
	
	@Column(name="IMPAYES_CREANCE")
	@OneToMany(mappedBy="creance",cascade=CascadeType.ALL)
	@JsonIgnore
	List<Impaye> impayes;

	public Creance() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	



	public Creance(Long id, String code, String nom, Creancier creancier, Formulaire formulaire, List<Impaye> impayes) {
		super();
		this.id = id;
		this.code = code;
		this.nom = nom;
		this.creancier = creancier;
		this.formulaire = formulaire;
		this.impayes = impayes;
	}







	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Creancier getCreancier() {
		return creancier;
	}

	public void setCreancier(Creancier creancier) {
		this.creancier = creancier;
	}







	public String getCode() {
		return code;
	}







	public void setCode(String code) {
		this.code = code;
	}







	public Formulaire getFormulaire() {
		return formulaire;
	}







	public void setFormulaire(Formulaire formulaire) {
		this.formulaire = formulaire;
	}







	public List<Impaye> getImpayes() {
		return impayes;
	}







	public void setImpayes(List<Impaye> impayes) {
		this.impayes = impayes;
	}
	
	
	
	
}
