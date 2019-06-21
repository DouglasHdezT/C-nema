package com.kdc.cnema.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.kdc.cnema.domain.audit.CountryAudit;

/**
 * Clase que mapea la entidad "pais" en la base de datos del proyecto.
 * @author DeusHdezT
 * @version 1.0
 */
@Entity(name = "pais")
public class Country {
	
	@Id
	@Column(name = "id_pais")
	private Integer id;
	
	@Column(name = "nombre_pais")
	private String name;
	
	@OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
	private List<User> users;
	
	@OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
	private List<Depto> deptos;

	@OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
	private List<CountryAudit> countryAudits;

	
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Depto> getDeptos() {
		return deptos;
	}

	public void setDeptos(List<Depto> deptos) {
		this.deptos = deptos;
	}

	public List<CountryAudit> getCountryAudits() {
		return countryAudits;
	}

	public void setCountryAudits(List<CountryAudit> countryAudits) {
		this.countryAudits = countryAudits;
	} 
	
}
