package fcaicu.aswe.lms.Controllers;

import fcaicu.aswe.lms.Models.Performance;
import fcaicu.aswe.lms.Services.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/performance")
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

    @GetMapping("/{studentId}")
    public Optional<Performance> getPerformance(@PathVariable int studentId) {
        return performanceService.getPerformance(studentId);
    }
}