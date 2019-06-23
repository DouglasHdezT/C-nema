package com.kdc.cnema.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdc.cnema.domain.audit.CountryAudit;
import com.kdc.cnema.repositories.CountryAuditRepository;
import com.kdc.cnema.service.CountryAuditService;

@Service
public class CountryAuditServiceImpl implements CountryAuditService{
	
	@Autowired
	private CountryAuditRepository countryAuditRepo;
	
	@Override
	public CountryAudit findOneById(Integer id) throws DataAccessException {
		return countryAuditRepo.findById(id).get();
	}

	@Override
	public List<CountryAudit> findAll() throws DataAccessException {
		return countryAuditRepo.findAll();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public CountryAudit save(CountryAudit countryAudit) throws DataAccessException {
		return countryAuditRepo.save(countryAudit);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) throws DataAccessException {
		countryAuditRepo.deleteById(id);
		
	}

}
