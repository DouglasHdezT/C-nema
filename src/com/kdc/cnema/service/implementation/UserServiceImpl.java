package com.kdc.cnema.service.implementation;

import java.util.List;

<<<<<<< HEAD
=======


>>>>>>> 2743c94d0296d1c60e014388741a794c7b2e6cfc
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
<<<<<<< HEAD
	UserRepository uRepo;

	@Override
	public User findOneById(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		return uRepo.findById(id).get();
=======
	UserRepository repository;

	@Override
	public User findOneById(Integer id) throws DataAccessException {
		return repository.findOneById(id);
>>>>>>> 2743c94d0296d1c60e014388741a794c7b2e6cfc
	}

	@Override
	public List<User> findAll() throws DataAccessException {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		return uRepo.findAll();
=======
		return repository.findAll();
>>>>>>> 2743c94d0296d1c60e014388741a794c7b2e6cfc
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public User save(User user) throws DataAccessException {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		return uRepo.save(user);
=======
		return repository.save(user);
>>>>>>> 2743c94d0296d1c60e014388741a794c7b2e6cfc
	}

	@Override
	public void deleteById(Integer id) throws DataAccessException {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		uRepo.deleteById(id);
=======
		repository.deleteById(id);
>>>>>>> 2743c94d0296d1c60e014388741a794c7b2e6cfc
		
	}

	@Override
	public User findOneByUsernameAndPassword(String username, String password) throws DataAccessException {
		return repository.findOneByUsernameAndPassword(username, password);
	}

	@Override
	public User findOneByUsername(String username) throws DataAccessException {
		return repository.findOneByUsername(username);
	}

}
