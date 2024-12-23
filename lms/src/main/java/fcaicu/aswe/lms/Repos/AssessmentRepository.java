package fcaicu.aswe.lms.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fcaicu.aswe.lms.Models.Assessment;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment,Integer>{
    
}