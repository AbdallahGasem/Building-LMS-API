package fcaicu.aswe.lms.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fcaicu.aswe.lms.Models.Question;
import jakarta.transaction.Transactional;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {
    
    @Query(value = "SELECT * FROM Question WHERE Topic LIKE %:topic% ", nativeQuery = true)
    List<Question> getQsByTopic(String topic);
    
    // @Query(value = "DELETE FROM Question WHERE Topic LIKE %:topic%", nativeQuery = true)
    // void deleteQSsByTopic(String topic);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Question WHERE Topic LIKE %:topic%", nativeQuery = true)
    void deleteQSsByTopic( String topic);


}
