package belgium.campus.student.course.registration.service.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "course_offerings")
public class CourseOffering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", nullable = false)
    private Instructor instructor;

    @Column(nullable = false, length = 20)
    private String semester;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private int capacity;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public long getCapacity() {
        return capacity;
    }

    public Long getId()
    {
        return id;
    }

    public void setCourse(Course course) {
    }

    public void setInstructor(Instructor instructor) {
    }

    public void setSemester(String semester) {
    }

    public void setYear(int year) {
    }

    public void setCapacity(int capacity) {
    }


    // Lombok generates all getters/setters, so there’s no risk of “missing return statement”.
}
