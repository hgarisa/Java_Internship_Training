package com.hrudhay.kyc_api.audit.repo;

import com.hrudhay.kyc_api.audit.entity.CustomerReadAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CustomerReadAuditRepository extends JpaRepository<CustomerReadAudit, Long>
{

    List<CustomerReadAudit> findByCustomerIdNumberOrderByIdDesc(String customerIdNumber);

}
