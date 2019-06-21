package com.kdc.cnema.controllers;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kdc.cnema.dtos.LoginForm;
import com.kdc.cnema.utils.JwtPayload;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {
	
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String main(@RequestBody LoginForm userSubmitted) {
		if(userSubmitted.getUsername() != null) {
			return "Holi ¿";
		}else {
			return "No sirvio";
		}
	}
	
	@RequestMapping(value = "/test")
	public JwtPayload jwtTest(@RequestParam String token){
		return JwtPayload.decodeToken(token);
	}
	
}
