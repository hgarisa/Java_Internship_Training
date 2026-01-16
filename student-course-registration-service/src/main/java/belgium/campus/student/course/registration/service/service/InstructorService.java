package belgium.campus.student.course.registration.service.service;

import belgium.campus.student.course.registration.service.exceptions.ResourceNotFoundException;
import belgium.campus.student.course.registration.service.repositories.InstructorRepository;

import org.springframework.stereotype.Service;
import belgium.campus.student.course.registration.service.entities.Instructor;
import belgium.campus.student.course.registration.service.dto.CreateInstructorRequest;
import belgium.campus.student.course.registration.service.exceptions.BadRequestException;

import org.springframework.transaction.annotation.Transactional;

@Service
public class InstructorService
{
    private final InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository)
    {
        this.instructorRepository = instructorRepository;
    }

    @Transactional
    public Instructor createInstructor(CreateInstructorRequest req)
    {
        if (req.staffNumber() == null || req.staffNumber().isBlank()) {
            throw new BadRequestException("staffNumber is required");
        }
        if (req.email() == null || req.email().isBlank()) {
            throw new BadRequestException("email is required");
        }

        instructorRepository.findByStaffNumber(req.staffNumber())
                .ifPresent(i -> { throw new BadRequestException("Instructor already exists with staffNumber: " + req.staffNumber()); });

        Instructor i = new Instructor();
        i.setStaffNumber(req.staffNumber());
        i.setFirstName(req.firstName());
        i.setLastName(req.lastName());
        i.setEmail(req.email());

        return instructorRepository.save(i);


    }

    @Transactional(readOnly = true)
    public Instructor getByStaffNumber(String staffNumber)
    {
        return instructorRepository.findByStaffNumber(staffNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found for staffNumber: " + staffNumber));

    }

    @Transactional(readOnly = true)
    public Instructor getById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found for id: " + id));
    }


    public Instructor getByEmail(String email)
    {

        if (email == null || email.isBlank()) {
            throw new BadRequestException("email is required");
        }

        return instructorRepository.findByEmail(email.trim())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Instructor not found for email: " + email));
    }
}
