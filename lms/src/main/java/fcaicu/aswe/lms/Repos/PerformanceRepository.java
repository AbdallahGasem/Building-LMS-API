package fcaicu.aswe.lms.Repos;

import fcaicu.aswe.lms.Models.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Integer> {
}