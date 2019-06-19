package com.kdc.cnema.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.kdc.cnema.domain.audit.TownAudit;

/**
 * Clase que mapea la entidad "municipio" en la base de datos del proyecto.
 * @author DeusHdezT
 * @version 1.0
 */
@Entity(name = "municipio")
public class Town {
	
	@Id
	@Column(name = "id_municipio")
	private Integer _id;
	
	@Column(name = "nombre_municipio")
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_depto")
	private Depto depto;
	
	@OneToMany(mappedBy = "town", fetch = FetchType.LAZY)
	private List<TownAudit> townAudits;

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

	public Depto getDepto() {
		return depto;
	}

	public void setDepto(Depto depto) {
		this.depto = depto;
	}

	public List<TownAudit> getTownAudits() {
		return townAudits;
	}

	public void setTownAudits(List<TownAudit> townAudits) {
		this.townAudits = townAudits;
	}
	
}
