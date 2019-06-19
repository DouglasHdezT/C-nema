package com.kdc.cnema.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.kdc.cnema.domain.Town;


public interface TownService {
	Town findOneById(Integer id) throws DataAccessException;
	List<Town> findAll() throws DataAccessException;
	Town save(Town user) throws DataAccessException;
	void deleteById(Integer id) throws DataAccessException;
}
