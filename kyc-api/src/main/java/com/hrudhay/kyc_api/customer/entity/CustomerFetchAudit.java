package com.hrudhay.kyc_api.customer.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_fetch_audit")
public class CustomerFetchAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto generated ID in Postgres
    private Long id;

    @Column(name = "uuid", nullable = false, length = 36, unique = true)
    private String uuid;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    public CustomerFetchAudit(String uuid, String description) {
        this.uuid = uuid;
        this.description = description;
    }

    public Long getId() { return id; }

    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
