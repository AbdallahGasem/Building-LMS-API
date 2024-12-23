package fcaicu.aswe.lms.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fcaicu.aswe.lms.Models.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {
    
}
