package fcaicu.aswe.lms.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import fcaicu.aswe.lms.Models.Course;

@Entity
@Table(name = "Course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CrsID;

    @Column(unique = true, nullable = false)
    private String Title; // UNIQUE NOT NULL

    @Column(nullable = true)
    private String Syllabus;

    // @Column(unique = true, nullable = false)
    // private int InstructorID; // fk to user , not null

    // course relationship <Manages>
    // fk to user
    @ManyToOne
    @JoinColumn(name = "instructorid", nullable = false, referencedColumnName = "[UID]")
    @JsonBackReference(value = "Course-User") // mapped to the json manage ref
    private User instructor;

    // Assessment-Course RelationShip <has>
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "Assessment-Course") // mapped to the json manage ref
    private List<Assessment> assessments;

    // Course-Lesson Relationship with Lessons
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "Course-Lesson")
    private List<Lesson> lessons = new ArrayList<>();

    // Relationship with Enrollments
    @ManyToMany
    @JoinTable(name = "Enroll", joinColumns = @JoinColumn(name = "CrsID"), inverseJoinColumns = @JoinColumn(name = "SID")// what?
    )
    private List<User> enrolledStudents = new ArrayList<>(); // Error

    public Course() {
    }

    public int getCrsID() {
        return CrsID;
    }

    public void setCrsID(int id) {
        this.CrsID = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSyllabus() {
        return Syllabus;
    }

    public void setSyllabus(String syllabus) {
        Syllabus = syllabus;
    }

    public User getInstructor() {
        return instructor;
    }

    public void setInstructor(User instructor) {
        this.instructor = instructor;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    // -----------------------------------------[ Assessment-Course RelationshipMethods ]----------------------------------------//

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
    }

    public List<User> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(List<User> enrolledStudents) {
        this.enrolledStudents = enrolledStudents; // Error
    }

    // Synchronization
    public void addAssessment(Assessment a) {
        assessments.add(a);
        a.setCourse(this);
    }

    public void removeAssessment(Assessment a) {
        assessments.remove(a);
        a.setCourse(null);
    }

}
