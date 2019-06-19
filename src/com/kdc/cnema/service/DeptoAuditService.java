package com.kdc.cnema.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.kdc.cnema.domain.audit.DeptoAudit;


public interface DeptoAuditService{

	public DeptoAudit findOneById() throws DataAccessException;
	List<DeptoAudit> findAll() throws DataAccessException;
	DeptoAudit save(DeptoAudit deptoAudit) throws DataAccessException;
	void deleteById(Integer id) throws DataAccessException;
}
