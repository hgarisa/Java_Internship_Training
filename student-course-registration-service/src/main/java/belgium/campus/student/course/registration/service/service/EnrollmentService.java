package belgium.campus.student.course.registration.service.service;

import belgium.campus.student.course.registration.service.dto.EnrollRequest;
import belgium.campus.student.course.registration.service.entities.CourseOffering;
import belgium.campus.student.course.registration.service.entities.Enrollment;
import belgium.campus.student.course.registration.service.entities.Student;
import belgium.campus.student.course.registration.service.exceptions.BadRequestException;
import belgium.campus.student.course.registration.service.exceptions.ResourceNotFoundException;
import belgium.campus.student.course.registration.service.repositories.CourseOfferingRepository;
import belgium.campus.student.course.registration.service.repositories.EnrollmentRepository;
import belgium.campus.student.course.registration.service.repositories.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnrollmentService {

    private static final String STATUS_ENROLLED = "ENROLLED";
    private static final String STATUS_DROPPED = "DROPPED";

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseOfferingRepository offeringRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             StudentRepository studentRepository,
                             CourseOfferingRepository offeringRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.offeringRepository = offeringRepository;
    }

    @Transactional
    public Enrollment enroll(EnrollRequest req) {
        if (req.studentNumber() == null || req.studentNumber().isBlank()) {
            throw new BadRequestException("studentNumber is required");
        }
        if (req.offeringId() == null) {
            throw new BadRequestException("offeringId is required");
        }

        Student student = studentRepository.findByStudentNumber(req.studentNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for studentNumber: " + req.studentNumber()));

        CourseOffering offering = offeringRepository.findById(req.offeringId())
                .orElseThrow(() -> new ResourceNotFoundException("CourseOffering not found for id: " + req.offeringId()));

        // Prevent duplicate enrollment
        boolean alreadyEnrolled = enrollmentRepository.existsByStudent_IdAndOffering_Id(student.getId(), offering.getId());
        if (alreadyEnrolled) {
            throw new BadRequestException("Student is already enrolled in this offering.");
        }

        // Capacity check (counts only ENROLLED)
        long enrolledCount = enrollmentRepository.countByOffering_IdAndStatus(offering.getId(), STATUS_ENROLLED);
        if (enrolledCount >= offering.getCapacity()) {
            throw new BadRequestException("Offering is full. Capacity reached: " + offering.getCapacity());
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setOffering(offering);
        enrollment.setStatus(STATUS_ENROLLED);

        return enrollmentRepository.save(enrollment);
    }

    @Transactional(readOnly = true)
    public Enrollment getById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found for id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Enrollment> getByStudentId(Long studentId) {
        if (studentId == null) throw new BadRequestException("studentId is required");
        return enrollmentRepository.findByStudent_Id(studentId);
    }

    @Transactional(readOnly = true)
    public List<Enrollment> getByOfferingId(Long offeringId) {
        if (offeringId == null) throw new BadRequestException("offeringId is required");
        return enrollmentRepository.findByOffering_Id(offeringId);
    }

    @Transactional
    public void dropEnrollment(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found for id: " + id));

        // Soft drop (keeps history)
        enrollment.setStatus(STATUS_DROPPED);
        enrollmentRepository.save(enrollment);
    }
}
