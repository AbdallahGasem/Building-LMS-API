package fcaicu.aswe.lms.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fcaicu.aswe.lms.Models.Assessment;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment,Integer>{
    
    
    // get all Assessments of a Certain type(Assign\Quiz) of a course
    @Query(value = "SELECT * FROM Assessment WHERE CrsID= :crsID AND Type= :type ", nativeQuery = true)
    List<Assessment> getAssessmentsByTypeOfCrs(int crsID , String type);

    // get all Assessments of a Course
    @Query(value = "SELECT * FROM Assessment WHERE CrsID= :crsID",nativeQuery = true)
    List<Assessment> getCrsAssessments(int crsID);

    // get all Assignments
    @Query(value = "SELECT * FROM Assessment WHERE Type= 'Assign' ", nativeQuery = true)
    List<Assessment> getAllAssignments(); 
    
    // get all Quizzes
    @Query(value = "SELECT * FROM Assessment WHERE Type= 'Quiz' ", nativeQuery = true)
    List<Assessment> getAllQuizzes(); 

    
}