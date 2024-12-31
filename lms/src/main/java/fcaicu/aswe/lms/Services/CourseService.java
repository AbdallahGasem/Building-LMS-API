package fcaicu.aswe.lms.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fcaicu.aswe.lms.Models.Course;
import fcaicu.aswe.lms.Models.User;
import fcaicu.aswe.lms.Repos.CourseRepository;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }
    // Update an existing course
    public Course updateCourse(int courseId, Course updatedCourse) {
        Optional<Course> existingCourseOpt = courseRepository.findById(courseId);
        if (existingCourseOpt.isEmpty()) {
            throw new RuntimeException("Course not found!");
        }

        Course existingCourse = existingCourseOpt.get();
        existingCourse.setTitle(updatedCourse.getTitle());
        existingCourse.setSyllabus(updatedCourse.getSyllabus());
        return courseRepository.save(existingCourse);
    }

    // Delete a course
    public void deleteCourse(int courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new RuntimeException("Course not found!");
        }
        courseRepository.deleteById(courseId);
    }

    public List<Course> getCoursesByInstructorId(int instructorId) {
        return courseRepository.findByInstructorId(instructorId);
    }

    public List<Course> getAvailableCoursesForStudent(User student) {
        return courseRepository.findAvailableCoursesForStudent(student);
    }

    public Course getCourseWithStudents(int courseId) {
        return courseRepository.findByIdWithEnrolledStudents(courseId);
    }

    public List<User>getEnrolledStudentsByCourse(int courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found!"));
        return course.getEnrolledStudents();
    }

}
