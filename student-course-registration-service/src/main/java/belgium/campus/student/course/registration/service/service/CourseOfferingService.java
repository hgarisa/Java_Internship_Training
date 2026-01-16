package belgium.campus.student.course.registration.service.service;

import belgium.campus.student.course.registration.service.dto.CreateCourseOfferingRequest;
import belgium.campus.student.course.registration.service.entities.Course;
import belgium.campus.student.course.registration.service.entities.CourseOffering;
import belgium.campus.student.course.registration.service.entities.Instructor;
import belgium.campus.student.course.registration.service.exceptions.BadRequestException;
import belgium.campus.student.course.registration.service.exceptions.ResourceNotFoundException;
import belgium.campus.student.course.registration.service.repositories.CourseOfferingRepository;
import belgium.campus.student.course.registration.service.repositories.CourseRepository;
import belgium.campus.student.course.registration.service.repositories.InstructorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseOfferingService {

    private final CourseOfferingRepository offeringRepository;
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    public CourseOfferingService(CourseOfferingRepository offeringRepository,
                                 CourseRepository courseRepository,
                                 InstructorRepository instructorRepository) {
        this.offeringRepository = offeringRepository;
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    @Transactional
    public CourseOffering createOffering(CreateCourseOfferingRequest req) {
        if (req.courseId() == null) throw new BadRequestException("courseId is required");
        if (req.instructorId() == null) throw new BadRequestException("instructorId is required");
        if (req.semester() == null || req.semester().isBlank()) throw new BadRequestException("semester is required");
        if (req.year() <= 0) throw new BadRequestException("year must be > 0");
        if (req.capacity() <= 0) throw new BadRequestException("capacity must be > 0");

        Course course = courseRepository.findById(req.courseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found for id: " + req.courseId()));

        Instructor instructor = instructorRepository.findById(req.instructorId())
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found for id: " + req.instructorId()));

        CourseOffering offering = new CourseOffering();
        offering.setCourse(course);
        offering.setInstructor(instructor);
        offering.setSemester(req.semester());
        offering.setYear(req.year());
        offering.setCapacity(req.capacity());

        return offeringRepository.save(offering);
    }

    @Transactional(readOnly = true)
    public CourseOffering getById(Long offeringId) {
        return offeringRepository.findById(offeringId)
                .orElseThrow(() -> new ResourceNotFoundException("CourseOffering not found for id: " + offeringId));
    }

    @Transactional(readOnly = true)
    public List<CourseOffering> getByCourseId(Long courseId) {
        if (courseId == null) throw new BadRequestException("courseId is required");
        return offeringRepository.findByCourse_Id(courseId);
    }

    @Transactional(readOnly = true)
    public List<CourseOffering> getByTerm(String semester, int year) {
        if (semester == null || semester.isBlank()) throw new BadRequestException("semester is required");
        if (year <= 0) throw new BadRequestException("year must be > 0");

        return offeringRepository.findBySemesterIgnoreCaseAndYear(semester, year);
    }
}
