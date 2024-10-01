package be.ipam.student.repository;

import be.ipam.student.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query("select c from Course c where c.courseName = ?1")
    public Optional<Course> findByCourseName(String courseName);
}
