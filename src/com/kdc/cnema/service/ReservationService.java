package com.kdc.cnema.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.kdc.cnema.domain.Reservation;


public interface ReservationService {
	Reservation findOneById(Integer id) throws DataAccessException;
	
	List<Reservation> findAll() throws DataAccessException;
	
	Reservation save(Reservation user) throws DataAccessException;
	
	void deleteById(Integer id) throws DataAccessException;
	
	void updateState(Integer id, Boolean state) throws DataAccessException;
}
