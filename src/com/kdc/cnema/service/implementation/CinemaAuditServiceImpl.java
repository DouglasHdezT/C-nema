package com.kdc.cnema.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdc.cnema.domain.audit.CinemaAudit;
import com.kdc.cnema.repositories.CinemaAuditRepository;
import com.kdc.cnema.service.CinemaAuditService;

@Service
public class CinemaAuditServiceImpl implements CinemaAuditService{
	
	@Autowired
	private CinemaAuditRepository cinemaAuditRepo;
	
	@Override
	public CinemaAudit findOneById(Integer id) throws DataAccessException {
		return cinemaAuditRepo.findById(id).get();
	}

	@Override
	public List<CinemaAudit> findAll() throws DataAccessException {
		return cinemaAuditRepo.findAll();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public CinemaAudit save(CinemaAudit cinemaAudit) throws DataAccessException {
		return cinemaAuditRepo.save(cinemaAudit);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) throws DataAccessException {
		cinemaAuditRepo.deleteById(id);
	}

}
