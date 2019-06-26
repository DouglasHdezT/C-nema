package com.kdc.cnema.dtos;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers.BigDecimalDeserializer;

public class BalanceDTO {
	
	@NotNull
	@JsonDeserialize(using = BigDecimalDeserializer.class)
	private BigDecimal ToChange;
	
	@NotBlank
	private String password;

	
	public BalanceDTO() {
		super();
	}

	public BalanceDTO(@NotNull BigDecimal toChange, @NotBlank String password) {
		super();
		ToChange = toChange;
		this.password = password;
	}
	

	public BigDecimal getToChange() {
		return ToChange;
	}

	public void setToChange(BigDecimal toChange) {
		ToChange = toChange;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
