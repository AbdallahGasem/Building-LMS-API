package fcaicu.aswe.lms.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Assessment") // Match the actual table name in the database
public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // specifing that the pk is autoincreamented
    @Column(name = "AssessID")
    private int AssessID;
    
    // @Column(name = "CrsID",insertable = false, updatable = false) // Prevent duplication
    // private int CrsID; // fk to course model
    
    @Column(name = "Type", length = 6)
    private String Type;    // validate the varchar(6) & ([type] IN ('Assign','Quiz'))!!!!! CHECK ([Type] IN ('Assign','Quiz'))
    
    @Column(name = "Deadline")
    private String Deadline;    // validate DATETIME
    
    @Column(name = "Topic", length = 100, nullable = false)
    private String Topic;   // VARCHAR(100) NOT NULL

    @Column(name = "AssessFILE", columnDefinition = "Varchar(255)", nullable = true)
    private String AssessFILE;
   
    

    /*
    "mappedBy" Attribute in `@OneToMany` Relationship
    The `mappedBy` attribute in the `@OneToMany` annotation is used to specify the field in the child entity that owns 
    the relationship. It indicates that the foreign key is managed by the child entity and not by the parent entity.
   
    mappedBy = "assessment"`**: This indicates that the `questions` list in the `Assessment` entity is mapped by the `assessment` 
    field in the `Question` entity. It means that the `Question` entity owns the foreign key relationship.
    
    Summary

    `mappedBy` Attribute**: Used in the parent entity (`@OneToMany` side) to specify the field in the child entity that owns
    the relationship. It indicates that the foreign key is managed by the child entity.
    
    `@JoinColumn` Annotation**: Used in the child entity (`@ManyToOne` side) to specify the foreign key column that references the primary key in the parent entity. The name attribute specifies the column name, and nullable specifies whether the column can be null.
    These annotations together ensure that the relationship between `Assessment` and `Question` is properly
    mapped and managed in the JPA context.
    */

    // Assessment-Course RelationShip <has>
    // fk to CrsID in Course
    @ManyToOne
    @JoinColumn(name = "CrsID",nullable = true )
    @JsonBackReference(value = "Assessment-Course") // mapped to the json manage ref
    private Course course; 
    
    // Assessment-Question RelationShip <has>
    @OneToMany(mappedBy = "assessment", cascade = {CascadeType.PERSIST, CascadeType.MERGE})  // specify the field in the child entity that owns the relationship. It indicates that the foreign key is managed by the child entity. see what cascade do on the chat(same as if you defined it in the db)
    @JsonManagedReference(value = "Assessment-Question")
    private List<Question> questions;
    
    // Submission-Assessment RelationShip <Submitted>
    @OneToMany(mappedBy = "assessment", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "Submission-Assessment")
    private List<Submission> submissions ;

    public int getAssessID() {
        return AssessID;
    }

    // public Integer getCrsID() {
    //     return CrsID;
    // }

    // public void setCrsID(int crsID) {
    //     CrsID = crsID;
    // }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDeadline() {
        return Deadline;
    }

    public void setDeadline(String deadline) {
        Deadline = deadline;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public String getAssessFILE() {
        return AssessFILE;
    }

    public void setAssessFILE(String assessFILE) {
        this.AssessFILE = assessFILE;
    }

    public Course getCourse(){
        return course;
    }
    
    public void setCourse(Course course){
        this.course = course;
    }


    //----------------------------------------[ Assessment-Question Relationship Methods ]---------------------------------------//
   
    public List<Question> getQuestions() {
        return questions;
    }
    
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    // Synchronization of the bi directional relationship!
    public void addQuestion(Question qs) {
        questions.add(qs);
        qs.setAssessment(this);
    }
 
    public void removeQuestion(Question qs) {
        questions.remove(qs);
        qs.setAssessment(null);
    }
    
    
    //---------------------------------------[ Submission-Assessment Relationship Methods ]--------------------------------------//
   
    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }

    // Synchronization of the bi directional relationship!
    public void addSubmission(Submission submission) {
        submissions.add(submission);
        submission.setAssessment(this);
    }
    
    public void removeSubmission(Submission submission) {
        submissions.remove(submission);
        submission.setAssessment(null);
    }

}