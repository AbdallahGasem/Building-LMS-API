package fcaicu.aswe.lms.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fcaicu.aswe.lms.Embedded.SubmissionKey;
import fcaicu.aswe.lms.Models.Submission;
import java.util.List;


@Repository
public interface SubmissionRepository extends JpaRepository <Submission, SubmissionKey> {
    
    @Query(value =  "SELECT * FROM Submission WHERE AssessID = :assessID", nativeQuery = true)
    List<Submission> findByAssessment(int assessID);
    
    @Query(value =  "SELECT * FROM Submission WHERE SID = :sid", nativeQuery = true)
    List<Submission> findByStudent(int sid);
} 
