package be.ipam.student;

import be.ipam.student.model.Course;
import be.ipam.student.model.Enrollment;
import be.ipam.student.model.Student;
import be.ipam.student.repository.CourseRepository;
import be.ipam.student.repository.EnrollmentRepository;
import be.ipam.student.repository.StudentRepository;
import be.ipam.student.service.CourseService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StudentApplicationTests {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private CourseService courseService;

    /**
     * Ce test ajoute un nouvel étudiant à la base de données. Il crée une nouvelle
     * instance de `Student`, définit son prénom et son nom de famille, puis l'enregistre
     * dans la base de données en utilisant `studentRepository.save()`.
     */
    @Test
    void testAddStudent() {
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");
        studentRepository.save(student);
    }
    @Test void testAddStudent2() {
        Student student = new Student();
        student.setFirstName("Mario");
        student.setLastName("Bros");
        student.setMail("mario.bro" +
                "s@nintendo.jp");
        student = studentRepository.save(student);
        studentRepository.findById(student.getStudentID());
        assertEquals("Mario", student.getFirstName());
    }

    /**
     * Ce test vérifie que le contexte Spring Boot se charge correctement et affiche
     * tous les étudiants présents dans la base de données. Il récupère tous les
     * étudiants en utilisant `studentRepository.findAll()` et les imprime dans la console.
     */
    @Test
    void contextLoads() {
        List<Student> students = studentRepository.findAll();
        for (Student student : students) {
            System.out.println(student.getFirstName() + " " + student.getLastName());
        }
    }
    @Test
    void contextLoads2() {
        List<Student> students = studentRepository.findAll();
        assertFalse(students.isEmpty());
        for (Student student : students) {
            System.out.println(student.getFirstName() + " " + student.getLastName());
        }
    }

    /**
     * Ce test met à jour un étudiant existant dans la base de données. Il récupère
     * l'étudiant avec l'ID 3 en utilisant `studentRepository.findById(3)`, modifie
     * son prénom et son nom de famille, puis enregistre les modifications en utilisant
     * `studentRepository.save()`.
     */
    @Test
    void testUpdateStudent() {
        Student student = studentRepository.findById(7).get();
        student.setFirstName("John1");
        student.setLastName("1");
        studentRepository.save(student);
    }
    @Test
    void testUpdateStudent2() {
        Student student = studentRepository.findById(8).get();
        student.setFirstName("John");
        student.setLastName("Pietrucci");
        student.setMail("John.pietrucci@gmail.com");
        student = studentRepository.save(student);
        student = studentRepository.findById(8).get();

        assertEquals("John", student.getFirstName());
        assertEquals("Pietrucci", student.getLastName());
        assertEquals("John.pietrucci@gmail.com", student.getMail());
    }

    /**
     * Ce test supprime un étudiant de la base de données. Il récupère l'étudiant
     * avec l'ID 3 en utilisant `studentRepository.findById(3)`, puis le supprime
     * de la base de données en utilisant `studentRepository.delete()`.
     */
    @Test
    void testDeleteStudent() {
        Student student = studentRepository.findById(8).get();
        studentRepository.delete(student);
    }
    @Test
    void testDeleteStudent2() {
        Student student = studentRepository.findById(8).get();
        studentRepository.delete(student);
        assertFalse(studentRepository.findById(8).isPresent());
    }

    /**

     Teste l'inscription d'un étudiant à un cours et l'affichage des cours associés.
     Crée un étudiant, un cours, les associe, et vérifie l'inscription.*/
    @Test
    @Transactional
    void testWalkingStudent(){
        Student student = new Student();
        student.setFirstName("Luigi");
        student.setLastName("Bros");
        student.setMail("luigi4@nitendo.be");
        student = studentRepository.save(student);

        Course course = new Course();
        course.setCourseName("C++");
        course.setCourseDescription("C++ programming");
        course = courseRepository.saveAndFlush(course);

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment = enrollmentRepository.saveAndFlush(enrollment);

        student.getEnrollments().add(enrollment);
        student = studentRepository.saveAndFlush(student);

        student = studentRepository.findById(student.getStudentID()).get();

        Set<Enrollment> enrollments = student.getEnrollments();

        System.out.println(student.getFirstName() + " " + student.getLastName() + " est inscrit");
        enrollments.forEach(enroll -> {
            System.out.println(enroll.getCourse().getCourseName());
        });
    }

    /**
     Teste l'affichage des cours auxquels un étudiant est inscrit.
     Récupère un étudiant par son ID et affiche les noms des cours.*/
    @Test
    @Transactional
    void testReadCourse() {
        Student student = studentRepository.findById(10).get();
        student.getEnrollments().forEach(enrollment -> {
            System.out.println(enrollment.getCourse().getCourseName());
        });
    }
}

//regarder jpaBuddy pour vendredi