package fcaicu.aswe.lms.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int QID;
    private String Type;
    private String QHeader;
    private String Choices;
    private String CorrectAns;
    private String Topic;

    /*
    "mappedBy" Attribute in `@OneToMany` Relationship
    The `mappedBy` attribute in the `@OneToMany` annotation is used to specify the field in the child entity that owns 
    the relationship. It indicates that the foreign key is managed by the child entity and not by the parent entity.
    mappedBy = "assessment"`**: This indicates that the `questions` list in the `Assessment` entity is mapped by the `assessment` 
    field in the `Question` entity. It means that the `Question` entity owns the foreign key relationship.

    
    @JoinColumn` Annotation
    The `@JoinColumn` annotation is used to specify the foreign key column in the child entity that references the primary key 
    in the parent entity. It is used in the `@ManyToOne` relationship to define the foreign key column.
    @JoinColumn(name = "AssessID")`**: This specifies that the `AssessID` column in the `Question` table is the foreign key 
    that references the primary key in the `Assessment` table.
    
    nullable = false , This indicates that the `AssessID` column cannot be null, meaning that every `Question`
    must be associated with an `Assessment`.

    Summary

    `mappedBy` Attribute**: Used in the parent entity (`@OneToMany` side) to specify the field in the child entity that owns the relationship. It indicates that the foreign key is managed by the child entity.
    `@JoinColumn` Annotation**: Used in the child entity (`@ManyToOne` side) to specify the foreign key column that references the primary key in the parent entity. The name attribute specifies the column name, and nullable specifies whether the column can be null.

    These annotations together ensure that the relationship between `Assessment` and `Question` is properly mapped and managed in the JPA context.
    */
    @ManyToOne
    @JoinColumn(name = "AssessID", nullable = true)
    private Assessment assessment;

    public int getQID() {
        return QID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getQHeader() {
        return QHeader;
    }

    public void setQHeader(String qHeader) {
        QHeader = qHeader;
    }

    public String getChoices() {
        return Choices;
    }

    public void setChoices(String choices) {
        Choices = choices;
    }

    public String getCorrectAns() {
        return CorrectAns;
    }

    public void setCorrectAns(String correctAns) {
        CorrectAns = correctAns;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public Assessment getAssessment() {
        return assessment;
    }

    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }
}