package com.kdc.cnema.service.implementation;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdc.cnema.domain.Depto;
import com.kdc.cnema.domain.audit.DeptoAudit;
import com.kdc.cnema.repositories.DeptoRepository;
import com.kdc.cnema.service.DeptoAuditService;
import com.kdc.cnema.service.DeptoService;

@Service
public class DeptoServiceImpl implements DeptoService{
	
	@Autowired
	private DeptoRepository deptoRepo;
	
	@Autowired
	private DeptoAuditService auditService;
	
	
	@Override
	public Depto findOneById(Integer id) throws DataAccessException {
		return deptoRepo.findById(id).get();
	}

	@Override
	public List<Depto> findAll() throws DataAccessException {
		return deptoRepo.findAll();
	}
	
	@Override
	public List<Depto> findAllActive() throws DataAccessException {
		return deptoRepo.findByStatus(true);
	}
	
	@Override
	public Depto findOneByName(String name) throws DataAccessException {
		return deptoRepo.findOneByName(name);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Depto save(Depto depto, String username) throws DataAccessException {
		if(depto.getId() == null) {
			auditService.save(generateAudit(username, depto.getName(), 1));
		}else {
			auditService.save(generateAudit(username, depto.getName(), 2));
		}
		
		return deptoRepo.save(depto);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) throws DataAccessException {
		deptoRepo.deleteById(id);
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateState(Integer id, Boolean state, String username) throws DataAccessException {
		auditService.save(generateAudit(username, deptoRepo.findById(id).get().getName(), 3));
		
		deptoRepo.updateState(id, state);
	}
	
	private DeptoAudit generateAudit(String username, String fieldname, int type) {
		DeptoAudit audit = new DeptoAudit();
		
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
