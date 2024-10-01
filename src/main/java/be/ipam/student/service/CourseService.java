package be.ipam.student.service;

import be.ipam.student.model.Course;
import be.ipam.student.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public Optional<Course> getCourseById(int id){
        return courseRepository.findById(id);
    }

    public Optional<Course> getCourseByName(String courseName){
        return courseRepository.findByCourseName(courseName);
    }


}