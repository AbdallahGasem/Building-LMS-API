package fcaicu.aswe.lms.Controllers;

import fcaicu.aswe.lms.Services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/{lessonId}/generate")
    public String generateOTP(@PathVariable int lessonId) {
        return attendanceService.generateOTP(lessonId);
    }

    @PostMapping("/{lessonId}/submit")
    public String submitOTP(@PathVariable int lessonId, @RequestParam String otp, @RequestParam int studentId) {
        return attendanceService.submitOTP(lessonId, otp, studentId);
    }
}