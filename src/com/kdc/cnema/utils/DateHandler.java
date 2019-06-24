package com.kdc.cnema.utils;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class DateHandler extends StdDeserializer<Date>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DateHandler(Class<?> vc) {
		super(vc);
		// TODO Auto-generated constructor stub
	}
	
	public DateHandler()
	{
		this(null); 
	}

	@Override
	public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		
		String date = p.getText();
		
		try {
			return new Date(Long.parseLong(date));
		}catch (Exception e) {
			return null;
		}
		
	}
}
