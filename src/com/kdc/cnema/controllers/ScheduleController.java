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
import com.kdc.cnema.domain.Movie;
import com.kdc.cnema.domain.Schedule;
import com.kdc.cnema.domain.User;
import com.kdc.cnema.dtos.ResponseDTO;
import com.kdc.cnema.exceptions.MalformedAuthHeader;
import com.kdc.cnema.service.CinemaService;
import com.kdc.cnema.service.MovieService;
import com.kdc.cnema.service.ScheduleService;
import com.kdc.cnema.service.UserService;
import com.kdc.cnema.utils.JwtPayload;

@RestController
@CrossOrigin(origins = "*")
public class ScheduleController {

	@Autowired
	ScheduleService scheduleService; 
	
	@Autowired
	CinemaService cinemaService;
	
	@Autowired
	MovieService movieService;
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping(value = "/schedules/all")
	public ResponseEntity<List<Schedule>> getAllSchedules(@RequestHeader("Authorization") String authHeader){
		List<Schedule> schedules = new ArrayList<>();
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			JwtPayload.validateToken(authHeader);
			
			schedules = scheduleService.findAll();
			code=HttpStatus.OK;
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
		
		return new ResponseEntity<List<Schedule>>(schedules, code);
	}
	
	@RequestMapping(value = "/schedules/all/active")
	public ResponseEntity<List<Schedule>> getAllSchedulesActive(@RequestHeader("Authorization") String authHeader){
		List<Schedule> schedules = new ArrayList<>();
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			JwtPayload.validateToken(authHeader);
			
			schedules = scheduleService.findAllActive();
			code=HttpStatus.OK;
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
		
		return new ResponseEntity<List<Schedule>>(schedules, code);
	}
	
	@RequestMapping(value = "/schedules/{id}")
	public ResponseEntity<Schedule> getSchedule(@PathVariable("id") Integer id, @RequestHeader("Authorization") String authHeader){
		Schedule schedule =  new Schedule();
		HttpStatus code = HttpStatus.BAD_GATEWAY;
		
		try {
			JwtPayload.validateToken(authHeader);
			
			schedule = scheduleService.findOneById(id);
			
			if(schedule == null) {
				schedule = new Schedule();
				code = HttpStatus.NOT_FOUND;
			}else {
				code = HttpStatus.OK;
			}
		}catch (io.jsonwebtoken.SignatureException e) {
			code = HttpStatus.FORBIDDEN;
		}catch (io.jsonwebtoken.MalformedJwtException e) {
			code = HttpStatus.FORBIDDEN;
		}catch (MalformedAuthHeader e) {
			code = HttpStatus.FORBIDDEN;
		}catch (Exception e) {
			code = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		
		return new ResponseEntity<Schedule>(schedule, code);
	}
	
	@RequestMapping(value = "/schedules/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> insertSchedule(@RequestBody @Valid Schedule schedule, @RequestHeader("Authorization") String authHeader, 
			BindingResult result){
		String message = "Default message";
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			JwtPayload.validateToken(authHeader);
			JwtPayload payload = JwtPayload.decodeToken(authHeader.substring(7));
			
			if(result.hasErrors()) {
				message = "Campos de horario invalidos";
				code = HttpStatus.BAD_REQUEST;
			}else {
				Cinema cinema = cinemaService.findOneById(schedule.getCinema().getId());
				Movie movie = movieService.findOneById(schedule.getMovie().getId());
				
				User user = userService.findOneById(Integer.parseInt(payload.getUid()));
				
				if(user == null || user.getType() == 0) {
					message = "Usuario no autorizado";
					code = HttpStatus.FORBIDDEN;
				}else if(cinema == null) {
					message = "Sala inexistente";
					code = HttpStatus.NOT_FOUND;
				}else if(movie == null) {
					message = "Pelicula inexistente";
					code = HttpStatus.NOT_FOUND;
				}else {
					schedule.setCinema(cinema);
					schedule.setMovie(movie);
					
					scheduleService.save(schedule);
					
					message = "Horario insertado con exito";
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
			e.printStackTrace();
			code = HttpStatus.INTERNAL_SERVER_ERROR;
			message = "Error interno de servidor";
		}
		
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(message), code);
	}
	
	@RequestMapping("/schedules/update/{id}")
	public ResponseEntity<ResponseDTO> updateState(@PathVariable("id") Integer id, @RequestHeader("Authorization") String authHeader){
		
		String message = "Default message";
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			
			JwtPayload.validateToken(authHeader);
			JwtPayload payload = JwtPayload.decodeToken(authHeader.substring(7));
			
			Schedule schedule= scheduleService.findOneById(id);
			User user = userService.findOneById(Integer.parseInt(payload.getUid()));
			
			if(user != null && user.getType() == 0) {
				code = HttpStatus.FORBIDDEN;
				message = "Faltan permisos";
			}else if(schedule  == null) {
				code = HttpStatus.NOT_FOUND;
				message = "Categoria no encontrada";
			}else {
				scheduleService.updateState(id, !schedule.getStatus());
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
