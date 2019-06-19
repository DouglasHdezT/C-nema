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

/**
 * Clase que mapea la entidad "departamento" en la base de datos del proyecto.
 * @author DeusHdezT
 * @version 1.0
 */
@Entity(name = "departamento")
public class Depto {
	
	@Id
	@Column(name="id_depto")
	private Integer _id;
	
	@Column(name="nombre_depto")
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_pais")
	private Country country;
	
	@OneToMany(mappedBy = "depto", fetch = FetchType.LAZY)
	private List<Town> townsList;
	
}
