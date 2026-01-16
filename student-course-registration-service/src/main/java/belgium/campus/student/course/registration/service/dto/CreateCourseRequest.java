package belgium.campus.student.course.registration.service.dto;

public record CreateCourseRequest(

        String courseCode,
        String courseName,
        int credits


) {}
