package belgium.campus.student.course.registration.service.service;

import belgium.campus.student.course.registration.service.dto.CreateStudentRequest;
import belgium.campus.student.course.registration.service.entities.Student;
import belgium.campus.student.course.registration.service.exceptions.BadRequestException;
import belgium.campus.student.course.registration.service.exceptions.ResourceNotFoundException;
import belgium.campus.student.course.registration.service.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService
{
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository)
    {
        this.studentRepository = studentRepository;
    }


    @Transactional
    public Student createStudent(CreateStudentRequest req)
    {

        if (req.studentNumber() == null || req.studentNumber().isBlank())
        {

            throw new BadRequestException("studentNumber is required");
        }
        if (req.email() == null || req.email().isBlank()) {
            throw new BadRequestException("email is required");
        }


        studentRepository.findByStudentNumber(req.studentNumber())
                .ifPresent(s -> { throw new BadRequestException("Student already exists with studentNumber: " + req.studentNumber());});


        Student s = new Student();
        s.setStudentNumber(req.studentNumber());
        s.setFirstName(req.firstName());
        s.setLastName(req.lastName());
        s.setEmail(req.email());
        s.setPhone(req.phone());
        // status + created_at are DB defaults; set only if your entity requires it

        return studentRepository.save(s);

    }

    @Transactional(readOnly = true)
    public Student getByStudentNumber(String studentNumber)
    {

        return studentRepository.findByStudentNumber(studentNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for studentNumber: " + studentNumber));

    }

    @Transactional(readOnly = true)
    public Student getById(Long id)
    {

        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for id: " + id));


    }


}
