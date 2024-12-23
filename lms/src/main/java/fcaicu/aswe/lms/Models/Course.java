package fcaicu.aswe.lms.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

import fcaicu.aswe.lms.Models.Course;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CrsID;
    private String Title; //  UNIQUE NOT NULL
    private String Syllabus;
    private int InstructorID; // fk to user , not null

    // fk to user
    @ManyToOne
    @JoinColumn(name = "UID",nullable = false)
    private User user;

    // fk assessment
    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<Course> courses;


    public int getCrsID() {
        return CrsID;
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

    public int getInstructorID() {
        return InstructorID;
    }

    public void setInstructorID(int instructorID) {
        InstructorID = instructorID;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }




}
