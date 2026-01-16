package belgium.campus.student.course.registration.service.repositories;

import belgium.campus.student.course.registration.service.entities.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>
{

    boolean existsByStudent_IdAndOffering_Id(Long studentId, Long offeringId);

    long countByOffering_IdAndStatus(Long offeringId, String status);

    List<Enrollment> findByStudent_Id(Long studentId);

    List<Enrollment> findByOffering_Id(Long offeringId);
}
