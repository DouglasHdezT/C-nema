package com.kdc.cnema.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
	
}
