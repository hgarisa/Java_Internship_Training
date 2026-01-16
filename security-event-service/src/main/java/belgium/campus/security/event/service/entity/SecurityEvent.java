package belgium.campus.security.event.service.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name = "security_event")
public class SecurityEvent
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // matches BIGSERIAL
    private Long id;


    @Column(name = "event_type", nullable = false, length = 100)
    private String eventType;


    @Column(name = "severity", nullable = false, length = 30)
    private String severity;

    @Column(name = "source_system", length = 100)
    private String sourceSystem;

    @Column(name = "description", length = 200)
    private String description;

    @CreationTimestamp
    @Column(name = "created_at",  nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // ---- getters/setters ----

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public String getSourceSystem() { return sourceSystem; }
    public void setSourceSystem(String sourceSystem) { this.sourceSystem = sourceSystem; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }




}
