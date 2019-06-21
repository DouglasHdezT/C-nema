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

import com.kdc.cnema.domain.audit.CategoryAudit;

/**
 * Clase que mapea la entidad "categoria" en la base de datos del proyecto.
 * @author DeusHdezT
 * @version 1.0
 */
@Entity
@Table(name = "categoria", schema = "public")
public class Category {
	
	@Id
	@Column(name = "id_categoria")
	@GeneratedValue(generator = "categoria_id_categoria_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "categoria_id_categoria_seq" , sequenceName = "public.categoria_id_categoria_seq", allocationSize = 1)
	private Integer id;
	
	@Column(name = "nombre_categoria")
	private String name;
	
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private List<Movie> movies;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

}
