package belgium.campus.student.course.registration.service.repositories;

import belgium.campus.student.course.registration.service.entities.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstructorRepository  extends JpaRepository<Instructor, Long>
{
    Optional<Instructor> findByStaffNumber(String staffNumber);

    Optional<Instructor> findByEmail(String email);

}
