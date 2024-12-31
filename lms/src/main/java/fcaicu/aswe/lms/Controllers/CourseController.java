package fcaicu.aswe.lms.Controllers;

import fcaicu.aswe.lms.Models.Course;
import fcaicu.aswe.lms.Models.User;
import fcaicu.aswe.lms.Services.CourseService;
import fcaicu.aswe.lms.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @PostMapping
    public Course createCourse(@RequestBody Course course){
        return courseService.createCourse(course);
    }
    @GetMapping("/instructor/{instructorId}")
    public List<Course>getCoursesByInstructorId(@PathVariable int instructorId){
        return courseService.getCoursesByInstructorId(instructorId);
    }

    @GetMapping("/student/{studentName}")
    public List<Course>getAvailableCoursesForStudent(@PathVariable String studentName){
        User student = userService.getUserByName(studentName);
        return courseService.getAvailableCoursesForStudent(student);
    }

    @GetMapping("/{courseId}/students")
    public Course getCourseWithStudents(@PathVariable int courseId) {
        return courseService.getCourseWithStudents(courseId);
    }


    @GetMapping("/{courseId}/enrolled-students")
    public List<User>getEnrolledStudents(@PathVariable int courseId) {
        return courseService.getEnrolledStudentsByCourse(courseId);
    }


    @PutMapping("/{courseId}")
    public Course updateCourse(@PathVariable int courseId, @RequestBody Course updatedCourse) {
        return courseService.updateCourse(courseId, updatedCourse);
    }

    @DeleteMapping("/{courseId}")
    public String deleteCourse(@PathVariable int courseId) {
        courseService.deleteCourse(courseId);
        return "Course deleted successfully!";
    }
}
