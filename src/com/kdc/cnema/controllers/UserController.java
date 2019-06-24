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
import com.kdc.cnema.dtos.ArgumentDTO;
import com.kdc.cnema.dtos.ResponseDTO;
import com.kdc.cnema.exceptions.MalformedAuthHeader;
import com.kdc.cnema.service.ReservationService;
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
	
	@Autowired
	ReservationService reservationService;
	
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
	
	@RequestMapping(value ="/users/update/")
	public ResponseEntity<ResponseDTO> updateState(@RequestBody ArgumentDTO argumentBody, @RequestHeader("Authorization") String authHeader){
		String message = "Default message";
		HttpStatus code = HttpStatus.BAD_REQUEST;
		
		try {
			
			JwtPayload.validateToken(authHeader);
			JwtPayload payload = JwtPayload.decodeToken(authHeader.substring(7));
			
			User user = userService.findOneById(Integer.parseInt(payload.getUid()));
			User userToUpdate = userService.findOneById(argumentBody.getId());
			
			if(user != null && user.getType() == 0) {
				
				code = HttpStatus.FORBIDDEN;
				message = "Faltan permisos";
			
			}else if(userToUpdate == null) {
				
				code = HttpStatus.NOT_FOUND;
				message = "Usuario no encontrado";
			
			}else{
				
				userService.updateStatus(userToUpdate.getId(), !userToUpdate.getStatus());;
				
				code = HttpStatus.OK;
				message = "Usuario modificado";
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
	
	
	/*
	 * Users methods
	 */
	
	@RequestMapping(value = "/users/reservations/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> insertReservation(@RequestBody @Valid Reservation reservation, 
			@RequestHeader("Authorization") String authHeader, BindingResult result){

		HttpStatus code = HttpStatus.BAD_REQUEST;
		String message = "Default message";
		
		try {
			
			JwtPayload.validateToken(authHeader);
			JwtPayload payload = JwtPayload.decodeToken(authHeader.substring(7));
			
			User user = userService.findOneById(Integer.parseInt(payload.getUid()));
			Schedule schedule = scheduleService.findOneById(reservation.getSchedule().getId());

			if(result.hasErrors()) {
				code = HttpStatus.BAD_REQUEST;
			}else {
				
				if(user == null || schedule == null) {
					
					code = HttpStatus.NOT_FOUND;
				
				}else {
					
					reservation.setTotalPrice(new BigDecimal(
							(reservation.getQuanNormal() * schedule.getNormalPrice().longValue()) +
							(reservation.getQuanPremium() * schedule.getPremiumPrice().longValue()) 
						));
					
					reservation.setQuanReservations(
							reservation.getQuanNormal() + reservation.getQuanPremium()
						);
					
					if(reservation.getUsedBalance().longValue() > user.getCurrCredit().longValue()){
						
						code = HttpStatus.CONFLICT;
						
					}else if (reservation.getQuanReservations() > schedule.getAvialable()) {
					
						code = HttpStatus.CONFLICT;
						
					}else {
						
						schedule.setAvialable(
									schedule.getAvialable() - 
									reservation.getQuanReservations()
								);
						
						user.setCurrCredit(new BigDecimal(
									user.getCurrCredit().longValue() - 
									reservation.getUsedBalance().longValue()
								));;
						
						reservation.setRemainBalance(user.getCurrCredit());
								
						schedule = scheduleService.save(schedule);
						user =  userService.save(user);
						
						reservation.setSchedule(schedule);
						reservation.setUser(user);
						
						reservation = reservationService.save(reservation);
					}
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
		
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(message), code);
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
