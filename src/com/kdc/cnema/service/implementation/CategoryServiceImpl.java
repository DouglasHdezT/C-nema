package com.kdc.cnema.service.implementation;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdc.cnema.domain.Category;
import com.kdc.cnema.repositories.CategoryRepository;
import com.kdc.cnema.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Override
	public Category findOneById(Integer id) {
		return categoryRepo.findById(id).get();
	}

	@Override
	public List<Category> findAll() throws DataAccessException {
		return categoryRepo.findAll();
	}
	
	@Override
	public Category findOneByName(String name) throws DataAccessException {
		return categoryRepo.findOneByName(name);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Category save(Category category) throws DataAccessException {
		return categoryRepo.save(category);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) throws DataAccessException {
		categoryRepo.deleteById(id);
	}

	@Override
	public void updateState(Integer id, Boolean state) throws DataAccessException {
		categoryRepo.updateState(id, state);
	}

}
