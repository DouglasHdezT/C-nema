package com.kdc.cnema.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.kdc.cnema.domain.User;
import com.kdc.cnema.domain.audit.ProfileAudit;

public interface UserService {
	
	User findOneById(Integer id) throws DataAccessException;
	
	User findOneByUsernameAndPassword(String username, String password) throws DataAccessException;
	
	User findOneByUsername(String username) throws DataAccessException;
	
	List<User> findAll() throws DataAccessException;
	
	User save(User user) throws DataAccessException;
	
	User loginSave(User user, ProfileAudit audit) throws DataAccessException;
	
	void deleteById(Integer id) throws DataAccessException;
	
	void updateLoggingState(Integer id, Boolean state) throws DataAccessException;
	
	void updateStatus(Integer id, Boolean status, ProfileAudit audit) throws DataAccessException;
	
	void updateBalance(User user, BigDecimal toChange) throws DataAccessException;
	
}

