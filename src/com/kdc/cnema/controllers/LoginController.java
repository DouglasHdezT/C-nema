package com.kdc.cnema.controllers;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kdc.cnema.domain.Country;
import com.kdc.cnema.domain.User;
import com.kdc.cnema.dtos.LoginForm;
import com.kdc.cnema.dtos.ResponseDTO;
import com.kdc.cnema.dtos.SignupDTO;
import com.kdc.cnema.repositories.CountryRepository;
import com.kdc.cnema.service.CountryService;
import com.kdc.cnema.service.UserService;
import com.kdc.cnema.utils.JwtPayload;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> main(@RequestBody LoginForm userSubmitted) {
		String message = "Default message";
		HttpStatus code = HttpStatus.FORBIDDEN;
		
		try {
			User user = userService.findOneByUsernameAndPassword(userSubmitted.getUsername(), 
					passwordEncoder.encode(userSubmitted.getPassword())); 
			
			if(user != null) {
				message = JwtPayload.generateToken(new JwtPayload(user.getUsername(), new Date(), user.getType()+"", user.getId()+""));
				code = HttpStatus.OK;
			}else {
				message = "Error en las credenciales";
				code = HttpStatus.FORBIDDEN;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			message = "Error interno del servidor";
			code = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		
		return new ResponseEntity<>(new ResponseDTO(message), code);
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> signup(@RequestBody @Valid User tempUser,   BindingResult result){
		
		String message = "Default Message";
		HttpStatus responseCode = HttpStatus.OK;
		
		if(result.hasErrors()) {
			message = "Revisar campos de usuario";
			responseCode = HttpStatus.BAD_REQUEST;
		}else {
			if(userService.findOneByUsername(tempUser.getUsername()) != null) {
				message = "Este usuario ya existe";
				responseCode = HttpStatus.BAD_REQUEST;
			}else {
				try {
					Country country = countryService.findOneById(tempUser.getCountry().getId());
					//System.out.println("Val :"+country.getName()+ " Id"+ country.getId());
					
					tempUser.setCountry(country);
					tempUser.setCurrCredit(new BigDecimal(20));
					tempUser.setType(0);
					
					User user = userService.save(tempUser);
					
					message = JwtPayload.generateToken(new JwtPayload(user.getUsername(), new Date(), user.getType()+"", user.getId()+""));
					responseCode = HttpStatus.OK;
				}catch (Exception e) {
					e.printStackTrace();
					message = "Error al ingresar usuario";
					responseCode = HttpStatus.INTERNAL_SERVER_ERROR;
				}
			}
			
			
		}
		
		return new ResponseEntity<>(new ResponseDTO(message), responseCode);
	}
	
	@RequestMapping(value = "/getTokenRip")
	public String test() {
		return JwtPayload.generateToken(new JwtPayload("Puto el que lo lea", new Date(), "sirviente", "Me la pela perro"));
	}
	
	@RequestMapping(value = "/test")
	public JwtPayload jwtTest(@RequestParam String token){
		return JwtPayload.decodeToken(token);
	}
	
	@RequestMapping(value = "/test2")
	public boolean jwtTest2(@RequestParam String token){
		return JwtPayload.isValidToken(token);
	}
	
}
