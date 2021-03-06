package com.kdc.cnema.service.implementation;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdc.cnema.domain.User;
import com.kdc.cnema.domain.audit.ProfileAudit;
import com.kdc.cnema.repositories.UserRepository;
import com.kdc.cnema.service.ProfileAuditService;
import com.kdc.cnema.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository uRepo;
	
	@Autowired
	ProfileAuditService auditService;

	@Override
	public User findOneById(Integer id) throws DataAccessException {
		return uRepo.findById(id).get();
	}

	@Override
	public List<User> findAll() throws DataAccessException {
		return uRepo.findAll();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public User save(User user) throws DataAccessException {
		return uRepo.save(user);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) throws DataAccessException {
		uRepo.deleteById(id);
	}

	@Override
	public User findOneByUsernameAndPassword(String username, String password) throws DataAccessException {
		return uRepo.findOneByUsernameAndPassword(username, password);
	}

	@Override
	public User findOneByUsername(String username) throws DataAccessException {
		return uRepo.findOneByUsername(username);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateLoggingState(Integer id, Boolean state) throws DataAccessException {
		uRepo.updateLoginState(id, state);
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateStatus(Integer id, Boolean status, ProfileAudit audit) throws DataAccessException {
		
		audit.setUser(findOneById(id));
		
		auditService.save(audit);
		uRepo.updateState(id, status);
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public User loginSave(User user, ProfileAudit audit) throws DataAccessException {
		user = uRepo.save(user);
		
		audit.setUser(user);
		auditService.save(audit);
		
		return user;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateBalance(User user, BigDecimal toChange) throws DataAccessException {
		BigDecimal newValue = new BigDecimal(user.getCurrCredit().doubleValue() + toChange.doubleValue());
		
		user.setCurrCredit(newValue);
		
		uRepo.save(user);
	}

}
