package belgium.campus.student.course.registration.service.controller;

import belgium.campus.student.course.registration.service.dto.CreateInstructorRequest;
import belgium.campus.student.course.registration.service.entities.Instructor;
import belgium.campus.student.course.registration.service.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Instructor create(@RequestBody CreateInstructorRequest req) {
        return instructorService.createInstructor(req);
    }

    @GetMapping("/{id}")
    public Instructor getById(@PathVariable Long id) {
        return instructorService.getById(id);
    }

    // If you prefer "by-email" endpoint (similar to student by studentNumber)
    @GetMapping("/by-email/{email}")
    public Instructor getByEmail(@PathVariable String email) {
        return instructorService.getByEmail(email);
    }
}
