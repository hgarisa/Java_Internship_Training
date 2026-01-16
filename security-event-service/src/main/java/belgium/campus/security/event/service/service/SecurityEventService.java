package belgium.campus.security.event.service.service;

import belgium.campus.security.event.service.entity.SecurityEvent;
import belgium.campus.security.event.service.dto.SecurityEventCreateRequest;
import belgium.campus.security.event.service.dto.SecurityEventResponse;
import java.util.List;

public interface SecurityEventService
{


    SecurityEventResponse create(SecurityEventCreateRequest request);

    List<SecurityEventResponse> getAll();

    SecurityEventResponse getById(Long id);

    void deleteById(Long id);

    // Get ALL events for a given sourceSystem (exact match)
    List<SecurityEventResponse> getBySourceSystem(String sourceSystem);



}
