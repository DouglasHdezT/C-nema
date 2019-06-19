package com.kdc.cnema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdc.cnema.domain.audit.ProfileAudit;

public interface ProfileAuditRepository extends JpaRepository<ProfileAudit, Integer>{

}
