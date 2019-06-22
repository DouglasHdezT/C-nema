package com.kdc.cnema.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.kdc.cnema.domain.audit.CategoryAudit;


public interface CategoryAuditService {

	public CategoryAudit findOneById(Integer id) throws DataAccessException;
	List<CategoryAudit> findAll() throws DataAccessException;
	CategoryAudit save(CategoryAudit categoryAudit) throws DataAccessException;
	void deleteById(Integer id) throws DataAccessException;
}
