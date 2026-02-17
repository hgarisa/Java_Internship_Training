package com.hrudhay.kyc_api.audit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrudhay.kyc_api.audit.entity.CustomerChangeAudit;
import com.hrudhay.kyc_api.audit.entity.CustomerReadAudit;
import com.hrudhay.kyc_api.audit.repo.CustomerChangeAuditRepository;
import com.hrudhay.kyc_api.audit.repo.CustomerReadAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AuditService {

    @Autowired
    private CustomerChangeAuditRepository changeRepo;

    @Autowired
    private CustomerReadAuditRepository readRepo;

    @Autowired
    private ObjectMapper objectMapper;

    // Runs in AUDIT DB transaction (separate from primary DB)
    @Transactional(transactionManager = "auditTransactionManager", propagation = Propagation.REQUIRES_NEW)
    public void logChange(String actionType,
                          String customerIdNumber,
                          Object oldObj,
                          Object newObj,
                          String status,
                          String message) {

        CustomerChangeAudit audit = new CustomerChangeAudit();
        audit.setUuid(UUID.randomUUID().toString());
        audit.setActionType(actionType);               // CREATE / UPDATE / DELETE
        audit.setCustomerIdNumber(customerIdNumber);
        audit.setOldData(toJson(oldObj));
        audit.setNewData(toJson(newObj));
        audit.setStatus(status);                       // SUCCESS / FAILED
        audit.setMessage(message);

        // created_at handled by DB (DEFAULT NOW())
        changeRepo.save(audit);
    }

    @Transactional(transactionManager = "auditTransactionManager", propagation = Propagation.REQUIRES_NEW)
    public void logRead(String readType,
                        String customerIdNumber,
                        Integer resultCount,
                        String status,
                        String message) {

        CustomerReadAudit audit = new CustomerReadAudit();
        audit.setUuid(UUID.randomUUID().toString());
        audit.setReadType(readType);                   // GET_ALL / GET_BY_ID / GET_BY_EMAIL...
        audit.setCustomerIdNumber(customerIdNumber);
        audit.setResultCount(resultCount);
        audit.setStatus(status);                       // SUCCESS / FAILED
        audit.setMessage(message);

        // created_at handled by DB (DEFAULT NOW())
        readRepo.save(audit);
    }

    private String toJson(Object obj) {
        if (obj == null) return null;
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            return String.valueOf(obj);
        }
    }
}
