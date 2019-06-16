package com.kdc.cnema.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Clase que mapea la entidad "reservacion" en la base de datos del proyecto.
 * @author DeusHdezT
 * @version 1.0
 */
@Entity(name = "reservacion")
public class Reservation {
	
	@Id
	@Column(name = "id_reservacion")
	private Integer _id;
	
	@Column(name = "cantidad_reservaciones")
	private Integer quanReservations;
	
	@Column(name = "precio_unitario")
	private BigDecimal unitPrice;
	
	@Column(name = "precio_total")
	private BigDecimal totalPrice;

}
