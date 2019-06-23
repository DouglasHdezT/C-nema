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

import com.kdc.cnema.domain.Reservation;
import com.kdc.cnema.domain.Schedule;
import com.kdc.cnema.domain.User;
import com.kdc.cnema.dtos.ResponseDTO;
import com.kdc.cnema.service.ReservationService;
import com.kdc.cnema.service.ScheduleService;
import com.kdc.cnema.service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class ReservationController {
	
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ScheduleService scheduleService;
	
	@RequestMapping("/reservations/all")
	public ResponseEntity<List<Reservation>> getAllReservations(){
		List<Reservation> reservations =  new ArrayList<>();	
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			reservations = reservationService.findAll();
			code = HttpStatus.OK;
		}catch (Exception e) {
			e.printStackTrace();
			code = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		
		return new ResponseEntity<List<Reservation>>(
				reservations,
				code);
	}

	@RequestMapping(value="/reservations/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> insertCountry(@RequestBody @Valid Reservation reservation, BindingResult result){
		
		String message = "Default message";
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			if(result.hasErrors()) {
				message = "Reservaciones invalidas";
				code = HttpStatus.BAD_REQUEST;
			}else {
				
				User user = userService.findOneById(reservation.getUser().getId());
				Schedule schedule = scheduleService.findOneById(reservation.getSchedule().getId());
				
				if(user == null) {
					message = "Usuario no encontrado";
					code = HttpStatus.NOT_FOUND;
				}else if (schedule == null) {
					message = "Horario no encontrado";
					code = HttpStatus.NOT_FOUND;
				}else {
					reservationService.save(reservation);
					message = "Reservacion insertada con �xito";
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