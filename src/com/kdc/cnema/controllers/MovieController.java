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

import com.kdc.cnema.domain.Category;
import com.kdc.cnema.domain.Movie;
import com.kdc.cnema.domain.User;
import com.kdc.cnema.dtos.ResponseDTO;
import com.kdc.cnema.exceptions.MalformedAuthHeader;
import com.kdc.cnema.service.CategoryService;
import com.kdc.cnema.service.MovieService;
import com.kdc.cnema.service.UserService;
import com.kdc.cnema.utils.JwtPayload;

@RestController
@CrossOrigin(origins = "*")
public class MovieController {
	
	@Autowired
	MovieService movieService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/movies/all")
	public ResponseEntity<List<Movie>> getAllMovies(@RequestHeader("Authorization") String authHeader){
		List<Movie> movies =  new ArrayList<>();	
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			JwtPayload.validateToken(authHeader);
			
			movies = movieService.findAll();
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
		
		return new ResponseEntity<List<Movie>>(
				movies,
				code);
	}
	
	
	@RequestMapping("/movies/{id}")
	public ResponseEntity<Movie> getMovie(@PathVariable(value = "id") Integer id, @RequestHeader("Authorization") String authHeader){
		Movie movie = new Movie();
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			JwtPayload.validateToken(authHeader);
			
			movie = movieService.findOneById(id);
			
			if(movie != null) {
				code = HttpStatus.OK;
			}else {
				code = HttpStatus.NOT_FOUND; 
				movie =  new Movie();
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
		
		return new ResponseEntity<Movie>(movie, code);
	}
	
	@RequestMapping(value="/movies/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> insertMovie(@RequestBody @Valid Movie movie, @RequestHeader("Authorization") String authHeader,
			BindingResult result){
		
		String message = "Default message";
		HttpStatus code = HttpStatus.BAD_REQUEST;
		Category category = categoryService.findOneById(movie.getCategory().getId());
		
		try {
			JwtPayload.validateToken(authHeader);
			JwtPayload payload = JwtPayload.decodeToken(authHeader.substring(7));
			
			if(result.hasErrors()) {
				message = "Campos de pelicula invalidos";
				code = HttpStatus.BAD_REQUEST;
			}else {
				Movie movieAux = movieService.findOneByTitle(movie.getTitle());
				
				User user = userService.findOneById(Integer.parseInt(payload.getUid()));
				
				if(user.getType() == 0) {
					message = "Usuario no autorizado";
					code = HttpStatus.FORBIDDEN;
				}else if(movieAux != null) {
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
