package be.ipam.student.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Enrollment")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int EnrollmentId;
    @ManyToOne
    @JoinColumn(name ="studentID")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "courseID")
    private Course course;
    private Date EnrollmentDate;


    public int getEnrollmentId() {
        return EnrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        EnrollmentId = enrollmentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getEnrollmentDate() {
        return EnrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        EnrollmentDate = enrollmentDate;
    }
}
