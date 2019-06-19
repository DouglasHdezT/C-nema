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
 * Clase que mapea la entidad "pelicula" en la base de datos del proyecto.
 * @author DeusHdezT
 * @version 1.0
 */
@Entity(name = "pelicula")
public class Movie {
	
	@Id
	@Column(name =  "id_pelicula")
	private Integer _id;
	
	@Column(name =  "titulo_pelicula")
	private String title;
	
	@Column(name =  "descripcion")
	private String description;
	
	@Column(name =  "url_image")
	private String urlImage;
	
	@Column(name =  "fecha_estreno")
	private Date releaseDate;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="id_categoria")
	private Category category;
	
	@OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
	private List<Schedule> schedulesList;

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Schedule> getSchedulesList() {
		return schedulesList;
	}

	public void setSchedulesList(List<Schedule> schedulesList) {
		this.schedulesList = schedulesList;
	}
	
}
