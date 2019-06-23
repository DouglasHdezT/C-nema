package com.kdc.cnema.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kdc.cnema.domain.Cinema;
import com.kdc.cnema.domain.Movie;
import com.kdc.cnema.domain.Schedule;
import com.kdc.cnema.dtos.ResponseDTO;
import com.kdc.cnema.service.CinemaService;
import com.kdc.cnema.service.MovieService;
import com.kdc.cnema.service.ScheduleService;

public class ScheduleController {

	@Autowired
	ScheduleService scheduleService; 
	
	@Autowired
	CinemaService cinemaService;
	
	@Autowired
	MovieService movieService;
	
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
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			message = "Error interno de servidor";
		}
		
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(message), code);
	}
	
	
}
