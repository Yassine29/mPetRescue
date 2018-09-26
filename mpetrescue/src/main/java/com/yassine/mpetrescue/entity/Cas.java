package com.yassine.mpetrescue.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
@Entity
@Table(name = "cas")
public class Cas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rapporteur")
	User rapporteur;

	@Column(name = "date_creation")
	String date_creation;

	@Column(name = "date_repere")
	String date_repere;

	@Column(name = "localisation")
	String localisation;

	@Column(name = "lieu")
	String lieu;

	@Column(name = "ville")
	String ville;

	@Column(name = "animal")
	String animal;

	@Column(name = "etat")
	String etat;

	@Column(name = "description")
	String description;

	@Column(name = "statut_cas")
	String statut;

	@JsonCreator
	public Cas(@JsonProperty("id") final Long id, @JsonProperty("rapporteur") final User rapporteur,
			@JsonProperty("date_creation") final String date_creation,
			@JsonProperty("date_repere") final String date_repere,
			@JsonProperty("localisation") final String localisation, @JsonProperty("lieu") final String lieu,
			@JsonProperty("ville") final String ville, @JsonProperty("animal") final String animal,
			@JsonProperty("etat") final String etat, @JsonProperty("description") final String description,
			@JsonProperty("statut") final String statut) {
		super();
		this.id = id;
		this.date_creation = date_creation;
		this.date_repere = date_repere;
		this.localisation = localisation;
		this.lieu = lieu;
		this.ville = ville;
		this.animal = animal;
		this.etat = etat;
		this.description = description;
		this.rapporteur = rapporteur;
		this.statut = statut;
	}
}
