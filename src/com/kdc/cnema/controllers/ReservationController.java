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

import com.kdc.cnema.domain.Reservation;
import com.kdc.cnema.domain.Schedule;
import com.kdc.cnema.domain.User;
import com.kdc.cnema.dtos.ResponseDTO;
import com.kdc.cnema.exceptions.MalformedAuthHeader;
import com.kdc.cnema.service.ReservationService;
import com.kdc.cnema.service.ScheduleService;
import com.kdc.cnema.service.UserService;
import com.kdc.cnema.utils.JwtPayload;

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
	public ResponseEntity<List<Reservation>> getAllReservations(@RequestHeader("Authorization") String authHeader){
		List<Reservation> reservations =  new ArrayList<>();	
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			JwtPayload.validateToken(authHeader);
			
			reservations = reservationService.findAll();
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
		
		
		return new ResponseEntity<List<Reservation>>(
				reservations,
				code);
	}
	
	@RequestMapping("/reservations/{id}")
	public ResponseEntity<Reservation> getReservation(@PathVariable() Integer id, @RequestHeader("Authorization") String authHeader){
		Reservation reservation = new Reservation();
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			JwtPayload.validateToken(authHeader);
			Reservation reservationAux = reservationService.findOneById(id);
			
			if(reservationAux == null) {
				code = HttpStatus.NOT_FOUND;
				reservation = new Reservation();
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
		
		return new ResponseEntity<Reservation>(reservation, code);
	}

	@RequestMapping(value="/reservations/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> insertCountry(@RequestBody @Valid Reservation reservation, @RequestHeader("Authorization") String authHeader
			, BindingResult result){
		
		String message = "Default message";
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			JwtPayload.validateToken(authHeader);
			
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
					message = "Reservacion insertada con exito";
					code = HttpStatus.OK;
				}
			}
		}catch (io.jsonwebtoken.SignatureException e) {
			code = HttpStatus.FORBIDDEN;
		}catch (io.jsonwebtoken.MalformedJwtException e) {
			code = HttpStatus.FORBIDDEN;
		}catch (MalformedAuthHeader e) {
			code = HttpStatus.FORBIDDEN;
		}catch (Exception e) {
			message = "Error interno de servidor";
			code = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(message), code);		
	}
}
