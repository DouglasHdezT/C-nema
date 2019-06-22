package com.kdc.cnema.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.kdc.cnema.domain.audit.ProfileAudit;


public interface ProfileAuditService {
	
	public ProfileAudit findOneById(Integer id) throws DataAccessException;
	List<ProfileAudit> findAll() throws DataAccessException;
	ProfileAudit save(ProfileAudit profileAudit) throws DataAccessException;
	void deleteById(Integer id) throws DataAccessException;

}
