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
import com.kdc.cnema.domain.Movie;
import com.kdc.cnema.domain.Town;
import com.kdc.cnema.dtos.ResponseDTO;
import com.kdc.cnema.service.CategoryService;
import com.kdc.cnema.service.MovieService;

@RestController
@CrossOrigin(origins = "*")
public class MovieController {
	
	@Autowired
	MovieService movieService;
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping("/movies/all")
	public ResponseEntity<List<Movie>> getAllMovies(){
		List<Movie> movies =  new ArrayList<>();	
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			movies = movieService.findAll();
			code = HttpStatus.OK;
		}catch (Exception e) {
			e.printStackTrace();
			code = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		
		return new ResponseEntity<List<Movie>>(
				movies,
				code);
	}
	
	
	@RequestMapping("/movies/{id}")
	public ResponseEntity<Movie> getMovie(@PathVariable(value = "id") Integer id){
		Movie movie = new Movie();
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			movie = movieService.findOneById(id);
			
			if(movie != null) {
				code = HttpStatus.OK;
			}else {
				code = HttpStatus.NOT_FOUND; 
				movie =  new Movie();
			}
		} catch (Exception e) {
			e.printStackTrace();
			code = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Movie>(movie, code);
	}
	
	@RequestMapping(value="/movies/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> insertMovie(@RequestBody @Valid Movie movie, BindingResult result){
		
		String message = "Default message";
		HttpStatus code = HttpStatus.BAD_REQUEST;
		Category category = categoryService.findOneById(movie.getCategory().getId());
		
		try {
			if(result.hasErrors()) {
				message = "Campos de la categoria invalidos";
				code = HttpStatus.BAD_REQUEST;
			}else {
				Movie movieAux = movieService.findOneByTitle(movie.getTitle());
				
				if(movieAux != null) {
					message = "Pelicula ya existe";
					code = HttpStatus.CONFLICT;
				}else {
					
					if(category == null) {
						message = "Categoria inexistente";
						code = HttpStatus.CONFLICT;
					}
					else {
						movie.setCategory(category);
						movieService.save(movie);
						message = "Pelicula insertada con exito";
						code = HttpStatus.OK;
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "Error interno de servidor";
			code = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(message), code);		
	}
	
}
