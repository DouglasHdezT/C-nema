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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kdc.cnema.domain.Category;
import com.kdc.cnema.dtos.ResponseDTO;
import com.kdc.cnema.service.CategoryService;

@RestController
@CrossOrigin(origins = "*")
public class CategoryController {
	
	@Autowired
	CategoryService cateogryService;
	
	@RequestMapping("/categories/all")
	public ResponseEntity<List<Category>> getAllCategories(){
		List<Category> categories =  new ArrayList<>();	
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			categories = cateogryService.findAll();
			code = HttpStatus.OK;
		}catch (Exception e) {
			e.printStackTrace();
			code = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		
		return new ResponseEntity<List<Category>>(
				categories,
				code);
	}
	
	@RequestMapping("/category/{id}")
	public ResponseEntity<Category> getCategory(@PathVariable(value = "id") Integer id){
		Category category = new Category();
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			category = cateogryService.findOneById(id);
			
			if(category != null) {
				code = HttpStatus.OK;
			}else {
				code = HttpStatus.NOT_FOUND; 
				category =  new Category();
			}
		} catch (Exception e) {
			e.printStackTrace();
			code = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Category>(category, code);
	}
	
	@RequestMapping(value="/category/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> insertCategory(@RequestBody @Valid Category category, BindingResult result){
		
		String message = "Default message";
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			if(result.hasErrors()) {
				message = "Campos de la categoria invalidos";
				code = HttpStatus.BAD_REQUEST;
			}else {
				Category categoryAux = cateogryService.findOneByName(category.getName());
				
				if(categoryAux != null) {
					message = "Categoria ya existe";
					code = HttpStatus.CONFLICT;
				}else {
					cateogryService.save(category);
					message = "Categoria insertada con �xito";
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
