package com.kdc.cnema.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Clase que mapea la entidad "pais" en la base de datos del proyecto.
 * @author DeusHdezT
 * @version 1.0
 */
@Entity(name = "pais")
public class Country {
	
	@Id
	@Column(name = "id_pais")
	private Integer _id;
	
	@Column(name = "nombre_pais")
	private String name;
	
	@OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
	private List<User> usersList;
	
	@OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
	private List<Depto> deptosList;

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<User> usersList) {
		this.usersList = usersList;
	}

	public List<Depto> getDeptosList() {
		return deptosList;
	}

	public void setDeptosList(List<Depto> deptosList) {
		this.deptosList = deptosList;
	}
	
	
}
