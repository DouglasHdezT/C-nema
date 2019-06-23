package com.kdc.cnema.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Clase que mapea la entidad "departamento" en la base de datos del proyecto.
 * @author DeusHdezT
 * @version 1.0
 */
@Entity
@Table(name = "departamento", schema = "public")
public class Depto {
	
	@Id
	@Column(name="id_depto")
	@GeneratedValue(generator = "departamento_id_depto_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "departamento_id_depto_seq" , sequenceName = "public.departamento_id_depto_seq", allocationSize = 1)
	private Integer id;
	
	@Column(name="nombre_depto")
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_pais" , referencedColumnName= "id_pais")
	private Country country;
	
	@JsonIgnore
	@OneToMany(mappedBy = "depto", fetch = FetchType.LAZY)
	private List<Town> towns;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Town> getTowns() {
		return towns;
	}

	public void setTowns(List<Town> towns) {
		this.towns = towns;
	}

}
