package fcaicu.aswe.lms.Models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // specifing that the pk is autoincreamented
    private int AssessID;
    private int CrsID; // fk to course model
    private String Type;    // validate the varchar(6) & ([Type] IN ('Assign','Quiz'))
    private String Deadline;    // validate DATETIME
    private String Topic;

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
    //  fk relationship of question model
    @OneToMany(mappedBy = "assessment", cascade = CascadeType.ALL)  //specify the field in the child entity that owns the relationship. It indicates that the foreign key is managed by the child entity. see what cascade do on the chat(same as if you defined it in the db)
    private List<Question> questions;

    // CrsId fk relationship
    @ManyToOne
    @JoinColumn(name = "CrsID",nullable = true )
    private Course course; 

    // submision relation
    @OneToMany(mappedBy = "assessment", cascade = CascadeType.ALL)
    private List<Submission> submissions ;

    public int getAssessID() {
        return AssessID;
    }

    public int getCrsID() {
        return CrsID;
    }

    public void setCrsID(int crsID) {
        CrsID = crsID;
    }

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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Course getCourse(){
        return course;
    }
    
    public void setCourse(Course course){
        this.course = course;
    }

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }
}