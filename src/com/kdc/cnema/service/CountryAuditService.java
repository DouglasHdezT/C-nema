package com.kdc.cnema.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.kdc.cnema.domain.audit.CountryAudit;


public interface CountryAuditService {
	
	public CountryAudit findOneById(Integer id) throws DataAccessException;
	
	List<CountryAudit> findAll() throws DataAccessException;
	
	CountryAudit save(CountryAudit countryAudit) throws DataAccessException;
	
	void deleteById(Integer id) throws DataAccessException;

}
