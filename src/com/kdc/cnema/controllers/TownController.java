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


import com.kdc.cnema.domain.Town;
import com.kdc.cnema.dtos.ResponseDTO;
import com.kdc.cnema.service.TownService;

@RestController
@CrossOrigin(origins = "*")
public class TownController {
	
	@Autowired
	TownService townService;
	
	@RequestMapping("/town/all")
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
	
	@RequestMapping(value="/towns/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> insertTown(@RequestBody @Valid Town town, BindingResult result){
		
		String message = "Default message";
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			if(result.hasErrors()) {
				message = "Campos de municipios invalidos";
				code = HttpStatus.BAD_REQUEST;
			}else {
				Town townAux = townService.findOneByName(town.getName());
				
				if(townAux != null) {
					message = "Categoria ya existe";
					code = HttpStatus.CONFLICT;
				}else {
					townService.save(town);
					message = "Municipio insertada con éxito";
					code = HttpStatus.OK;
				}
				
			}
		} catch (Exception e) {
			message = "Error interno de servidor";
			code = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(message), code);		
	}

	
}
