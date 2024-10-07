package be.ipam.student.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link be.ipam.student.model.Enrollment}
 */
@Value
public class EnrollmentDto implements Serializable {
    int EnrollmentId;
    StudentDto student;
    CourseDto course;
    Date EnrollmentDate;
}