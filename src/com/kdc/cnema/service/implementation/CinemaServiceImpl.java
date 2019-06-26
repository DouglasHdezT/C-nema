package com.kdc.cnema.service.implementation;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdc.cnema.domain.Cinema;
import com.kdc.cnema.domain.audit.CinemaAudit;
import com.kdc.cnema.repositories.CinemaRepository;
import com.kdc.cnema.service.CinemaAuditService;
import com.kdc.cnema.service.CinemaService;

@Service
public class CinemaServiceImpl implements CinemaService{
	
	@Autowired
	private CinemaRepository cinemaRepo;
	
	@Autowired
	private CinemaAuditService auditService;
	
	
	@Override
	public Cinema findOneById(Integer id) throws DataAccessException {
		return cinemaRepo.findById(id).get();
	}

	@Override
	public List<Cinema> findAll() throws DataAccessException {
		return cinemaRepo.findAll();
	}
	
	@Override
	public List<Cinema> findAllActive() throws DataAccessException {
		return cinemaRepo.findByStatus(true);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Cinema save(Cinema cinema, String username) throws DataAccessException {
		if(cinema.getId() == null) {
			auditService.save(generateAudit(username, "Sala "+cinema.getRoomNumber(), 1));
		}else {
			auditService.save(generateAudit(username, "Sala "+cinema.getRoomNumber(), 2));
		}
		
		return cinemaRepo.save(cinema);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) throws DataAccessException {
		cinemaRepo.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateState(Integer id, Boolean state, String username) throws DataAccessException {
		auditService.save(generateAudit(username, "Sala "+ cinemaRepo.findById(id).get().getRoomNumber(), 3));
		cinemaRepo.updateState(id, state);
	}
	
	private CinemaAudit generateAudit(String username, String fieldname, int type) {
		CinemaAudit audit = new CinemaAudit();
		
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
