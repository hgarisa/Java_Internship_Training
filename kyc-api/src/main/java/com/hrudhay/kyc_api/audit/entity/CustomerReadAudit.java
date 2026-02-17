package com.hrudhay.kyc_api.audit.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_read_audit")
public class CustomerReadAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 36)
    private String uuid;

    @Column(name = "read_type", nullable = false, length = 20)
    private String readType; // GET_ALL, GET_BY_ID

    // NEW: store business ID (idNumber)
    @Column(name = "customer_id_number", length = 50)
    private String customerIdNumber;

    @Column(name = "result_count")
    private Integer resultCount;

    @Column(nullable = false, length = 10)
    private String status; // SUCCESS, FAILED

    @Column(columnDefinition = "TEXT")
    private String message;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public CustomerReadAudit() {}

    public CustomerReadAudit(String uuid, String readType, String customerIdNumber,
                             Integer resultCount, String status, String message) {
        this.uuid = uuid;
        this.readType = readType;
        this.customerIdNumber = customerIdNumber;
        this.resultCount = resultCount;
        this.status = status;
        this.message = message;
    }

    public Long getId() { return id; }

    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }

    public String getReadType() { return readType; }
    public void setReadType(String readType) { this.readType = readType; }

    public String getCustomerIdNumber() { return customerIdNumber; }
    public void setCustomerIdNumber(String customerIdNumber) { this.customerIdNumber = customerIdNumber; }

    public Integer getResultCount() { return resultCount; }
    public void setResultCount(Integer resultCount) { this.resultCount = resultCount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
