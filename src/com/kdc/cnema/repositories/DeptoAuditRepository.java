package com.kdc.cnema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdc.cnema.domain.audit.DeptoAudit;

public interface DeptoAuditRepository extends JpaRepository<DeptoAudit, Integer>{

}
