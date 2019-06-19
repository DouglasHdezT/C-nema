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
}
