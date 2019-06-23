package com.kdc.cnema.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kdc.cnema.domain.Country;
import com.kdc.cnema.domain.User;
import com.kdc.cnema.dtos.ResponseDTO;
import com.kdc.cnema.exceptions.MalformedAuthHeader;
import com.kdc.cnema.service.CountryService;
import com.kdc.cnema.service.UserService;
import com.kdc.cnema.utils.JwtPayload;

@RestController
@CrossOrigin(origins = "*")
public class CountryController {
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	UserService userService;

	@RequestMapping("/countries/all")
	public ResponseEntity<List<Country>> getAllCountries(){
		List<Country> countries =  new ArrayList<>();	
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			countries = countryService.findAll();
			code = HttpStatus.OK;
		}catch (Exception e) {
			e.printStackTrace();
			code = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		
		return new ResponseEntity<List<Country>>(
				countries,
				code);
	}
	
	@RequestMapping("/countries/{id}")
	public ResponseEntity<Country> getCountry(@PathVariable(value = "id") Integer id, @RequestHeader("Authorization") String authHeader){
		Country country = new Country();
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			JwtPayload.validateToken(authHeader);
			country = countryService.findOneById(id);
			
			if(country != null) {
				code = HttpStatus.OK;
			}else {
				code = HttpStatus.NOT_FOUND; 
				country =  new Country();
			}
		}catch (io.jsonwebtoken.SignatureException e) {
			code = HttpStatus.FORBIDDEN;
		}catch (io.jsonwebtoken.MalformedJwtException e) {
			code = HttpStatus.FORBIDDEN;
		}catch (MalformedAuthHeader e) {
			code = HttpStatus.FORBIDDEN;
		}catch (Exception e) {
			e.printStackTrace();
			code = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Country>(country, code);
	}
	
	@RequestMapping(value="/countries/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> insertCountry(@RequestBody @Valid Country country, @RequestHeader("Authorization") String authHeader, 
			BindingResult result){
		
		String message = "Default message";
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			JwtPayload.validateToken(authHeader);
			JwtPayload payload = JwtPayload.decodeToken(authHeader.substring(7));
			
			if(result.hasErrors()) {
				message = "Campos paises invalidos";
				code = HttpStatus.BAD_REQUEST;
			}else {
				Country countryAux = countryService.findOneByName(country.getName());
				
				User user = userService.findOneById(Integer.parseInt(payload.getUid()));
				
				if(user.getType() == 0) {
					message = "Usuario no autorizado";
					code = HttpStatus.FORBIDDEN;
				}else if(countryAux != null) {
					message = "Pais ya existe";
					code = HttpStatus.CONFLICT;
				}else {
					countryService.save(country);
					message = "Categoria insertada con exito";
					code = HttpStatus.OK;
				}
				
			}
		}catch (io.jsonwebtoken.SignatureException e) {
			message = "Token invalido";
			code = HttpStatus.FORBIDDEN;
		}catch (io.jsonwebtoken.MalformedJwtException e) {
			message = "Token invalido";
			code = HttpStatus.FORBIDDEN;
		}catch (MalformedAuthHeader e) {
			message = "Token invalido";
			code = HttpStatus.FORBIDDEN;
		}catch (Exception e) {
			message = "Error interno de servidor";
			code = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(message), code);		
	}
	
	@RequestMapping("countries/update/{id}")
	public ResponseEntity<ResponseDTO> updateState(@PathVariable("id") Integer id, @RequestHeader("Authorization") String authHeader){
		
		String message = "Default message";
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			
			JwtPayload.validateToken(authHeader);
			JwtPayload payload = JwtPayload.decodeToken(authHeader.substring(7));
			
			Country country = countryService.findOneById(id);
			User user = userService.findOneById(Integer.parseInt(payload.getUid()));
			
			if(user != null && user.getType() == 0) {
				code = HttpStatus.FORBIDDEN;
				message = "Faltan permisos";
			}else if(country  == null) {
				code = HttpStatus.NOT_FOUND;
				message = "Categoria no encontrada";
			}else {
				countryService.updateState(id, !country.getStatus());
				code = HttpStatus.OK;
				message = "Estado modificado con exito";
			}
			
		}catch (io.jsonwebtoken.SignatureException e) {
			message = "Token invalido";
			code = HttpStatus.FORBIDDEN;
		}catch (io.jsonwebtoken.MalformedJwtException e) {
			message = "Token invalido";
			code = HttpStatus.FORBIDDEN;
		}catch (MalformedAuthHeader e) {
			message = "Token invalido";
			code = HttpStatus.FORBIDDEN;
		}catch (Exception e) {
			message = "Error interno de servidor";
			code = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(message), code);
	}
}
