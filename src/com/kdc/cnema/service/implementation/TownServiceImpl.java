package com.kdc.cnema.service.implementation;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdc.cnema.domain.Town;
import com.kdc.cnema.domain.audit.TownAudit;
import com.kdc.cnema.repositories.TownRepository;
import com.kdc.cnema.service.TownAuditService;
import com.kdc.cnema.service.TownService;

@Service
public class TownServiceImpl implements TownService{
	
	@Autowired
	TownRepository tRepo;
	
	@Autowired
	private TownAuditService auditService;
	
	@Override
	public Town findOneById(Integer id) throws DataAccessException {
		return tRepo.findById(id).get();
	}

	@Override
	public List<Town> findAll() throws DataAccessException {
		return tRepo.findAll();
	}
	
	@Override
	public List<Town> findAllActive() throws DataAccessException {
		return tRepo.findByStatus(true);
	}
	
	@Override
	public Town findOneByName(String name) throws DataAccessException {
		return tRepo.findOneByName(name);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Town save(Town town, String username) throws DataAccessException {
		if(town.getId() == null) {
			auditService.save(generateAudit(username, town.getName(), 1));
		}else {
			auditService.save(generateAudit(username, town.getName(), 2));
		}
		
		return tRepo.save(town);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) throws DataAccessException {
		tRepo.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateState(Integer id, Boolean state, String username) throws DataAccessException {
		auditService.save(generateAudit(username, tRepo.findById(id).get().getName(), 1));
		
		tRepo.updateState(id, state);
	}
	
	private TownAudit generateAudit(String username, String fieldname, int type) {
		TownAudit audit = new TownAudit();
		
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
