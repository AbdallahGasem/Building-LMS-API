package fcaicu.aswe.lms.Models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fcaicu.aswe.lms.Embedded.SubmissionKey;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class Submission {
    
    @EmbeddedId
    private SubmissionKey submissionID;   // Composite(assessID & SID)
    // private int AssessID; // m to 1 fk to the Assessment --- pk 
    // private int SID; // m to 1 fk to the User --- pk
    
    @Column(name = "FileURL",length = 255)
    private String FileURL; // VARCHAR(255)
    @Column(name = "Subtime")
    private LocalDateTime Subtime; // DATETIME NOT NULL DEFAULT getdate()
    @Column(name = "Status",length = 255)
    private String Status; // VARCHAR(255)
    @Column(name = "Score")
    private Integer Score;  // changed int to INTEGER to allow null values!
    
    @Column(name = "feedback")  
    private String FeedBack;

    // Submission-Assessment Relationship <Submitted>
    @ManyToOne
    @JoinColumn(name = "AssessID", referencedColumnName = "AssessID") //Foreign key to assessment table
    @MapsId("AssessID") // Map composite key part to assessment
    @JsonBackReference(value = "Submission-Assessment")     // mapped to the json manage ref
    private Assessment assessment;
    
    // Submission-User RelationShip <Submit>
    // fk to UID User 
    @ManyToOne
    @MapsId("SID")  // Map composite key part to assessment
    @JoinColumn(name = "SID", referencedColumnName = "[UID]") //Foreign key to user table
    @JsonBackReference(value = "Submission-User" )
    private User student;

    
    public SubmissionKey getSubmissionID() {
        return submissionID;
    }

    public void setSubmissionID(SubmissionKey submissionID) {
        this.submissionID = submissionID;
    }

    public String getFileURL() {
        return FileURL;
    }

    public void setFileURL(String fileURL) {
        FileURL = fileURL;
    }

    public LocalDateTime getSubtime() {
        return Subtime;
    }

    public void setSubtime(LocalDateTime subtime) {
        Subtime = subtime;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Integer getScore() {
        return Score != null? Score : -999 ;
    }

    public void setScore(Integer score) {
        Score = score;
    }

    public String getFeedBack() {
        return FeedBack;
    }

    public void setFeedBack(String feedBack) {
        FeedBack = feedBack;
    }


    public Assessment getAssessment() {
        return assessment;
    }

    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }
    
    
}
