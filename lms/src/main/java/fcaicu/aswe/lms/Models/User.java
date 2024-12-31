package fcaicu.aswe.lms.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "usertable")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "[UID]")
    private int UID;

    @Column(length = 30)
    private String Name;    // VARCHAR(30)

    @Column(unique = true, nullable = false)
    private String Email;   // unique not null

    @Column(nullable = false, length = 255)
    private String Password;    // NVARCHAR(255), [need to be encrepted before sending to the DB ]
    
    @Column(name = "Role")
    private String Role;    // NVARCHAR(20) , CHECK ([Role] IN ('ADMIN', 'INSTRUCTOR', 'STUDENT')) ---> Could force an enum!
        


    // User-User Relationship <create>
    // fk to UID User
    @ManyToOne
    @JoinColumn(name = "creationadminid", referencedColumnName = "[UID]")
    private User creationAdmin;

    // Course-User relationship <Manages>
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "Course-User")
    private List<Course> taughtCourses = new ArrayList<>();

    @ManyToMany(mappedBy = "enrolledStudents")
    private List<Course> enrolledCourses = new ArrayList<>();


    // Submission-User RelationShip <Submit>
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "Submission-User" )
    private List<Submission> submissions;


    public int getUID() {
        return UID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = encryptPassword(password);
    }

    private String encryptPassword(String password) {
        // Add your encryption logic here
        return password; // Replace this with actual encryption logic
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }


    public List<Course> getTaughtCourses(){
        return taughtCourses;
    }

    public void setTaughtCourses(List<Course> taughtCourses){
        this.taughtCourses = taughtCourses;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }


    public User getCreationAdmin() {
        return creationAdmin;
    }

    public void setCreationAdmin(User creationAdmin) {
        this.creationAdmin = creationAdmin;
    }
    
    //-----------------------------------------[Submission-User Relationship Methods]--------------------------------------------//
    
    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }

    // synchronization of the bi directional relationship!
    public void addSubmission(Submission submission) {
        submissions.add(submission);
        submission.setStudent(this);
    }
    
    public void removeSubmission(Submission submission) {
        submissions.remove(submission);
        submission.setStudent(null);
    }
    
}
