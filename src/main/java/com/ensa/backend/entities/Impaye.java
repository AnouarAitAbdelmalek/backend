package com.ensa.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="IMPAYE")
public class Impaye {
	
	
	@Id
	@Column(name="IMPAYE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	
	double Solde;

}
