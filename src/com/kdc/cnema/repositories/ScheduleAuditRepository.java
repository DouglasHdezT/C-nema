package com.kdc.cnema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdc.cnema.domain.audit.ScheduleAudit;

public interface ScheduleAuditRepository extends JpaRepository<ScheduleAudit, Integer>{
	
	
	
}
