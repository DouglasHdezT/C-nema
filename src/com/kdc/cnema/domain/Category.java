package com.kdc.cnema.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.kdc.cnema.domain.audit.CategoryAudit;

/**
 * Clase que mapea la entidad "categoria" en la base de datos del proyecto.
 * @author DeusHdezT
 * @version 1.0
 */
@Entity(name = "categoria")
public class Category {
	
	@Id
	@Column(name = "id_categoria")
	private Integer _id;
	
	@Column(name = "nombre_categoria")
	private String name;
	
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private List<Movie> movies;

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private List<CategoryAudit> categoryAudits;

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
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

	public List<CategoryAudit> getCategoryAudits() {
		return categoryAudits;
	}

	public void setCategoryAudits(List<CategoryAudit> categoryAudits) {
		this.categoryAudits = categoryAudits;
	} 
	
}
