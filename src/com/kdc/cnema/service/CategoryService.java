package com.kdc.cnema.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.kdc.cnema.domain.Category;

public interface CategoryService {
	Category findOneById(Integer id) throws DataAccessException;
	Category findOneByName(String name) throws DataAccessException;
	List<Category> findAll() throws DataAccessException;
	Category save(Category category) throws DataAccessException;
	void deleteById(Integer id) throws DataAccessException;
}
