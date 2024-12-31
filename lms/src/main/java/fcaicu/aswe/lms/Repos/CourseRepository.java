package fcaicu.aswe.lms.Repos;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import fcaicu.aswe.lms.Models.Course;
import fcaicu.aswe.lms.Models.User;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {


    // @Query("SELECT c FROM Course c WHERE c.instructor = :instructorID")
    @Query(value = "SELECT * FROM Course WHERE InstructorID = :instructorID", nativeQuery = true)
    List<Course> findByInstructorId(int instructorID);

    // Find courses available for a specific student
    @Query("SELECT c FROM Course c WHERE :student NOT MEMBER OF c.enrolledStudents")
    List<Course> findAvailableCoursesForStudent(@Param("student") User student);

    // Fetch a course along with its enrolled students
    @Query("SELECT c FROM Course c LEFT JOIN FETCH c.enrolledStudents WHERE c.CrsID = :CrsID")
    Course findByIdWithEnrolledStudents(@Param("courseId") int courseId);
}
