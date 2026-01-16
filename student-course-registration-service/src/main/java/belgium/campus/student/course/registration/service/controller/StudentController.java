package belgium.campus.student.course.registration.service.controller;

import belgium.campus.student.course.registration.service.dto.CreateStudentRequest;
import belgium.campus.student.course.registration.service.entities.Student;
import belgium.campus.student.course.registration.service.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@RequestBody CreateStudentRequest req) {
        return studentService.createStudent(req);
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @GetMapping("/by-student-number/{studentNumber}")
    public Student getByStudentNumber(@PathVariable String studentNumber) {
        return studentService.getByStudentNumber(studentNumber);
    }
}
