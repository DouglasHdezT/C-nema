package com.kdc.cnema.dtos;

public class GetTokenDTO {
	
	public String token;
	public String password;
	public String getToken() {
		return token;
	}
	
	
	public GetTokenDTO() {
		super();
	}


	public GetTokenDTO(String token, String password) {
		super();
		this.token = token;
		this.password = password;
	}


	public void setToken(String token) {
		this.token = token;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
