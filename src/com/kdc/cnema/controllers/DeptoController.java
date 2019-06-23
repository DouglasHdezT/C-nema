package com.kdc.cnema.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kdc.cnema.domain.Country;
import com.kdc.cnema.domain.Depto;
import com.kdc.cnema.dtos.ResponseDTO;
import com.kdc.cnema.service.CountryService;
import com.kdc.cnema.service.DeptoService;

@RestController
@CrossOrigin(origins = "*")
public class DeptoController {
	
	@Autowired
	DeptoService deptoService;
	
	@Autowired
	CountryService countryService;
	
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
		
		
		return new ResponseEntity<List<Depto>>(
				deptos,
				code);
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
						message = "Departamento insertada con éxito";
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
