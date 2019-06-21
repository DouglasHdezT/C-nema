package com.kdc.cnema.utils;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtPayload {
	
	private String subject;
	
	private Date issuedAtTime;
	
	private String type;
	
	private String uid;
	

	public JwtPayload(String subject, Date issuedAtTime, String type, String uid) {
		super();
		this.subject = subject;
		this.issuedAtTime = issuedAtTime;
		this.type = type;
		this.uid = uid;
	}

	public static String generateToken(JwtPayload playload) {
		return Jwts.builder().setSubject(playload.subject)
	            .claim("type", playload.type)
	            .claim("uid", playload.uid)
	            .setIssuedAt(playload.issuedAtTime)
	            .signWith(SignatureAlgorithm.HS256, "secretkey").compact(); 
	}
	
	public static JwtPayload decodeToken(String token) {
		Claims claims = Jwts.parser()
			.parseClaimsJws(token).getBody();
		
		return null;
	}

}
