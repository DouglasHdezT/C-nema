package com.kdc.cnema.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.kdc.cnema.domain.Country;


public interface CountryService {
	
	public Country findOneById(Integer id) throws DataAccessException;
	
	Country findOneByName(String name) throws DataAccessException;
	
	List<Country> findAll() throws DataAccessException;
	
	List<Country> findAllActive() throws DataAccessException;
	
	Country save(Country country, String username) throws DataAccessException;
	
	void deleteById(Integer id) throws DataAccessException;

	void updateState(Integer id, Boolean state, String username) throws DataAccessException;
}
