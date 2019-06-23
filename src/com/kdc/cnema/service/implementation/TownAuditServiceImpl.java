package com.kdc.cnema.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdc.cnema.domain.audit.TownAudit;
import com.kdc.cnema.repositories.TownAuditRepository;
import com.kdc.cnema.service.TownAuditService;

@Service
public class TownAuditServiceImpl implements TownAuditService{
	
	@Autowired
	TownAuditRepository tARepo;

	@Override
	public TownAudit findOneById(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		return tARepo.findById(id).get();
	}

	@Override
	public List<TownAudit> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return tARepo.findAll();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public TownAudit save(TownAudit townAudit) throws DataAccessException {
		// TODO Auto-generated method stub
		return tARepo.save(townAudit);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		tARepo.deleteById(id);
	}

}
