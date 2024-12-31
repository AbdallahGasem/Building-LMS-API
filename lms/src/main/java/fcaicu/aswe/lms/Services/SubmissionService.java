package fcaicu.aswe.lms.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fcaicu.aswe.lms.Embedded.SubmissionKey;
import fcaicu.aswe.lms.Models.Submission;
import fcaicu.aswe.lms.Models.User;
import fcaicu.aswe.lms.Models.Assessment;
import fcaicu.aswe.lms.Repos.AssessmentRepository;
import fcaicu.aswe.lms.Repos.SubmissionRepository;
import fcaicu.aswe.lms.Repos.UserRepository;


@Service
public class SubmissionService {
    
    @Autowired
    private SubmissionRepository subRepo;
   
    @Autowired
    private AssessmentRepository assessRepo;
    
    @Autowired
    private UserRepository uRepo;
   
    //----------------------------------------------------[POST Methods]---------------------------------------------------------//
    
    // create a submission
    public Submission addSubmission(Submission submission){
        
        // ---------------------[Debuging]-----------------
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("SubmissionID: "+ submission.getSubmissionID().getAssessID() + ", " + submission.getSubmissionID().getSID());
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("assessID: "+ submission.getAssessment().getAssessID() );
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("SID: "+ submission.getStudent().getUID());
        System.out.println("-------------------------------------------------------------------------------");
        // ---------------------[Debuging]-----------------

        Assessment a = assessRepo.findById(submission.getAssessment().getAssessID())
        .orElseThrow(() -> new RuntimeException("Assessment with ID " + submission.getAssessment().getAssessID() + " not found!"));
        submission.setAssessment(a);

        User u = uRepo.findById(submission.getStudent().getUID()).orElseThrow(() -> new RuntimeException("Assessment with ID " + submission.getStudent().getUID() + " not found!"));
        submission.setStudent(u);
        
        return subRepo.save(submission);
    }
    
    //----------------------------------------------------[GET Methods]---------------------------------------------------------//
    public List<Submission> getSubmissions(){
        return subRepo.findAll();
    }
    
    public Submission getSubmissionbyID(int assessID, int sid){

        SubmissionKey id = new SubmissionKey(assessID, sid);
        return subRepo.findById(id).orElse(new Submission());   // returns an empty submission object if not exists!
    }

    public List<Submission> getAssessmentSubs(int assessID){
        
        return subRepo.findByAssessment(assessID);
    }

    public List<Submission> getStudentSubs( int sid){
        
        return subRepo.findByStudent(sid);
    }

    //----------------------------------------------------[PUT Methods]----------------------------------------------------------//

    // edit a Submission
    public void editSubmission(SubmissionKey subID, Submission edsubmission){
        Submission existingSub = getSubmissionbyID(subID.getAssessID(), subID.getSID());

        if (edsubmission.getStatus() != null) {
            existingSub.setStatus(edsubmission.getStatus());
        }
        if (edsubmission.getFeedBack() != null) {
            existingSub.setFeedBack(edsubmission.getFeedBack());
        }
        if (edsubmission.getFileURL() != null) {
            existingSub.setFileURL(edsubmission.getFileURL());
        }
        if (edsubmission.getScore() != null) {
            existingSub.setScore(edsubmission.getScore());
        }
        if (edsubmission.getStudent() != null) {
            existingSub.setStudent(edsubmission.getStudent());
        }
        if (edsubmission.getAssessment() != null) {
            existingSub.setAssessment(edsubmission.getAssessment());
        }

        
    }


    //---------------------------------------------------[DELETE Methods]--------------------------------------------------------//
    public boolean deleteSubmission(int assessID, int sid){

        SubmissionKey id = new SubmissionKey(assessID,sid); 
        
        // verfying existence before Deletion
        if(subRepo.existsById(id)) {
            subRepo.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

    // deleteAssessSubs helper
    // get all keys of a list of submissions 
    private List<SubmissionKey> getKeys(List<Submission> submissions){

        List<SubmissionKey> keys = new ArrayList<>();

        for (int i = 0; i < submissions.size(); i++) {
            keys.add(
                submissions.get(i).getSubmissionID()
            );
        }

        return keys;
    }

    public void deleteAssessSubs(int assessID){
        // 1) get the assessment submissions

        List<Submission> assessSubs = getAssessmentSubs(assessID);
        List<SubmissionKey> assessKeys = getKeys(assessSubs);
        
        // handling no submissions of a certain assessment
        if (assessKeys.isEmpty()) {
            throw new RuntimeException("\ndeleteAssessSubs(): No assessment keys found Assessment with key -> "+ assessID + " Do Not Have Submissions!\n");
        }

        subRepo.deleteAllById(assessKeys);

    }

    public void deleteStudSubs(int sid){

        List<Submission> studSubs = getStudentSubs(sid);
        List<SubmissionKey> studKeys = getKeys(studSubs);
        
        // handling no submissions of a certain assessment
        if (studKeys.isEmpty()) {
            throw new RuntimeException("\ndeleteStudSubs(): No Stud keys found Student with key -> "+ sid + " Do Not Have Submissions!\n");
        }

        subRepo.deleteAllById(studKeys);

    }

    public void deleteAll(){
        subRepo.deleteAll();
    }


}
