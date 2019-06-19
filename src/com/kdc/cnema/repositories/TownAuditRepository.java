package com.kdc.cnema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdc.cnema.domain.audit.TownAudit;

public interface TownAuditRepository extends JpaRepository<TownAudit, Integer>{

}
