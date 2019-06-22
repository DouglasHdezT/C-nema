package com.kdc.cnema.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdc.cnema.domain.User;
import com.kdc.cnema.repositories.UserRepository;
import com.kdc.cnema.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository uRepo;

	@Override
	public User findOneById(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		return uRepo.findById(id).get();
	}

	@Override
	public List<User> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return uRepo.findAll();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public User save(User user) throws DataAccessException {
		// TODO Auto-generated method stub
		return uRepo.save(user);
	}
	@Override
	public void deleteById(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
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

}
