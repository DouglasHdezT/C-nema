package com.kdc.cnema.utils;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class BigDecimalHadler extends StdDeserializer<BigDecimal>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BigDecimalHadler(Class<?> vc) {
		super(vc);
		// TODO Auto-generated constructor stub
	}
	
	public BigDecimalHadler() {
		this(null);
	}

	@Override
	public BigDecimal deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		try {
			return new BigDecimal(p.getFloatValue());
		} catch (Exception e) {
			return null;
		}
	}

}
