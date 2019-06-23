package com.kdc.cnema.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Clase que mapea la entidad "pais" en la base de datos del proyecto.
 * @author DeusHdezT
 * @version 1.0
 */
@Entity
@Table(name = "pais", schema = "public")
public class Country {
	
	@Id
	@Column(name = "id_pais")
	@GeneratedValue(generator = "pais_id_pais_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "pais_id_pais_seq" , sequenceName = "public.pais_id_pais_seq", allocationSize = 1)
	private Integer id;
	
	@NotBlank
	@Column(name = "nombre_pais")
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
	private List<User> users;
	
	@JsonIgnore
	@OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
	private List<Depto> deptos;

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

}
