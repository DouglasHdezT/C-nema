package com.kdc.cnema.domain.audit;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Clase que mapea la entidad "cuentas_auditoria" en la base de datos del proyecto.
 * @author DeusHdezT
 * @version 1.0
 */
@Entity(name = "cuentas_auditoria")
public class ProfileAudit {
	
	@Id
	@Column(name = "id_cuenta")
	private Integer _id;
	
	@Column(name = "usuario_modificacion")
	private String userModifier;
	
	@Column(name = "fecha_modificacion")
	private Date modificationDate;
	
	@Column(name = "tipo_accion_estado")
	private Boolean stateChanged;
	
	@Column(name = "razon")
	private String argument;
	
}
