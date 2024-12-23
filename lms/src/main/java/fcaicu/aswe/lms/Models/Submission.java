package fcaicu.aswe.lms.Models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Submission {
    @Id  //complex how
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int AssessID; // m to 1 fk to the Assessment --- pk 
    private int SID; // m to 1 fk to the User --- pk
    private String FileURL; // VARCHAR(255)
    private LocalDateTime Subtime; // DATETIME NOT NULL DEFAULT getdate()
    private String Status; // VARCHAR(255)
    private int Score;
    private String FeedBack;

    @ManyToOne
    @JoinColumn(name = "AssessID", nullable = true)
    private Assessment assessment;
    
    @ManyToOne
    @JoinColumn(name = "UID", nullable = true)
    private User user;

    public int getAssessID() {
        return AssessID;
    }


    public int getSID() {
        return SID;
    }

    public void setSID(int SID) {
        this.SID = SID;
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

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
