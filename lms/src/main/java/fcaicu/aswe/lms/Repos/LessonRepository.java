package fcaicu.aswe.lms.Repos;
import fcaicu.aswe.lms.Models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson,Integer> {
    @Query(value = "SELECT * FROM lessons WHERE course_id = :courseId", nativeQuery = true)
    List<Lesson> findByCourseId(int courseId);
}