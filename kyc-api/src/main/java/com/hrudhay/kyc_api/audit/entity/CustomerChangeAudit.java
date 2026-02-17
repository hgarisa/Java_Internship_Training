package com.hrudhay.kyc_api.audit.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_change_audit")
public class CustomerChangeAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 36)
    private String uuid;

    @Column(name = "action_type", nullable = false, length = 10)
    private String actionType; // CREATE, UPDATE, DELETE

    // NEW: store business ID (idNumber)
    @Column(name = "customer_id_number", length = 50)
    private String customerIdNumber;

    // (Optional) keep old column in entity only if still in DB
    // @Column(name = "customer_id")
    // private Long customerId;

    @Column(name = "old_data", columnDefinition = "TEXT")
    private String oldData;

    @Column(name = "new_data", columnDefinition = "TEXT")
    private String newData;

    @Column(nullable = false, length = 10)
    private String status; // SUCCESS, FAILED

    @Column(columnDefinition = "TEXT")
    private String message;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public CustomerChangeAudit() {}

    public CustomerChangeAudit(String uuid, String actionType, String customerIdNumber,
                               String oldData, String newData, String status, String message) {
        this.uuid = uuid;
        this.actionType = actionType;
        this.customerIdNumber = customerIdNumber;
        this.oldData = oldData;
        this.newData = newData;
        this.status = status;
        this.message = message;
    }

    public Long getId() { return id; }

    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }

    public String getActionType() { return actionType; }
    public void setActionType(String actionType) { this.actionType = actionType; }

    public String getCustomerIdNumber() { return customerIdNumber; }
    public void setCustomerIdNumber(String customerIdNumber) { this.customerIdNumber = customerIdNumber; }

    public String getOldData() { return oldData; }
    public void setOldData(String oldData) { this.oldData = oldData; }

    public String getNewData() { return newData; }
    public void setNewData(String newData) { this.newData = newData; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
