package belgium.campus.security.event.service.controller;

import belgium.campus.security.event.service.dto.SecurityEventCreateRequest;
import belgium.campus.security.event.service.dto.SecurityEventResponse;
import belgium.campus.security.event.service.service.SecurityEventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")

public class SecurityEventController
{

private final SecurityEventService service;


    public SecurityEventController(SecurityEventService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
   public SecurityEventResponse create( @Valid @RequestBody SecurityEventCreateRequest request)
   {
       return service.create(request);
   }

    @GetMapping("/{id}")
    public SecurityEventResponse getById(@PathVariable  Long id)
    {
        return service.getById(id);
    }

    @GetMapping
    public List<SecurityEventResponse>  getAll()
    {

        return service.getAll();

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id)
    {

        service.deleteById(id);
    }


    @GetMapping("/source/{sourceSystem}")
    public List<SecurityEventResponse>  findAllBySourceSystem(@PathVariable   String sourceSystem)
    {

        return service.getBySourceSystem(sourceSystem);

    }




}
