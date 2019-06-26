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

import com.kdc.cnema.domain.Cinema;
import com.kdc.cnema.domain.User;
import com.kdc.cnema.domain.audit.CinemaAudit;
import com.kdc.cnema.dtos.ResponseDTO;
import com.kdc.cnema.exceptions.MalformedAuthHeader;
import com.kdc.cnema.service.CinemaAuditService;
import com.kdc.cnema.service.CinemaService;
import com.kdc.cnema.service.UserService;
import com.kdc.cnema.utils.JwtPayload;

@RestController
@CrossOrigin(origins = "*")
public class CinemaController {
	
	@Autowired
	CinemaService cinemaService;
	
	@Autowired
	CinemaAuditService auditService;
	
	@Autowired
	UserService userService;

	
	@RequestMapping("/cinemas/all")
	public ResponseEntity<List<Cinema>> getAllCinemas(@RequestHeader("Authorization") String authHeader){
		List<Cinema> cinemas =  new ArrayList<>();	
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			JwtPayload.validateToken(authHeader);
			
			cinemas = cinemaService.findAll();
			code = HttpStatus.OK;
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
		
		return new ResponseEntity<List<Cinema>>(cinemas, code);
	}
	
	@RequestMapping("/countries/all/audits")
	public ResponseEntity<List<CinemaAudit>> getAllAudits(@RequestHeader("Authorization") String authHeader){
		List<CinemaAudit> audits =  new ArrayList<>();	
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			JwtPayload.validateToken(authHeader);
			JwtPayload payload = JwtPayload.decodeToken(authHeader.substring(7));
			
			User user = userService.findOneById(Integer.parseInt(payload.getUid()));
			
			if(user != null && user.getType() == 0) {
				code = HttpStatus.FORBIDDEN;
			}else {
				audits = auditService.findAll();
				code = HttpStatus.OK;
			}
			
		}catch (io.jsonwebtoken.SignatureException e) {
			code = HttpStatus.FORBIDDEN;
		}catch (io.jsonwebtoken.MalformedJwtException e) {
			code = HttpStatus.FORBIDDEN;
		}catch (MalformedAuthHeader e) {
			code = HttpStatus.FORBIDDEN;
		}catch (Exception e) {
			e.printStackTrace();
			code=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<List<CinemaAudit>>(audits, code);
	}
	
	@RequestMapping("/cinemas/{id}")
	public ResponseEntity<Cinema> getCinema(@PathVariable(value = "id") Integer id, @RequestHeader("Authorization") String authHeader){
		Cinema cinema = new Cinema();
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			JwtPayload.validateToken(authHeader);
			
			cinema = cinemaService.findOneById(id);
			
			if(cinema != null) {
				code = HttpStatus.OK;
			}else {
				code = HttpStatus.NOT_FOUND; 
				cinema =  new Cinema();
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
		
		return new ResponseEntity<Cinema>(cinema, code);
	}
	
	@RequestMapping(value="/cinemas/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> insertCinema(@RequestBody @Valid Cinema cinema, @RequestHeader("Authorization") String authHeader,
			BindingResult result){
		
		String message = "Default message";
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			JwtPayload.validateToken(authHeader);
			JwtPayload payload = JwtPayload.decodeToken(authHeader.substring(7));
			
			if(result.hasErrors()) {
				message = "Campos de sala invalidos";
				code = HttpStatus.BAD_REQUEST;
			}else {
				User user = userService.findOneById(Integer.parseInt(payload.getUid()));
				
				if(user.getType() == 0) {
					message = "Usuario no autorizado";
					code = HttpStatus.FORBIDDEN;
				}
				else{
					cinemaService.save(cinema, user.getUsername());
					message = "Sala insertada con exito";
					code = HttpStatus.OK;
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
			message = "Error interno de servidor";
			code = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(message), code);		
	}
	
	@RequestMapping("/cinemas/update/{id}")
	public ResponseEntity<ResponseDTO> updateState(@PathVariable("id") Integer id, @RequestHeader("Authorization") String authHeader){
		
		String message = "Default message";
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			
			JwtPayload.validateToken(authHeader);
			JwtPayload payload = JwtPayload.decodeToken(authHeader.substring(7));
			
			Cinema cinema = cinemaService.findOneById(id);
			User user = userService.findOneById(Integer.parseInt(payload.getUid()));
			
			if(user != null && user.getType() == 0) {
				code = HttpStatus.FORBIDDEN;
				message = "Faltan permisos";
			}else if(cinema  == null) {
				code = HttpStatus.NOT_FOUND;
				message = "Categoria no encontrada";
			}else {
				cinemaService.updateState(id, !cinema.getStatus(), user.getUsername());
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
