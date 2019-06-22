package com.kdc.cnema.dtos;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SignupDTO {
	
	@NotEmpty
	private String firstanme;
	
	@NotEmpty
	private String lastname;
	
	@NotEmpty
	private String username;
	
	@NotEmpty
	private String address;
	
	@NotEmpty
	private String password;
	
	@NotNull
	private Date birthDate;
	
	@NotNull
	private Integer idCountry;

	public SignupDTO(String firstanme, String lastname, String username, String address, String password,
			Date birthDate, Integer idCountry) {
		super();
		
		this.firstanme = firstanme;
		this.lastname = lastname;
		this.username = username;
		this.address = address;
		this.password = password;
		this.birthDate = birthDate;
		this.idCountry = idCountry;
	}

	public String getFirstanme() {
		return firstanme;
	}

	public void setFirstanme(String firstanme) {
		this.firstanme = firstanme;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getIdCountry() {
		return idCountry;
	}

	public void setIdCountry(Integer idCountry) {
		this.idCountry = idCountry;
	}
	
}
