package belgium.campus.student.course.registration.service.dto;

public record CreateStudentRequest(
        String studentNumber,
        String firstName,
        String lastName,
        String email,
        String phone

)
{}
