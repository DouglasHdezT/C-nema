package com.kdc.cnema.service;

import org.springframework.dao.DataAccessException;

import com.kdc.cnema.domain.Category;

public interface CategoryService {
	
	public Category findOneById() throws DataAccessException;
}
