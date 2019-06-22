package com.kdc.cnema.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.kdc.cnema.domain.audit.ProfileAudit;
import com.kdc.cnema.repositories.ProfileAuditRepository;
import com.kdc.cnema.service.ProfileAuditService;

@Service
public class ProfileAuditServiceImpl implements ProfileAuditService{
	
	@Autowired
	ProfileAuditRepository pARepo;

	@Override
	public ProfileAudit findOneById(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		return pARepo.findById(id).get();
	}

	@Override
	public List<ProfileAudit> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return pARepo.findAll();
	}

	@Override
	public ProfileAudit save(ProfileAudit profileAudit) throws DataAccessException {
		// TODO Auto-generated method stub
		return pARepo.save(profileAudit);
	}

	@Override
	public void deleteById(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		pARepo.deleteById(id);
	}

}
