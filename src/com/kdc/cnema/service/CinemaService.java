package com.kdc.cnema.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.kdc.cnema.domain.Cinema;

public interface CinemaService {
	
	public Cinema findOneById(Integer id) throws DataAccessException;
	
	List<Cinema> findAll() throws DataAccessException;
	
	List<Cinema> findAllActive() throws DataAccessException;
	
	Cinema save(Cinema cinema, String username) throws DataAccessException;
	
	void deleteById(Integer id) throws DataAccessException;

	void updateState(Integer id, Boolean state, String username) throws DataAccessException;
}
