package com.kdc.cnema.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdc.cnema.domain.audit.ProfileAudit;
import com.kdc.cnema.repositories.ProfileAuditRepository;
import com.kdc.cnema.service.ProfileAuditService;

@Service
public class ProfileAuditServiceImpl implements ProfileAuditService{
	
	@Autowired
	ProfileAuditRepository pARepo;

	@Override
	public ProfileAudit findOneById(Integer id) throws DataAccessException {
		return pARepo.findById(id).get();
	}
	
	@Override
	public ProfileAudit findOneByUserId(Integer id) throws DataAccessException {
		return pARepo.findOneByUserId(id);
	}

	@Override
	public List<ProfileAudit> findAll() throws DataAccessException {
		return pARepo.findAll();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ProfileAudit save(ProfileAudit profileAudit) throws DataAccessException {
		return pARepo.save(profileAudit);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) throws DataAccessException {
		pARepo.deleteById(id);
	}

}
