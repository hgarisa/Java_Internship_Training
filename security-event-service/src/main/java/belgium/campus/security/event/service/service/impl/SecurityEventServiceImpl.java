package belgium.campus.security.event.service.service.impl;

import belgium.campus.security.event.service.dto.SecurityEventCreateRequest;
import belgium.campus.security.event.service.dto.SecurityEventResponse;
import belgium.campus.security.event.service.entity.SecurityEvent;
import  belgium.campus.security.event.service.repository.SecurityEventRepository;
import belgium.campus.security.event.service.service.SecurityEventService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SecurityEventServiceImpl implements SecurityEventService
{

    private final SecurityEventRepository repository;

    public SecurityEventServiceImpl(SecurityEventRepository repository) {
        this.repository = repository;
    }


    @Override
    public SecurityEventResponse create(SecurityEventCreateRequest request)
    {
        SecurityEvent event = new SecurityEvent();
        event.setEventType(request.getEventType());
        event.setSeverity(request.getSeverity());
        event.setSourceSystem(request.getSourceSystem());
        event.setDescription(request.getDescription());

        // createdAt will be set by DB default now()

        SecurityEvent saved = repository.save(event);

        return toResponse(saved);
    }

    private SecurityEventResponse toResponse(SecurityEvent e)
    {

        return new SecurityEventResponse(
                e.getId(),
               e.getEventType(),
                e.getSeverity(),
                e.getSourceSystem(),
                e.getDescription(),
                e.getCreatedAt()

        );
    }

    @Override
    public List<SecurityEventResponse> getAll()
    {

        return repository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public SecurityEventResponse getById(Long id)
    {

        SecurityEvent event = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SecurityEvent not found with id: " + id));

        return toResponse(event);


    }


    @Override
    public List<SecurityEventResponse> getBySourceSystem(String sourceSystem) {
        return repository.findBySourceSystem(sourceSystem)
                .stream()
                .map(this::toResponse)   // reuse your existing mapper method
                .toList();
    }



    @Override
    public void deleteById(Long id)
    {

        if (!repository.existsById(id))
        {
            throw new RuntimeException("Cannot Delete. SecurityEvent not found with id: " + id);


        }
        repository.deleteById(id);
    }
}
