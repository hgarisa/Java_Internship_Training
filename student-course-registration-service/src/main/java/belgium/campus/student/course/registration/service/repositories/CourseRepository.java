package belgium.campus.student.course.registration.service.repositories;

import belgium.campus.student.course.registration.service.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository  extends JpaRepository<Course, Long>
{

    Optional<Course> findByCourseCode(String courseCode);


}
