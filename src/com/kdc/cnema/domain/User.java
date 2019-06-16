package com.kdc.cnema.domain;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Clase que mapea la entidad "usuario" en la base de datos del proyecto.
 * @author DeusHdezT
 * @version 1.0
 */
@Entity(name = "usuario")
public class User {

	@Id
	@Column(name = "id_usuario")
	private Integer _id;
	
	@Column(name = "tipo_usuario")
	private Integer type;
	
	@Column(name = "nombre_usuario")
	private String fistname;
	
	@Column(name = "apellido_usuario")
	private String lastname;
	
	@Column(name = "fecha_nacimiento")
	private Date birthDate;
	
	@Column(name = "direccion")
	private String address;
	
	@Column(name = "estado")
	private Boolean status;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password_usuario")
	private String password;
	
	@Column(name = "saldo")
	private BigDecimal currCredit;
}
