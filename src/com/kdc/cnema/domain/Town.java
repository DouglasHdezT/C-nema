package com.kdc.cnema.domain;

import java.util.List;

import javax.persistence.CascadeType;
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

import com.kdc.cnema.domain.audit.TownAudit;

/**
 * Clase que mapea la entidad "municipio" en la base de datos del proyecto.
 * @author DeusHdezT
 * @version 1.0
 */
@Entity
@Table(name = "municipio", schema = "public")
public class Town {
	
	@Id
	@Column(name = "id_municipio")
	@GeneratedValue(generator = "municipio_id_municipio_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "municipio_id_municipio_seq" , sequenceName = "	public.municipio_id_municipio_seq", allocationSize = 1)
	private Integer id;
	
	@Column(name = "nombre_municipio")
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_depto")
	private Depto depto;

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

	public Depto getDepto() {
		return depto;
	}

	public void setDepto(Depto depto) {
		this.depto = depto;
	}
	
}
