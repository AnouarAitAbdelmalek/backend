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
@Table(name="FORMULAIRE")
public class Formulaire {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_FORMULAIRE")
	Long id;
	
	@JoinColumn(name="CREANCE_FORMULAIRE")
	@OneToOne(mappedBy="formulaire")
	@JsonIgnore
	Creance creance;
	
	
	@Column(name="CHAMPS_FORMULAIRE")
	@OneToMany(mappedBy="formulaire",cascade=CascadeType.ALL)
	@JsonIgnore
	List<Champ> champs;
}
