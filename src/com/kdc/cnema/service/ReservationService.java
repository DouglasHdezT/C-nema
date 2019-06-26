package com.kdc.cnema.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.kdc.cnema.domain.Reservation;
import com.kdc.cnema.domain.Schedule;
import com.kdc.cnema.domain.User;


public interface ReservationService {
	Reservation findOneById(Integer id) throws DataAccessException;
	
	List<Reservation> findAll() throws DataAccessException;
	
	List<Reservation> findAllPerUser(Integer id) throws DataAccessException;
	
	Reservation save(Reservation reservation, Schedule schedule, User user) throws DataAccessException;
	
	void deleteById(Integer id) throws DataAccessException;
}
