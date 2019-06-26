package com.kdc.cnema.service.implementation;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdc.cnema.domain.Country;
import com.kdc.cnema.domain.audit.CountryAudit;
import com.kdc.cnema.repositories.CountryRepository;
import com.kdc.cnema.service.CountryAuditService;
import com.kdc.cnema.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService{

	@Autowired
	CountryRepository repository;
	
	@Autowired
	private CountryAuditService auditService;
	
	
	@Override
	public Country findOneById(Integer id) throws DataAccessException {
		return repository.findById(id).get();
	}

	@Override
	public List<Country> findAll() throws DataAccessException {
		return repository.findAll();
	}
	
	@Override
	public List<Country> findAllActive() throws DataAccessException {
		return repository.findByStatus(true);
	}
	
	@Override
	public Country findOneByName(String name) throws DataAccessException {
		return repository.findOneByName(name);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Country save(Country country, String username) throws DataAccessException {
		if(country.getId() == null) {
			auditService.save(generateAudit(username, country.getName(), 1));
		}else {
			auditService.save(generateAudit(username, country.getName(), 2));
		}
		
		return repository.save(country);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) throws DataAccessException {
		repository.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateState(Integer id, Boolean state, String username) throws DataAccessException {
		auditService.save(generateAudit(username, repository.findOneById(id).getName(), 3));
		
		repository.updateState(id, state);
	}
	
	private CountryAudit generateAudit(String username, String fieldname, int type) {
		CountryAudit audit = new CountryAudit();
		
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
