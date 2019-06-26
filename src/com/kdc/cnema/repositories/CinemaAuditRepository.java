package com.kdc.cnema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdc.cnema.domain.audit.CinemaAudit;

public interface CinemaAuditRepository extends JpaRepository<CinemaAudit, Integer>{
	
	
	
}
