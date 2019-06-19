package com.kdc.cnema.service.implementation;


import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.kdc.cnema.domain.Category;
import com.kdc.cnema.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Override
	public Category findOneById() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public List<Category> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category save(Category category) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

}
