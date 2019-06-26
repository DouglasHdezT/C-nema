package com.kdc.cnema.service.implementation;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdc.cnema.domain.Movie;
import com.kdc.cnema.domain.audit.MovieAudit;
import com.kdc.cnema.repositories.MovieRepository;
import com.kdc.cnema.service.MovieAuditService;
import com.kdc.cnema.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	MovieRepository mRepo;
	
	@Autowired
	private MovieAuditService auditService;
	

	@Override
	public Movie findOneById(Integer id) throws DataAccessException {
		return mRepo.findById(id).get();
	}

	@Override
	public List<Movie> findAll(){
		return mRepo.findAll();
	}
	
	@Override
	public List<Movie> findAllActive(){
		return mRepo.findByStatus(true);
	}
	
	@Override
	public Movie findOneByTitle(String title) throws DataAccessException {
		return mRepo.findOneByTitle(title);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Movie save(Movie movie, String username) throws DataAccessException {
		if(movie.getId() == null) {
			auditService.save(generateAudit(username, movie.getTitle(), 1));
		}else {
			auditService.save(generateAudit(username, movie.getTitle(), 2));
		}
		
		return mRepo.save(movie);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) throws DataAccessException {
		mRepo.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateState(Integer id, Boolean state, String username) throws DataAccessException {
		auditService.save(generateAudit(username, mRepo.findById(id).get().getTitle(), 3));
		
		mRepo.updateState(id, state);
	}
	
	private MovieAudit generateAudit(String username, String fieldname, int type) {
		MovieAudit audit = new MovieAudit();
		
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
