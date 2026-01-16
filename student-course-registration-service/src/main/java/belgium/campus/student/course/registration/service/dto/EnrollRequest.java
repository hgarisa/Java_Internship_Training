package belgium.campus.student.course.registration.service.dto;

public record EnrollRequest(

        String studentNumber,
        Long offeringId
)
{

}
