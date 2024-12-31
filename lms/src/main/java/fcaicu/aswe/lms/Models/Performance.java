package fcaicu.aswe.lms.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Performance")
public class Performance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int studentId;

    @Column(nullable = false)
    private double grade;

    @Column(nullable = false)
    private int attendance;

    @Column(nullable = false)
    private int assignmentsCompleted;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public int getAssignmentsCompleted() {
        return assignmentsCompleted;
    }

    public void setAssignmentsCompleted(int assignmentsCompleted) {
        this.assignmentsCompleted = assignmentsCompleted;
    }
}