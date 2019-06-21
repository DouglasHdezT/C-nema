package com.kdc.cnema.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.kdc.cnema.domain.User;

public interface UserService {
	User findOneById(Integer id) throws DataAccessException;
	User findOneByUsernameAndPassword(String username, String password) throws DataAccessException;
	List<User> findAll() throws DataAccessException;
	User save(User user) throws DataAccessException;
	void deleteById(Integer id) throws DataAccessException;
}
