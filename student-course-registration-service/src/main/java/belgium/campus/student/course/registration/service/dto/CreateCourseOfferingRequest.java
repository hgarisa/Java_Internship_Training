package belgium.campus.student.course.registration.service.dto;

public record CreateCourseOfferingRequest(
        Long courseId,
        Long instructorId,
        String semester,
        int year,
        int capacity

)

{
}
