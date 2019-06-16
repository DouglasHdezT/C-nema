package com.kdc.cnema.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
	
}
