package com.kdc.cnema.domain.audit;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Clase que mapea la entidad "depto_auditoria" en la base de datos del proyecto.
 * @author DeusHdezT
 * @version 1.0
 */
@Entity
@Table(name = "depto_auditoria", schema = "public")
public class DeptoAudit {

	@Id
	@Column(name = "id_categoria_auditoria")
	@GeneratedValue(generator = "depto_auditoria_id_depto_auditoria_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "depto_auditoria_id_depto_auditoria_seq" , sequenceName = "	public.depto_auditoria_id_depto_auditoria_seq", allocationSize = 1)
	private Integer id;
	
	@Column(name = "usuario_modificacion")
	private String userModifier;
	
	@Column(name = "fecha_modificacion")
	private Date modificationDate; 
	
	@Column(name = "campo_modificacion")
	private String modifiedField;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserModifier() {
		return userModifier;
	}

	public void setUserModifier(String userModifier) {
		this.userModifier = userModifier;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	public String getModifiedField() {
		return modifiedField;
	}

	public void setModifiedField(String modifiedField) {
		this.modifiedField = modifiedField;
	}

	
}
