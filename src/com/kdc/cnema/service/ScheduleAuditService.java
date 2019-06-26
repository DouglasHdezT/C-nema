package com.kdc.cnema.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.kdc.cnema.domain.audit.ScheduleAudit;


public interface ScheduleAuditService {

	public ScheduleAudit findOneById(Integer id) throws DataAccessException;
	
	List<ScheduleAudit> findAll() throws DataAccessException;
	
	ScheduleAudit save(ScheduleAudit scheduleAudit) throws DataAccessException;
	
	void deleteById(Integer id) throws DataAccessException;
}
