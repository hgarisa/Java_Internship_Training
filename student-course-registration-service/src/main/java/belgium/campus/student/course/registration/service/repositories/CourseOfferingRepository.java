package belgium.campus.student.course.registration.service.repositories;

import belgium.campus.student.course.registration.service.entities.CourseOffering;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseOfferingRepository extends JpaRepository<CourseOffering, Long>
{
    // all offerings for a course
    List<CourseOffering> findByCourse_Id(Long courseId);


    // all offerings for a given term
    List<CourseOffering> findBySemesterIgnoreCaseAndYear(String semester, int year);

}
