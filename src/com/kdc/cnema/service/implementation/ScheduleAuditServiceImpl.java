package com.kdc.cnema.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdc.cnema.domain.audit.ScheduleAudit;
import com.kdc.cnema.repositories.ScheduleAuditRepository;
import com.kdc.cnema.service.ScheduleAuditService;

@Service
public class ScheduleAuditServiceImpl implements ScheduleAuditService{
	
	@Autowired
	private ScheduleAuditRepository scheduleAuditRepo;
	
	@Override
	public ScheduleAudit findOneById(Integer id) throws DataAccessException {
		return scheduleAuditRepo.findById(id).get();
	}

	@Override
	public List<ScheduleAudit> findAll() throws DataAccessException {
		return scheduleAuditRepo.findAll();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ScheduleAudit save(ScheduleAudit scheduleAudit) throws DataAccessException {
		return scheduleAuditRepo.save(scheduleAudit);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) throws DataAccessException {
		scheduleAuditRepo.deleteById(id);
	}

}
