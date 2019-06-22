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
	
	public JwtPayload() {
		// TODO Auto-generated constructor stub
	}

	public static String generateToken(JwtPayload playload) {
		
		return Jwts.builder().setSubject(playload.subject)
	            .claim("type", playload.type)
	            .claim("uid", playload.uid)
	            .setIssuedAt(playload.issuedAtTime)
	            .signWith(SignatureAlgorithm.HS256, ConstantsAPI.JWT_SECRET).compact(); 
	}
	
	public static JwtPayload decodeToken(String token) {
		
		JwtPayload payload = new JwtPayload();
		
		//System.out.println(token);
		
		Claims claims = Jwts.parser().setSigningKey(ConstantsAPI.JWT_SECRET)
				.parseClaimsJws(token).getBody();
			
			payload.uid = (String) claims.get("uid");
			payload.type = (String) claims.get("type");
			payload.subject = claims.getSubject();
			payload.issuedAtTime  = claims.getIssuedAt();
		
		return payload;
	}
	
	public static boolean isValidToken(String token) {
		try {
			Jwts.parser().setSigningKey(ConstantsAPI.JWT_SECRET).parse(token);
			return true;
		}catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getIssuedAtTime() {
		return issuedAtTime;
	}

	public void setIssuedAtTime(Date issuedAtTime) {
		this.issuedAtTime = issuedAtTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	
	

}
