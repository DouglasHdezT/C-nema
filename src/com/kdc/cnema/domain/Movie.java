package com.kdc.cnema.domain;

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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Clase que mapea la entidad "pelicula" en la base de datos del proyecto.
 * @author DeusHdezT
 * @version 1.0
 */
@Entity(name = "pelicula")
public class Movie {
	
	@Id
	@Column(name =  "id_pelicula")
	@GeneratedValue(generator = "pelicula_id_pelicula_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "pelicula_id_pelicula_seq" , sequenceName = "public.pelicula_id_pelicula_seq", allocationSize = 1)
	private Integer id;
	
	@NotBlank
	@Size(min = 1, max= 150)
	@Column(name =  "titulo_pelicula")
	private String title;
	
	@NotBlank
	@Size(min = 1, max= 1000)
	@Column(name =  "descripcion")
	private String description;
	
	@Min(1)
	@Column(name =  "duracion")
	private Integer length;
	
	@NotBlank
	@Column(name =  "url_imagen")
	private String urlImage;
	
	@NotNull
	@Column(name =  "fecha_estreno")
	private Date releaseDate;
	
	@NotNull
	@Column(name =  "status")
	private Boolean status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_categoria", referencedColumnName= "id_categoria")
	private Category category;
	
	@JsonIgnore
	@OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
	private List<Schedule> schedules;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

}
