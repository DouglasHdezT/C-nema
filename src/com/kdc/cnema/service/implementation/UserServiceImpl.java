package com.kdc.cnema.service.implementation;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.kdc.cnema.domain.User;
import com.kdc.cnema.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Override
	public User findOneById(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User save(User user) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

}
