package belgium.campus.security.event.service.dto;

import java.time.LocalDateTime;

public class SecurityEventResponse {

    private Long id;
    private String eventType;
    private String severity;
    private String sourceSystem;
    private String description;
    private LocalDateTime createdAt;

    public SecurityEventResponse() {}

    public SecurityEventResponse(Long id, String eventType, String severity,
                                 String sourceSystem, String description, LocalDateTime createdAt) {
        this.id = id;
        this.eventType = eventType;
        this.severity = severity;
        this.sourceSystem = sourceSystem;
        this.description = description;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getEventType() {
        return eventType;
    }

    public String getSeverity() {
        return severity;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
