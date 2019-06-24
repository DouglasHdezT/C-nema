package com.kdc.cnema.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kdc.cnema.domain.Reservation;
import com.kdc.cnema.domain.Schedule;
import com.kdc.cnema.domain.User;
import com.kdc.cnema.exceptions.MalformedAuthHeader;
import com.kdc.cnema.service.ScheduleService;
import com.kdc.cnema.service.UserService;
import com.kdc.cnema.utils.JwtPayload;

@CrossOrigin(origins = "*")
@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ScheduleService scheduleService;
	
	/*
	 * Admin methods
	 */
	
	@RequestMapping("/users/all")
	public ResponseEntity<List<User>> getAllCategories(@RequestHeader("Authorization") String authHeader){
		List<User> users =  new ArrayList<>();	
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			JwtPayload.validateToken(authHeader);
			JwtPayload payload = JwtPayload.decodeToken(authHeader.substring(7));
			
			User user = userService.findOneById(Integer.parseInt(payload.getUid()));
			
			if(user.getType() == 0) {
				code = HttpStatus.FORBIDDEN;
			}else {
				users = userService.findAll();
				code = HttpStatus.OK;
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
		
		
		return new ResponseEntity<List<User>>(
				users,
				code);
	}
	
	
	/*
	 * Users methods
	 */
	
	@RequestMapping(value = "/users/reservations/save", method = RequestMethod.POST)
	public ResponseEntity<Reservation> insertReservation(@RequestBody @Valid Reservation reservation, 
			@RequestHeader("Authorization") String authHeader, BindingResult result){
		Reservation reservationFinal = new Reservation();
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			
			JwtPayload.validateToken(authHeader);
			JwtPayload payload = JwtPayload.decodeToken(authHeader.substring(7));
			
			User user = userService.findOneById(Integer.parseInt(payload.getUid()));
			Schedule schedule = scheduleService.findOneById(reservation.getSchedule().getId());

			if(result.hasErrors()) {
				code = HttpStatus.BAD_REQUEST;
			}else {
				
				reservation.setTotalPrice(new BigDecimal(
							reservation.getQuanReservations() *
							reservation.getUnitPrice().longValue()
						));
				
				if(user == null || schedule == null) {
					
					code = HttpStatus.NOT_FOUND;
				
				}else if(reservation.getTotalPrice().longValue() > user.getCurrCredit().longValue()){
					
					code = HttpStatus.CONFLICT;
					
				}else if (reservation.getQuanReservations() > schedule.getAvialable()) {
				
					code = HttpStatus.CONFLICT;
					
				}else {
					
					schedule.setAvialable(
								schedule.getAvialable() - 
								reservation.getQuanReservations()
							);
					
					schedule = scheduleService.save(schedule);
					
					reservation.setSchedule(schedule);
					reservation.setUser(user);
					
				}
				
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
		
		return new ResponseEntity<Reservation>(reservationFinal, code);
	}
	
	@RequestMapping(value = "/users/reservations", method = RequestMethod.POST)
	public ResponseEntity<List<Reservation>> getAllReservationPerUser(@RequestHeader("Authorization") String authHeader){
		List<Reservation> reservations = new ArrayList<>();
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			JwtPayload.validateToken(authHeader);
			JwtPayload payload = JwtPayload.decodeToken(authHeader.substring(7));
			
			User user = userService.findOneById(Integer.parseInt(payload.getUid()));
					
			if(user == null) {
				code = HttpStatus.NOT_FOUND;
			}else {
				reservations = user.getReservations();
				code = HttpStatus.OK;
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
		
		return new ResponseEntity<List<Reservation>>(reservations, code);
	}
	
}
