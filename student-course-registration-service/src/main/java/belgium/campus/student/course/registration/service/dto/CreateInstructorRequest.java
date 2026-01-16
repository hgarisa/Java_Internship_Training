package belgium.campus.student.course.registration.service.dto;

public record CreateInstructorRequest(
        String staffNumber,
        String firstName,
        String lastName,
        String email

)
{}
