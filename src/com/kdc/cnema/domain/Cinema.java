package com.kdc.cnema.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Clase que mapea la entidad "sala" en la base de datos del proyecto.
 * @author DeusHdezT
 * @version 1.0
 */
@Entity(name = "sala")
public class Cinema {

	@Id
	@Column(name="id_sala")
	private Integer _id;
	
	@Column(name="numero_sala")
	private Integer roomNumber;
	
	@Column(name = "tipo_sala")
	private String type;
	
	@Column(name= "capacidad")
	private Integer capacity;
	
}
