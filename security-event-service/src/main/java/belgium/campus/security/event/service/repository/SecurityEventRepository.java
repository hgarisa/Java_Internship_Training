package belgium.campus.security.event.service.repository;


import belgium.campus.security.event.service.entity.SecurityEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SecurityEventRepository extends JpaRepository<SecurityEvent, Long>
{

    // Get ALL events for a given sourceSystem (exact match)
    List<SecurityEvent> findBySourceSystem(String sourceSystem);

    // later we can add custom queries here if needed


}
