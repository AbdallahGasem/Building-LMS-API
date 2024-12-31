package fcaicu.aswe.lms.Embedded;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SubmissionKey implements Serializable {
    private int AssessID;
    private int SID;

    public SubmissionKey() {}

    public SubmissionKey(int AssessID, int SID) {
        this.AssessID = AssessID;
        this.SID = SID;
    }

    // Getters and Setters
    public int getAssessID() {
        return AssessID;
    }

    public void setAssessID(int AssessID) {
        this.AssessID = AssessID;
    }

    public int getSID() {
        return SID;
    }

    public void setSID(int SID) {
        this.SID = SID;
    }

    //---------------------------------------------------------------------------------
    // Override equals() and hashCode() // for comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubmissionKey that = (SubmissionKey) o;
        return AssessID == that.AssessID && SID == that.SID;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(AssessID, SID);
    }
    //---------------------------------------------------------------------------------
}
