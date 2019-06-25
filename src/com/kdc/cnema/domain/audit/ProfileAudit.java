package com.kdc.cnema.domain.audit;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.TimestampDeserializer;
import com.kdc.cnema.domain.User;

/**
 * Clase que mapea la entidad "cuentas_auditoria" en la base de datos del proyecto.
 * @author DeusHdezT
 * @version 1.0
 */
@Entity
@Table(name = "cuentas_auditoria", schema = "public")
public class ProfileAudit {
	
	@Id
	@Column(name = "id_cuenta")
	@GeneratedValue(generator = "cuentas_auditoria_id_cuenta_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "cuentas_auditoria_id_cuenta_seq" , sequenceName = "public.cuentas_auditoria_id_cuenta_seq", allocationSize = 1)
	private Integer _id;
	
	@Column(name = "usuario_modificacion")
	private String userModifier;
	
	@Column(name = "fecha_modificacion")
	@JsonDeserialize(using = TimestampDeserializer.class)
	private Timestamp modificationDate;
	
	@Column(name = "tipo_accion_estado")
	private Boolean stateChanged;
	
	@Column(name = "razon")
	private String argument;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name ="id_usuario")
	private User user;

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
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

	public Boolean getStateChanged() {
		return stateChanged;
	}

	public void setStateChanged(Boolean stateChanged) {
		this.stateChanged = stateChanged;
	}

	public String getArgument() {
		return argument;
	}

	public void setArgument(String argument) {
		this.argument = argument;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

		
}
