package com.hrudhay.kyc_api.audit.repo;

import com.hrudhay.kyc_api.audit.entity.CustomerChangeAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface CustomerChangeAuditRepository extends JpaRepository<CustomerChangeAudit, Long>
{


    List<CustomerChangeAudit> findByCustomerIdNumberOrderByIdDesc(String customerIdNumber);



}
