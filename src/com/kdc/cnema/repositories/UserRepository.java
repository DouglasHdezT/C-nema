package com.kdc.cnema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kdc.cnema.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public User findOneByUsernameAndPassword(String username, String password);
	
	public User findOneByUsername(String username);
	
	public User findOneById(Integer id);
	
	@Modifying
	@Query(value = "UPDATE usuario SET isLogged = :state WHERE id_usuario = :id"
			, nativeQuery = true)
	public void updateLoginState(@Param("id") Integer id, @Param("state") Boolean state);
}
