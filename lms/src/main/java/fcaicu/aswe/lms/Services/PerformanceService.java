package fcaicu.aswe.lms.Services;

import fcaicu.aswe.lms.Models.Performance;
import fcaicu.aswe.lms.Repos.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PerformanceService {

    @Autowired
    private PerformanceRepository performanceRepository;

    public Optional<Performance> getPerformance(int studentId) {
        return performanceRepository.findById(studentId);
    }
}