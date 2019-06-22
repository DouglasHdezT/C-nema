package com.kdc.cnema.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.kdc.cnema.domain.audit.MovieAudit;


public interface MovieAuditService {
	
	public MovieAudit findOneById(Integer id) throws DataAccessException;
	List<MovieAudit> findAll() throws DataAccessException;
	MovieAudit save(MovieAudit movieAudit) throws DataAccessException;
	void deleteById(Integer id) throws DataAccessException;

}
