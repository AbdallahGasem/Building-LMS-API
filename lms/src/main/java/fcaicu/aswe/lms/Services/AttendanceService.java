package fcaicu.aswe.lms.Services;

import fcaicu.aswe.lms.Models.Attendance;
import fcaicu.aswe.lms.Models.Lesson;
import fcaicu.aswe.lms.Models.User;
import fcaicu.aswe.lms.Repos.AttendanceRepository;
import fcaicu.aswe.lms.Repos.LessonRepository;
import fcaicu.aswe.lms.Repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private UserRepository userRepository;

    public String generateOTP(int lessonId) {
        Optional<Lesson> lesson = lessonRepository.findById(lessonId);
        if (lesson.isPresent()) {
            String otp = String.valueOf(new Random().nextInt(999999));
            // Save OTP to the lesson or another appropriate place
            return otp;
        }
        return "Lesson not found";
    }

    public String submitOTP(int lessonId, String otp, int studentId) {
        Optional<Lesson> lesson = lessonRepository.findById(lessonId);
        Optional<User> student = userRepository.findById(studentId);
        if (lesson.isPresent() && student.isPresent()) {
            // Validate OTP and mark attendance
            Attendance attendance = new Attendance();
            attendance.setLesson(lesson.get());
            attendance.setStudent(student.get());
            attendance.setOTP(otp);
            attendance.setStatus(true); // Assuming OTP is valid
            attendanceRepository.save(attendance);
            return "Attendance marked";
        }
        return "Invalid lesson or student";
    }
}
