package belgium.campus.student.course.registration.service.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "courses")
public class Course
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_code", nullable = false, unique = true, length = 20)
    private String courseCode;

    @Column(name = "course_name", nullable = false, length = 200)
    private String courseName;

    @Column(nullable = false)
    private int credits;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public void setCourseCode(String courseCode)
    {
        this.courseCode = courseCode;

    }

    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }

    public void setCredits(int credits)
    {
        this.credits = credits;
    }

    // getters/setters

}
