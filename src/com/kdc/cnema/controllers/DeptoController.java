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
import com.kdc.cnema.dtos.ResponseDTO;
import com.kdc.cnema.exceptions.MalformedAuthHeader;
import com.kdc.cnema.service.CountryService;
import com.kdc.cnema.service.DeptoService;
import com.kdc.cnema.utils.JwtPayload;

@RestController
@CrossOrigin(origins = "*")
public class DeptoController {
	
	@Autowired
	DeptoService deptoService;
	
	@Autowired
	CountryService countryService;
	
	@RequestMapping("/deptos/all")
	public ResponseEntity<List<Depto>> getAllDeptos(@RequestHeader("Authorization") String authHeader){
		List<Depto> deptos =  new ArrayList<>();	
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		//System.out.println(authHeader);
		
		try {
			JwtPayload.validateToken(authHeader);
			
			deptos = deptoService.findAll();
			code = HttpStatus.OK;
		}catch (io.jsonwebtoken.SignatureException e) {
			code = HttpStatus.BAD_REQUEST;
		}catch (MalformedAuthHeader e) {
			code = HttpStatus.BAD_REQUEST;
		}catch (Exception e) {
			e.printStackTrace();
			code = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		
		return new ResponseEntity<List<Depto>>(
				deptos,
				code);
	}
	
	
	@RequestMapping("/deptos/{id}")
	public ResponseEntity<Depto> getTown(@PathVariable(value = "id") Integer id){
		Depto depto = new Depto();
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			depto = deptoService.findOneById(id);
			
			if(depto != null) {
				code = HttpStatus.OK;
			}else {
				code = HttpStatus.NOT_FOUND; 
				depto =  new Depto();
			}
		} catch (Exception e) {
			e.printStackTrace();
			code = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Depto>(depto, code);
	}
	
	@RequestMapping(value="/deptos/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> insertDepto(@RequestBody @Valid Depto depto, BindingResult result){
		
		String message = "Default message";
		HttpStatus code = HttpStatus.BAD_REQUEST;
		Country country = countryService.findOneById(depto.getCountry().getId());
		
		try {
			if(result.hasErrors()) {
				message = "Campos de departamentos invalidos";
				code = HttpStatus.BAD_REQUEST;
			}else {
				Depto deptoAux = deptoService.findOneByName(depto.getName());
				
				if(deptoAux != null) {
					message = "Pais ya existe";
					code = HttpStatus.CONFLICT;
				}else {
					
					if(country == null) {
						message = "Pais inexistente";
						code = HttpStatus.CONFLICT;
					}
					else {
						depto.setCountry(country);
						deptoService.save(depto);
						message = "Departamento insertada con exito";
						code = HttpStatus.OK;
					}
				}
				
			}
		} catch (Exception e) {
			message = "Error interno de servidor";
			code = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(message), code);		
	}
	

}
