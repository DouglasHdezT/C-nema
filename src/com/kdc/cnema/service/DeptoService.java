package com.kdc.cnema.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.kdc.cnema.domain.Depto;



public interface DeptoService {
	
	public Depto findOneById() throws DataAccessException;
	
	List<Depto> findAll() throws DataAccessException;
	
	Depto save(Depto depto) throws DataAccessException;
	
	void deleteById(Integer id) throws DataAccessException;

}
