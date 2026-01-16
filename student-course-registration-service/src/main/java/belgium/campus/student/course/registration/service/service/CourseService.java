package belgium.campus.student.course.registration.service.service;
import belgium.campus.student.course.registration.service.dto.CreateCourseRequest;
import belgium.campus.student.course.registration.service.entities.Course;
import belgium.campus.student.course.registration.service.exceptions.BadRequestException;
import belgium.campus.student.course.registration.service.exceptions.ResourceNotFoundException;
import belgium.campus.student.course.registration.service.repositories.CourseRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;


@Service
public class CourseService
{
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    @Transactional
    public Course createCourse(CreateCourseRequest req) {
        if (req.courseCode() == null || req.courseCode().isBlank()) {
            throw new BadRequestException("courseCode is required");
        }
        if (req.courseName() == null || req.courseName().isBlank()) {
            throw new BadRequestException("courseName is required");
        }
        if (req.credits() <= 0) {
            throw new BadRequestException("credits must be > 0");
        }

        courseRepository.findByCourseCode(req.courseCode())
                .ifPresent(c -> { throw new BadRequestException("Course already exists with courseCode: " + req.courseCode()); });

        Course c = new Course();
        c.setCourseCode(req.courseCode());
        c.setCourseName(req.courseName());
        c.setCredits(req.credits());

        return courseRepository.save(c);
    }
    @Transactional(readOnly = true)
    public Course getByCourseCode(String courseCode) {
        return courseRepository.findByCourseCode(courseCode)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found for courseCode: " + courseCode));
    }

    @Transactional(readOnly = true)
    public Course getById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found for id: " + id));
    }

}
