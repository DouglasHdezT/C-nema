package com.kdc.cnema.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Clase que mapea la entidad "municipio" en la base de datos del proyecto.
 * @author DeusHdezT
 * @version 1.0
 */
@Entity(name = "municipio")
public class Town {
	
	@Id
	@Column(name = "id_municipio")
	private Integer _id;
	
	@Column(name = "nombre_municipio")
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_depto")
	private Depto depto; 
}
