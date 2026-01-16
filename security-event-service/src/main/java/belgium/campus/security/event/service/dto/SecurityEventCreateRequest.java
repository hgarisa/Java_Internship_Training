package belgium.campus.security.event.service.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
public class SecurityEventCreateRequest
{

    @NotBlank
    @Size(max = 100)
    private String eventType;


    @NotBlank
    @Size(max = 30)
    private String severity;

    @Size(max = 100)
    private String sourceSystem;


    @Size(max = 200)
    private String description;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }


    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
