package com.kdc.cnema.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.kdc.cnema.domain.audit.CategoryAudit;
import com.kdc.cnema.repositories.CategoryAuditRepository;
import com.kdc.cnema.service.CategoryAuditService;

@Service
public class CategoryAuditServiceImpl implements CategoryAuditService{
	
	@Autowired
	private CategoryAuditRepository categoryAuditRepo;
	
	@Override
	public CategoryAudit findOneById(Integer id) throws DataAccessException {
		return categoryAuditRepo.findById(id).get();
	}

	@Override
	public List<CategoryAudit> findAll() throws DataAccessException {
		return categoryAuditRepo.findAll();
	}

	@Override
	public CategoryAudit save(CategoryAudit categoryAudit) throws DataAccessException {
		return categoryAuditRepo.save(categoryAudit);
	}

	@Override
	public void deleteById(Integer id) throws DataAccessException {
		categoryAuditRepo.deleteById(id);
		
	}

}
