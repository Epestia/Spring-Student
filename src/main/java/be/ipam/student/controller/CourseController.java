package be.ipam.student.controller;

import be.ipam.student.model.Course;
import be.ipam.student.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /*public CourseController(CourseService courseService) {
        this.courseService = new CourseService();
    }*/
    @GetMapping("/{id}")
    public ResponseEntity<Course> findCourseById(@PathVariable int id){
        Optional<Course> course = courseService.getCourseById(id);
        return course.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{courseName}")
    public ResponseEntity<Course> findCourseByName(@PathVariable String courseName){
        Optional<Course> course = courseService.getCourseByName(courseName);
        return course.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }
}