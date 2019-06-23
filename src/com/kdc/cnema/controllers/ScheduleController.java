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

import com.kdc.cnema.domain.Cinema;
import com.kdc.cnema.domain.Movie;
import com.kdc.cnema.domain.Schedule;
import com.kdc.cnema.dtos.ResponseDTO;
import com.kdc.cnema.service.CinemaService;
import com.kdc.cnema.service.MovieService;
import com.kdc.cnema.service.ScheduleService;

@RestController
@CrossOrigin(origins = "*")
public class ScheduleController {

	@Autowired
	ScheduleService scheduleService; 
	
	@Autowired
	CinemaService cinemaService;
	
	@Autowired
	MovieService movieService;
	
	
	@RequestMapping(value = "/schedule/all")
	public ResponseEntity<List<Schedule>> getAllSchedules(){
		List<Schedule> schedules = new ArrayList<>();
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			schedules = scheduleService.findAll();
			code=HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
			code=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<List<Schedule>>(schedules, code);
	}
	
	@RequestMapping(value = "/schedule/{id}")
	public ResponseEntity<Schedule> getSchedule(@PathVariable("id") Integer id){
		Schedule schedule =  new Schedule();
		HttpStatus code = HttpStatus.BAD_GATEWAY;
		
		try {
			schedule = scheduleService.findOneById(id);
			
			if(schedule == null) {
				schedule = new Schedule();
				code = HttpStatus.NOT_FOUND;
			}else {
				code = HttpStatus.OK;
			}
		} catch (Exception e) {
			code = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		
		return new ResponseEntity<Schedule>(schedule, code);
	}
	
	@RequestMapping(value = "/schedule/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> insertSchedule(@RequestBody @Valid Schedule schedule, BindingResult result){
		String message = "Default message";
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			
			if(result.hasErrors()) {
				message = "Campos de horario invalidos";
				code = HttpStatus.BAD_REQUEST;
			}else {
				Cinema cinema = cinemaService.findOneById(schedule.getId());
				Movie movie = movieService.findOneById(schedule.getId());
				
				if(cinema == null) {
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
			
		}catch (Exception e) {
			e.printStackTrace();
			message = "Error interno de servidor";
		}
		
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(message), code);
	}
	
	
}
