package com.kdc.cnema.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.kdc.cnema.domain.audit.DeptoAudit;
import com.kdc.cnema.repositories.DeptoAuditRepository;
import com.kdc.cnema.service.DeptoAuditService;

@Service
public class DeptoAuditServiceImpl implements DeptoAuditService{
	
	@Autowired
	private DeptoAuditRepository deptoAuditRepo;
	
	@Override
	public DeptoAudit findOneById(Integer id) throws DataAccessException {
		return deptoAuditRepo.findById(id).get();
	}

	@Override
	public List<DeptoAudit> findAll() throws DataAccessException {
		return deptoAuditRepo.findAll();
	}

	@Override
	public DeptoAudit save(DeptoAudit deptoAudit) throws DataAccessException {
		return deptoAuditRepo.save(deptoAudit);
	}

	@Override
	public void deleteById(Integer id) throws DataAccessException {
		deptoAuditRepo.deleteById(id);
		
	}

}
