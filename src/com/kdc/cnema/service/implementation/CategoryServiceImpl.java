package com.kdc.cnema.service.implementation;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.kdc.cnema.domain.Category;
import com.kdc.cnema.repositories.CategoryRepository;
import com.kdc.cnema.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Override
	public Category findOneById(Integer id) {
		return categoryRepo.findOneById(id);
	}
	
	

	@Override
	public List<Category> findAll() throws DataAccessException {
		return categoryRepo.findAll();
	}

	@Override
	public Category save(Category category) throws DataAccessException {
		return categoryRepo.save(category);
	}

	@Override
	public void deleteById(Integer id) throws DataAccessException {
		categoryRepo.deleteById(id);
		
	}

}
