package com.kdc.cnema.domain;

import java.util.Date;
import java.util.List;

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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kdc.cnema.domain.audit.ProfileAudit;

/**
 * Clase que mapea la entidad "usuario" en la base de datos del proyecto.
 * @author DeusHdezT
 * @version 1.0
 */
@Entity
@Table(name = "usuario", schema = "public")
public class User {

	@Id
	@Column(name = "id_usuario")
	@GeneratedValue(generator = "usuario_id_usuario_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "usuario_id_usuario_seq" , sequenceName = "public.usuario_id_usuario_seq", allocationSize = 1)
	private Integer id;
	
	@NotNull
	@Min(0)
	@Max(1)
	@Column(name = "tipo_usuario")
	private Integer type;
	
	@NotEmpty
	@Column(name = "nombre_usuario")
	private String fistname;
	
	@NotEmpty
	@Column(name = "apellido_usuario")
	private String lastname;
	
	@NotNull
	@Column(name = "fecha_nacimiento")
	private Date birthDate;
	
	@NotEmpty
	@Column(name = "direccion")
	private String address;
	
	@NotNull
	@Column(name = "estado")
	private Boolean status;
	
	@Column(name = "is_logged")
	private Boolean logged;
	
	@NotEmpty
	@Column(name = "username")
	private String username;
	
	@NotEmpty
	@Column(name = "password_usuario")
	private String password;
	
	@Column(name = "saldo")
	private Float currCredit;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_pais", referencedColumnName= "id_pais")
	private Country country;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Reservation> reservations;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<ProfileAudit> profileAudits;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getFistname() {
		return fistname;
	}

	public void setFistname(String fistname) {
		this.fistname = fistname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	
	public Boolean getLogged() {
		return logged;
	}

	public void setLogged(Boolean logged) {
		this.logged = logged;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Float getCurrCredit() {
		return currCredit;
	}

	public void setCurrCredit(Float currCredit) {
		this.currCredit = currCredit;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public List<ProfileAudit> getProfileAudits() {
		return profileAudits;
	}

	public void setProfileAudits(List<ProfileAudit> profileAudits) {
		this.profileAudits = profileAudits;
	}

}
