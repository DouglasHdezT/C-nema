package com.kdc.cnema.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Clase que mapea la entidad "sala" en la base de datos del proyecto.
 * @author DeusHdezT
 * @version 1.0
 */
@Entity
@Table(name = "sala", schema = "public")
public class Cinema {

	@Id
	@Column(name="id_sala")
	@GeneratedValue(generator = "sala_id_sala_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "sala_id_sala_seq" , sequenceName = "public.sala_id_sala_seq", allocationSize = 1)
	private Integer id;
	
	@Column(name="numero_sala")
	private Integer roomNumber;
	
	@Column(name = "tipo_sala")
	private String type;
	
	@Column(name= "capacidad")
	private Integer capacity;
	
	@OneToMany(mappedBy = "cinema", fetch = FetchType.LAZY)
	private List<Schedule> schedules;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	
}
