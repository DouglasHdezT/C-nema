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
import com.kdc.cnema.dtos.ResponseDTO;
import com.kdc.cnema.service.CountryService;

@RestController
@CrossOrigin(origins = "*")
public class CountryController {
	
	@Autowired
	CountryService countryService;

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
	
	@RequestMapping(value="/countries/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> insertCountry(@RequestBody @Valid Country country, BindingResult result){
		
		String message = "Default message";
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			if(result.hasErrors()) {
				message = "Campos paises invalidos";
				code = HttpStatus.BAD_REQUEST;
			}else {
				Country countryAux = countryService.findOneByName(country.getName());
				
				if(countryAux != null) {
					message = "Pais ya existe";
					code = HttpStatus.CONFLICT;
				}else {
					countryService.save(country);
					message = "Categoria insertada con éxito";
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
