package com.kdc.cnema.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	
	@OneToMany(mappedBy = "cinema", fetch = FetchType.LAZY)
	private List<Schedule> schedules;

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
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
