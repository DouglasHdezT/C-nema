package com.kdc.cnema.domain;

import java.sql.Date;
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
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_sala")
	private Cinema cinema;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_pelicula")
	private Movie movie;
	
	@OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY)
	private List<Reservation> reservationsList;
	
}
