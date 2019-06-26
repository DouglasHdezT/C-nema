package com.kdc.cnema.service.implementation;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdc.cnema.domain.Category;
import com.kdc.cnema.domain.audit.CategoryAudit;
import com.kdc.cnema.repositories.CategoryRepository;
import com.kdc.cnema.service.CategoryAuditService;
import com.kdc.cnema.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private CategoryAuditService auditService;
	
	@Override
	public Category findOneById(Integer id) {
		return categoryRepo.findById(id).get();
	}

	@Override
	public List<Category> findAll() throws DataAccessException {
		return categoryRepo.findAll();
	}
	
	@Override
	public List<Category> findAllActive() throws DataAccessException {
		return categoryRepo.findByStatus(true);
	}
	
	@Override
	public Category findOneByName(String name) throws DataAccessException {
		return categoryRepo.findOneByName(name);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Category save(Category category, String username) throws DataAccessException {
		if(category.getId() == null) {
			auditService.save(generateAudit(username, category.getName(), 1));
		}else {
			auditService.save(generateAudit(username, category.getName(), 2));
		}
		
		return categoryRepo.save(category);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) throws DataAccessException {
		categoryRepo.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateState(Integer id, Boolean state, String username) throws DataAccessException {
		auditService.save(generateAudit(username, categoryRepo.findOneById(id).getName(), 3));
		
		categoryRepo.updateState(id, state);
	}
	
	private CategoryAudit generateAudit(String username, String fieldname, int type) {
		CategoryAudit audit = new CategoryAudit();
		
		switch (type) {
		case 1:
			audit.setModifiedField("Se creo el campo: "+ fieldname);
			break;
		case 2:
			audit.setModifiedField("Se actualizo el campo: "+ fieldname);
			break;
		case 3:
			audit.setModifiedField("Cambio de estado en: "+ fieldname);
			break;

		default:
			audit.setModifiedField("Modificacion sin categorizacion: "+ fieldname);
			break;
		}
		
		audit.setModificationDate(new Timestamp(new Date().getTime()));
		audit.setUserModifier(username);
		
		return audit;
	}

}
