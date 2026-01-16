package belgium.campus.student.course.registration.service.controller;

import belgium.campus.student.course.registration.service.dto.CreateCourseRequest;
import belgium.campus.student.course.registration.service.entities.Course;
import belgium.campus.student.course.registration.service.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Course create(@RequestBody CreateCourseRequest req) {
        return courseService.createCourse(req);
    }

    @GetMapping("/{id}")
    public Course getById(@PathVariable Long id) {
        return courseService.getById(id);
    }

    @GetMapping("/by-code/{courseCode}")
    public Course getByCode(@PathVariable String courseCode) {
        return courseService.getByCourseCode(courseCode);
    }
}
