package belgium.campus.student.course.registration.service.controller;

import belgium.campus.student.course.registration.service.dto.EnrollRequest;
import belgium.campus.student.course.registration.service.entities.Enrollment;
import belgium.campus.student.course.registration.service.service.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Enrollment enroll(@RequestBody EnrollRequest req) {
        return enrollmentService.enroll(req);
    }

    @GetMapping("/{id}")
    public Enrollment getById(@PathVariable Long id) {
        return enrollmentService.getById(id);
    }

    @GetMapping("/by-student/{studentId}")
    public List<Enrollment> getByStudent(@PathVariable Long studentId) {
        return enrollmentService.getByStudentId(studentId);
    }

    @GetMapping("/by-offering/{offeringId}")
    public List<Enrollment> getByOffering(@PathVariable Long offeringId) {
        return enrollmentService.getByOfferingId(offeringId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void drop(@PathVariable Long id) {
        enrollmentService.dropEnrollment(id);
    }
}
