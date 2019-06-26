package com.kdc.cnema.service.implementation;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdc.cnema.domain.Schedule;
import com.kdc.cnema.domain.audit.ScheduleAudit;
import com.kdc.cnema.repositories.ScheduleRepository;
import com.kdc.cnema.service.ScheduleAuditService;
import com.kdc.cnema.service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService{
	
	@Autowired
	ScheduleRepository sRepo;
	
	@Autowired
	private ScheduleAuditService auditService;

	@Override
	public Schedule findOneById(Integer id) throws DataAccessException {
		return sRepo.findById(id).get();
	}

	@Override
	public List<Schedule> findAll() throws DataAccessException {
		return sRepo.findAll();
	}
	
	@Override
	public List<Schedule> findAllActive() throws DataAccessException {
		return sRepo.findByStatus(true);
	}
	
	@Override
	public List<Schedule> findAllPerMovie(Integer id) throws DataAccessException {
		return sRepo.findAllPerMovie(id); 
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Schedule save(Schedule schedule, String username) throws DataAccessException {
		if(schedule.getId() == null) {
			auditService.save(generateAudit(username, 
					"Horario sala "+schedule.getCinema().getRoomNumber()+" para "+schedule.getMovie().getTitle()
					, 1));
		}else {
			auditService.save(generateAudit(username, 
					"Horario sala "+schedule.getCinema().getRoomNumber()+" para "+schedule.getMovie().getTitle(), 
					2));
		}
		
		return sRepo.save(schedule);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Schedule reserve(Schedule schedule) throws DataAccessException {
			
		return sRepo.save(schedule);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) throws DataAccessException {
		sRepo.deleteById(id);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateState(Integer id, Boolean state, String username) throws DataAccessException {
		
		Schedule schedule = sRepo.findById(id).get();
		
		auditService.save(generateAudit(username, 
				"Horario sala "+schedule.getCinema().getRoomNumber()+" para "+schedule.getMovie().getTitle(), 
				3));
		
		sRepo.updateState(id, state);
	}

	private ScheduleAudit generateAudit(String username, String fieldname, int type) {
		ScheduleAudit audit = new ScheduleAudit();
		
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
