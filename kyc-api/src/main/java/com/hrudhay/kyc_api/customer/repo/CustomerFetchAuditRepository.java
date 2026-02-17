package com.hrudhay.kyc_api.customer.repo;

import com.hrudhay.kyc_api.customer.entity.CustomerFetchAudit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerFetchAuditRepository extends JpaRepository<CustomerFetchAudit, UUID>
{




}
