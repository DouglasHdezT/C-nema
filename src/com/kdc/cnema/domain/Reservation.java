package com.kdc.cnema.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.TimestampDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers.BigDecimalDeserializer;

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
	@SequenceGenerator(name = "reservacion_id_reservacion_seq" , sequenceName = "public.reservacion_id_reservacion_seq", allocationSize = 1)
	private Integer id;
	
	@NotNull
	@Column(name = "fecha_ingreso")
	@JsonDeserialize(using = TimestampDeserializer.class)
	private Timestamp timestamp;
	
	@Min(1)
	@Max(15)
	@Column(name = "cantidad_reservaciones")
	private Integer quanReservations;
	
	@JsonDeserialize(using = BigDecimalDeserializer.class)
	@NotNull
	@Column(name = "subtotal")
	private BigDecimal totalPrice;
	
	@JsonDeserialize(using = BigDecimalDeserializer.class)
	@NotNull
	@Column(name = "saldo_utilizar")
	private BigDecimal usedBalance;
	
	@JsonDeserialize(using = BigDecimalDeserializer.class)
	@NotNull
	@Column(name = "saldo_remanente_cuenta")
	private BigDecimal remainBalance;
	
	@JsonDeserialize(using = BigDecimalDeserializer.class)
	@NotNull
	@Column(name = "gran_total")
	private BigDecimal grandTotal;
	
	@Min(1)
	@Max(15)
	@Column(name = "cantidad_especial")
	private Integer quanPremium;;
	
	@Min(1)
	@Max(15)
	@Column(name = "cantidad_normal")
	private Integer quanNormal;
	
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

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getUsedBalance() {
		return usedBalance;
	}

	public void setUsedBalance(BigDecimal usedBalance) {
		this.usedBalance = usedBalance;
	}

	public BigDecimal getRemainBalance() {
		return remainBalance;
	}

	public void setRemainBalance(BigDecimal remainBalance) {
		this.remainBalance = remainBalance;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	public Integer getQuanPremium() {
		return quanPremium;
	}

	public void setQuanPremium(Integer quanPremium) {
		this.quanPremium = quanPremium;
	}

	public Integer getQuanNormal() {
		return quanNormal;
	}

	public void setQuanNormal(Integer quanNormal) {
		this.quanNormal = quanNormal;
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
