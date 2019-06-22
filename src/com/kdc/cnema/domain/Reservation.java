package com.kdc.cnema.domain;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Clase que mapea la entidad "reservacion" en la base de datos del proyecto.
 * @author DeusHdezT
 * @version 1.0
 */
@Entity
@Table(name = "reservacion", schema = "public")
public class Reservation {
	
	@Id
	@Column(name = "id_reservacion")
	@GeneratedValue(generator = "reservacion_id_reservacion_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "reservacion_id_reservacion_seq" , sequenceName = "	public.reservacion_id_reservacion_seq", allocationSize = 1)
	private Integer id;
	
	@Column(name = "cantidad_reservaciones")
	private Integer quanReservations;
	
	@Column(name = "precio_unitario")
	private BigDecimal unitPrice;
	
	@Column(name = "precio_total")
	private BigDecimal totalPrice;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_horario", referencedColumnName= "id_horario")
	private Schedule schedule;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_usuario", referencedColumnName= "id_usuario")
	private User user;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuanReservations() {
		return quanReservations;
	}

	public void setQuanReservations(Integer quanReservations) {
		this.quanReservations = quanReservations;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
