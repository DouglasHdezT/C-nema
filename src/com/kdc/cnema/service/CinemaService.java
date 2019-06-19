package com.kdc.cnema.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.kdc.cnema.domain.Cinema;

public interface CinemaService {
	
	public Cinema findOneById() throws DataAccessException;
	List<Cinema> findAll() throws DataAccessException;
	Cinema save(Cinema cinema) throws DataAccessException;
	void deleteById(Integer id) throws DataAccessException;

}
