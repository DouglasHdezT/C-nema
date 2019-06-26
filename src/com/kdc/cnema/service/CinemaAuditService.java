package com.kdc.cnema.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.kdc.cnema.domain.audit.CinemaAudit;


public interface CinemaAuditService {

	public CinemaAudit findOneById(Integer id) throws DataAccessException;
	
	List<CinemaAudit> findAll() throws DataAccessException;
	
	CinemaAudit save(CinemaAudit cinemaAudit) throws DataAccessException;
	
	void deleteById(Integer id) throws DataAccessException;
}
