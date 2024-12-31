package fcaicu.aswe.lms.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int AttID; // Primary Key
    private String OTP; // OTP for the session(like a password)
    private boolean Status; // Attendance status 

    // Many attendance records belong to one lesson
    @ManyToOne
    @JoinColumn(name = "LessonID", nullable = false)
    private Lesson lesson;

    // Many attendance records belong to one student
    @ManyToOne
    @JoinColumn(name = "SID", nullable = false) // Foreign key to Student
    private User student;

    public int getAttID() {return AttID;}

    public void setAttID(int attID) {AttID = attID;}

    public String getOTP() {return OTP;}

    public void setOTP(String OTP) {this.OTP = OTP;}

    public boolean isStatus() {return Status;}

    public void setStatus(boolean status) {Status = status;}

    public Lesson getLesson() {return lesson;}

    public void setLesson(Lesson lesson) {this.lesson = lesson;}

    public User getStudent() {return student;}
  
    public void setStudent(User student) {this.student = student;}
}
