package com.kdc.cnema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdc.cnema.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
