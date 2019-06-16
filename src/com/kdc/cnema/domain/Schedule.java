package com.kdc.cnema.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Clase que mapea la entidad "horario" en la base de datos del proyecto.
 * @author DeusHdezT
 * @version 1.0
 */
@Entity(name = "horario")
public class Schedule {

	@Id
	@Column(name = "id_horario")
	private Integer _id;
	
	@Column(name = "disponibles")
	private Integer avialable;
	
	@Column(name = "hora_inicio")
	private Date startTime;
	
	@Column(name = "hora_fin")
	private Date endTime;
	
}
