package com.kdc.cnema.domain.audit;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.TimestampDeserializer;

/**
 * Clase que mapea la entidad "categoria_auditoria" en la base de datos del proyecto.
 * @author DeusHdezT
 * @version 1.0
 */
@Entity
@Table(name = "categoria_auditoria", schema = "public")
public class CategoryAudit {
	
	@Id
	@Column(name = "id_categoria_auditoria")
	@GeneratedValue(generator = "categoria_auditoria_id_categoria_auditoria_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "categoria_auditoria_id_categoria_auditoria_seq" , sequenceName = "public.categoria_auditoria_id_categoria_auditoria_seq", allocationSize = 1)
	private Integer id;
	
	@Column(name = "usuario_modificacion")
	private String userModifier;
	
	@Column(name = "fecha_modificacion")
	@JsonDeserialize(using = TimestampDeserializer.class)
	private Timestamp modificationDate;
	
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

	public Timestamp getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Timestamp modificationDate) {
		this.modificationDate = modificationDate;
	}

	public String getModifiedField() {
		return modifiedField;
	}

	public void setModifiedField(String modifiedField) {
		this.modifiedField = modifiedField;
	}

	
}
