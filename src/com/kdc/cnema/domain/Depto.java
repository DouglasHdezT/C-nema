package com.kdc.cnema.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.kdc.cnema.domain.audit.DeptoAudit;

/**
 * Clase que mapea la entidad "departamento" en la base de datos del proyecto.
 * @author DeusHdezT
 * @version 1.0
 */
@Entity(name = "departamento")
public class Depto {
	
	@Id
	@Column(name="id_depto")
	private Integer id;
	
	@Column(name="nombre_depto")
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_pais")
	private Country country;
	
	@OneToMany(mappedBy = "depto", fetch = FetchType.LAZY)
	private List<Town> towns;

	@OneToMany(mappedBy = "depto", fetch = FetchType.LAZY)
	private List<DeptoAudit> deptoAudits;

	
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

	public List<DeptoAudit> getDeptoAudits() {
		return deptoAudits;
	}

	public void setDeptoAudits(List<DeptoAudit> deptoAudits) {
		this.deptoAudits = deptoAudits;
	}
	
	
}
