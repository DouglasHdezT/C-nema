package com.kdc.cnema.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.kdc.cnema.domain.Town;


public interface TownService {
	Town findOneById(Integer id) throws DataAccessException;
	
	Town findOneByName(String name) throws DataAccessException;
	
	List<Town> findAll() throws DataAccessException;
	
	List<Town> findAllActive() throws DataAccessException;
	
	Town save(Town town, String username) throws DataAccessException;
	
	void deleteById(Integer id) throws DataAccessException;
	
	void updateState(Integer id, Boolean state, String username) throws DataAccessException;
}
