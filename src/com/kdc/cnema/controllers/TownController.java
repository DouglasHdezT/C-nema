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

import com.kdc.cnema.domain.Depto;
import com.kdc.cnema.domain.Town;
import com.kdc.cnema.domain.User;
import com.kdc.cnema.dtos.ResponseDTO;
import com.kdc.cnema.exceptions.MalformedAuthHeader;
import com.kdc.cnema.service.DeptoService;
import com.kdc.cnema.service.TownService;
import com.kdc.cnema.service.UserService;
import com.kdc.cnema.utils.JwtPayload;

@RestController
@CrossOrigin(origins = "*")
public class TownController {
	
	@Autowired
	TownService townService;
	
	@Autowired
	DeptoService deptoService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/towns/all")
	public ResponseEntity<List<Town>> getAllTowns(){
		List<Town> towns =  new ArrayList<>();	
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			towns = townService.findAll();
			code = HttpStatus.OK;
		}catch (Exception e) {
			e.printStackTrace();
			code = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		
		return new ResponseEntity<List<Town>>(
				towns,
				code);
	}
	
	
	@RequestMapping("/towns/{id}")
	public ResponseEntity<Town> getTown(@PathVariable(value = "id") Integer id, @RequestHeader("Authorization") String authHeader){
		Town town = new Town();
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			JwtPayload.validateToken(authHeader);
			
			town = townService.findOneById(id);
			
			if(town != null) {
				code = HttpStatus.OK;
			}else {
				code = HttpStatus.NOT_FOUND; 
				town =  new Town();
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
		
		return new ResponseEntity<Town>(town, code);
	}
	
	
	@RequestMapping(value="/towns/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> insertTown(@RequestBody @Valid Town town, @RequestHeader("Authorization") String authHeader
			, BindingResult result){
		
		String message = "Default message";
		HttpStatus code = HttpStatus.BAD_REQUEST;
		Depto depto = deptoService.findOneById(town.getDepto().getId());
		
		try {
			JwtPayload.validateToken(authHeader);
			JwtPayload payload = JwtPayload.decodeToken(authHeader.substring(7));
			
			if(result.hasErrors()) {
				message = "Campos de municipios invalidos";
				code = HttpStatus.BAD_REQUEST;
			}else {

				User user = userService.findOneById(Integer.parseInt(payload.getUid()));
				
				if(user.getType() == 0) {
					message = "Usuario no autorizado";
					code = HttpStatus.FORBIDDEN;
				}else if(depto==null) {
					message = "Departamento inexistente";
					code = HttpStatus.CONFLICT;
				}
				else {
					town.setDepto(depto);
					townService.save(town);
					message = "Municipio insertado con exito";
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

	
}
