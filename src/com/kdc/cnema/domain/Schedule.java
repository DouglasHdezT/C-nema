package com.kdc.cnema.domain;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Clase que mapea la entidad "horario" en la base de datos del proyecto.
 * @author DeusHdezT
 * @version 1.0
 */
@Entity
@Table(name = "horario", schema = "public")
public class Schedule {

	@Id
	@Column(name = "id_horario")
	@GeneratedValue(generator = "horario_id_horario_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "horario_id_horario_seq" , sequenceName = "	public.horario_id_horario_seq", allocationSize = 1)
	private Integer id;
	
	@Min(0)
	@Max(600)
	@Column(name = "disponibles")
	private Integer avialable;
	
	@NotNull
	@Column(name = "hora_inicio")
	private Date startTime;
	
	@NotNull
	@Column(name = "hora_fin")
	private Date endTime;
	
	@NotNull
	@Column(name =  "status")
	private Boolean status;

	@NotNull
	@Column(name =  "precio_especial")
	private BigDecimal premiumPrice;
	
	@NotNull
	@Column(name =  "precio_normal")
	private BigDecimal normalPrice;
	
	@NotBlank
	@Column(name = "tipo_lenguaje")
	private String type;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_sala", referencedColumnName= "id_sala")
	private Cinema cinema;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_pelicula", referencedColumnName= "id_pelicula")
	private Movie movie;
	
	@JsonIgnore
	@OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY)
	private List<Reservation> reservations;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAvialable() {
		return avialable;
	}

	public void setAvialable(Integer avialable) {
		this.avialable = avialable;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public BigDecimal getPremiumPrice() {
		return premiumPrice;
	}

	public void setPremiumPrice(BigDecimal premiumPrice) {
		this.premiumPrice = premiumPrice;
	}

	public BigDecimal getNormalPrice() {
		return normalPrice;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setNormalPrice(BigDecimal normalPrice) {
		this.normalPrice = normalPrice;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	
}
