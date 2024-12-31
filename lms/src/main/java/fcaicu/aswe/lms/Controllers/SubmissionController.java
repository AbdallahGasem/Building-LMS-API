package fcaicu.aswe.lms.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import fcaicu.aswe.lms.Models.Submission;
import fcaicu.aswe.lms.Services.SubmissionService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class SubmissionController {
    
    @Autowired
    private SubmissionService subServ;
    

    //--------------------------------------------------------[Post Data]--------------------------------------------------------//

    // create a submission
    @PostMapping("/submissions") // consumes = {"application/json"}
    public Submission addSubmission(@RequestBody Submission submission) {
        return subServ.addSubmission(submission);
        
    }



    //--------------------------------------------------------[GET Data]---------------------------------------------------------//
    // get all submissions
    @GetMapping("/submissions")
    public List<Submission> getAllSubmissions(){
        return subServ.getSubmissions();
    }

    // get a submisson of a student of a certain assessment
    @GetMapping("/submission/{assessID}/{sid}")
    public Submission getSubmission(@PathVariable int assessID, @PathVariable int sid ) {

        return subServ.getSubmissionbyID(assessID, sid);
    }

    // get all submissions of an Assessment
    @GetMapping("/submission/assessment/{assessID}")
    public List<Submission> getAssessmentSubs(@PathVariable int assessID) {
        return subServ.getAssessmentSubs(assessID);
    }
    
    
    // get all submissions of a Student
    @GetMapping("/submission/student/{sid}")
    public List<Submission> getStudentSubs(@PathVariable int sid) {
        return subServ.getStudentSubs(sid);
    }

        

    //--------------------------------------------------------[DELETE Data]------------------------------------------------------//

    // Delete a specific Submission
    @DeleteMapping("/submission/del/{assessID}/{sid}")
    public void deleteSubByID(@PathVariable int assessID, @PathVariable int sid){
        subServ.deleteSubmission(assessID, sid);
    }
    
    // Delete all Assessment's Submissions
    @DeleteMapping("/submission/assessment/del/{assessID}")
    public void deleteAssessSubsByID(@PathVariable int assessID){
        subServ.deleteAssessSubs(assessID);
    }
    
    // Delete all student's Submissions
    @DeleteMapping("/submission/stud/del/{sid}")
    public void deleteStudSubsByID(@PathVariable int sid){
        subServ.deleteStudSubs(sid);
    }

    // delete all Submissions Table (USE WISELY!)
    @DeleteMapping("/submissions/del")  // note the s in the "submissions"!
    public void deleteAll(){
        subServ.deleteAll();
    }
    
}