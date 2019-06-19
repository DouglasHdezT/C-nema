package com.kdc.cnema.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.kdc.cnema.domain.audit.TownAudit;


public interface TownAuditService {

	public TownAudit findOneById() throws DataAccessException;
	List<TownAudit> findAll() throws DataAccessException;
	TownAudit save(TownAudit townAudit) throws DataAccessException;
	void deleteById(Integer id) throws DataAccessException;
}
