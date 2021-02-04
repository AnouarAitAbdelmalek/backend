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
@Table(name="IMPAYE")
public class Impaye {
	
	
	@Id
	@Column(name="IMPAYE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	@Column(name="IMPAYE_MONTANT")
	double montant;
	
	@Column(name="IMPAYE_TYPE")
	String type;
	
	@Column(name="IMPAYE_REFERENCE")
	String reference; 
	
	@JoinColumn(name="IMPAYE_CREANCE")
	@ManyToOne
	Creance creance;

	public Impaye() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Impaye(Long id, double montant, String type, String reference, Creance creance) {
		super();
		this.id = id;
		this.montant = montant;
		this.type = type;
		this.reference = reference;
		this.creance = creance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Creance getCreance() {
		return creance;
	}

	public void setCreance(Creance creance) {
		this.creance = creance;
	}
	
	
	
	
	
	
	

}
