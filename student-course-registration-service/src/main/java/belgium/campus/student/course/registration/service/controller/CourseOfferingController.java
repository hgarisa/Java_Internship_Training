package belgium.campus.student.course.registration.service.controller;

import belgium.campus.student.course.registration.service.dto.CreateCourseOfferingRequest;
import belgium.campus.student.course.registration.service.entities.CourseOffering;
import belgium.campus.student.course.registration.service.service.CourseOfferingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offerings")
public class CourseOfferingController {

    private final CourseOfferingService offeringService;

    public CourseOfferingController(CourseOfferingService offeringService) {
        this.offeringService = offeringService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseOffering create(@RequestBody CreateCourseOfferingRequest req) {
        return offeringService.createOffering(req);
    }

    @GetMapping("/{id}")
    public CourseOffering getById(@PathVariable Long id) {
        return offeringService.getById(id);
    }

    @GetMapping("/by-course/{courseId}")
    public List<CourseOffering> getByCourse(@PathVariable Long courseId) {
        return offeringService.getByCourseId(courseId);
    }

    @GetMapping("/by-term")
    public List<CourseOffering> getByTerm(@RequestParam String semester,
                                          @RequestParam int year) {
        return offeringService.getByTerm(semester, year);
    }
}
