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
import com.kdc.cnema.domain.Depto;
import com.kdc.cnema.domain.User;
import com.kdc.cnema.domain.audit.DeptoAudit;
import com.kdc.cnema.dtos.ResponseDTO;
import com.kdc.cnema.exceptions.MalformedAuthHeader;
import com.kdc.cnema.service.CountryService;
import com.kdc.cnema.service.DeptoAuditService;
import com.kdc.cnema.service.DeptoService;
import com.kdc.cnema.service.UserService;
import com.kdc.cnema.utils.JwtPayload;

@RestController
@CrossOrigin(origins = "*")
public class DeptoController {
	
	@Autowired
	DeptoService deptoService;
	
	@Autowired
	DeptoAuditService auditService;
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping("/deptos/all")
	public ResponseEntity<List<Depto>> getAllDeptos(){
		List<Depto> deptos =  new ArrayList<>();	
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {

			deptos = deptoService.findAll();
			code = HttpStatus.OK;
			
		}catch (Exception e) {
			e.printStackTrace();
			code = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		
		return new ResponseEntity<List<Depto>>(deptos, code);
	}
	
	@RequestMapping("/deptos/all/audits")
	public ResponseEntity<List<DeptoAudit>> getAllAudits(@RequestHeader("Authorization") String authHeader){
		List<DeptoAudit> audits =  new ArrayList<>();	
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
		
		return new ResponseEntity<List<DeptoAudit>>(audits, code);
	}
	
	@RequestMapping("/deptos/{id}")
	public ResponseEntity<Depto> getTown(@PathVariable(value = "id") Integer id, @RequestHeader("Authorization") String authHeader){
		Depto depto = new Depto();
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			JwtPayload.validateToken(authHeader);
			
			depto = deptoService.findOneById(id);
			
			if(depto != null) {
				code = HttpStatus.OK;
			}else {
				code = HttpStatus.NOT_FOUND; 
				depto =  new Depto();
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
		
		return new ResponseEntity<Depto>(depto, code);
	}
	
	@RequestMapping(value="/deptos/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> insertDepto(@RequestBody @Valid Depto depto, @RequestHeader("Authorization") String authHeader,
			BindingResult result){
		
		String message = "Default message";
		HttpStatus code = HttpStatus.BAD_REQUEST;
		Country country = countryService.findOneById(depto.getCountry().getId());
		
		try {
			JwtPayload.validateToken(authHeader);
			JwtPayload payload = JwtPayload.decodeToken(authHeader.substring(7));
			
			if(result.hasErrors()) {
				message = "Campos de departamentos invalidos";
				code = HttpStatus.BAD_REQUEST;
			}else {
				User user = userService.findOneById(Integer.parseInt(payload.getUid()));
				
				if(user.getType() == 0) {
					message = "Usuario no autorizado";
					code = HttpStatus.FORBIDDEN;
				}else if(country == null) {
					message = "Pais inexistente";
					code = HttpStatus.CONFLICT;
				}
				else {
					depto.setCountry(country);
					deptoService.save(depto, user.getUsername());
					message = "Departamento insertada con exito";
					code = HttpStatus.OK;
				}
			}
		} catch (io.jsonwebtoken.SignatureException e) {
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
	
	@RequestMapping("/deptos/update/{id}")
	public ResponseEntity<ResponseDTO> updateState(@PathVariable("id") Integer id, @RequestHeader("Authorization") String authHeader){
		
		String message = "Default message";
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			
			JwtPayload.validateToken(authHeader);
			JwtPayload payload = JwtPayload.decodeToken(authHeader.substring(7));
			
			Depto depto = deptoService.findOneById(id);
			User user = userService.findOneById(Integer.parseInt(payload.getUid()));
			
			if(user != null && user.getType() == 0) {
				code = HttpStatus.FORBIDDEN;
				message = "Faltan permisos";
			}else if(depto  == null) {
				code = HttpStatus.NOT_FOUND;
				message = "Categoria no encontrada";
			}else {
				deptoService.updateState(id, !depto.getStatus(), user.getUsername());
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
