package com.kdc.cnema.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdc.cnema.domain.audit.MovieAudit;
import com.kdc.cnema.repositories.MovieAuditRepository;
import com.kdc.cnema.service.MovieAuditService;

@Service
public class MovieAuditServiceImpl implements MovieAuditService{

	@Autowired
	private MovieAuditRepository movieAuditRepo;
	
	@Override
	public MovieAudit findOneById(Integer id) throws DataAccessException {
		return movieAuditRepo.findById(id).get();
	}

	@Override
	public List<MovieAudit> findAll() throws DataAccessException {
		return movieAuditRepo.findAll();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public MovieAudit save(MovieAudit movieAudit) throws DataAccessException {
		return movieAuditRepo.save(movieAudit);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) throws DataAccessException {
		movieAuditRepo.deleteById(id);
	}

}
