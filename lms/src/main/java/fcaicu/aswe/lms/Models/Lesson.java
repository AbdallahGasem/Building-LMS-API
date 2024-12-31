package fcaicu.aswe.lms.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int LessonID; // primary Key
    private String Title; //title of the lesson
    @Column(length = 1000)
    private String Description; // Detailed description of the lesson
    private String MediaURL; // URL to media
    // Many lessons belong to one course
    @ManyToOne
    @JoinColumn(name = "CrsID", nullable = false) // Foreign Key to Course
    @JsonBackReference(value = "Course-Lesson") // mapped to the json manage ref
    private Course course;

    public int getLessonID() {return LessonID;}

    public void setLessonID(int lessonID) {LessonID = lessonID;}

    public String getTitle() {return Title;}

    public void setTitle(String title) {Title = title;}

    public String getDescription() {return Description;}

    public void setDescription(String description) {Description = description;}

    public String getMediaURL() {return MediaURL;}

    public void setMediaURL(String mediaURL) {MediaURL = mediaURL;}

    public Course getCourse() {return course;}

    public void setCourse(Course course) {this.course = course;}
}
