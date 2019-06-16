package com.kdc.cnema.domain.audit;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Clase que mapea la entidad "depto_auditoria" en la base de datos del proyecto.
 * @author DeusHdezT
 * @version 1.0
 */
@Entity(name = "depto_auditoria")
public class DeptoAudit {

	@Id
	@Column(name = "id_categoria_auditoria")
	private Integer _id;
	
	@Column(name = "usuario_modificacion")
	private String userModifier;
	
	@Column(name = "fecha_modificacion")
	private Date modificationDate; 
	
}
