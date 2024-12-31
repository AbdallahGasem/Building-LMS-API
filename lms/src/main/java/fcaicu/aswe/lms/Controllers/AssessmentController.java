package fcaicu.aswe.lms.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import fcaicu.aswe.lms.Models.Assessment;
import fcaicu.aswe.lms.Models.Question;
import fcaicu.aswe.lms.Models.Submission;
import fcaicu.aswe.lms.Services.AssessmentService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class AssessmentController {
    
    @Autowired
    AssessmentService assServ;

    //-------------------------------------------------------[POST Data]---------------------------------------------------------//    

    // create an assessment
    @PostMapping("/assessments")
    public void addAssessment(@RequestBody Assessment assessment ) {
        assServ.addAssessment(assessment);
    }
    
    // create N Random Questions Quiz
    @PostMapping("/assessments/quiz/{qtype}/{N}")
    public void addRandqQuiz(@RequestBody Assessment assessment, @PathVariable int N, @PathVariable String qtype) {
        assServ.addRandqQuiz(assessment , N , assessment.getTopic(), qtype);
    }


    //-------------------------------------------------------[GET Data]----------------------------------------------------------//
    
    // get Assessment by id
    @GetMapping("/assessment/{assessID}")
    public Assessment getAssessByID(@PathVariable int assessID) {
        return assServ.getAssessbyID(assessID);
    }

    // get all Assessments of type(Assign\Quiz) assignment of a course
    @GetMapping("/assessment/{crsID}/{type}")
    public List<Assessment> getAssessmentsByTypeOfCrs(@PathVariable int crsID, @PathVariable String type) {
        return assServ.getAssessmentsByTypeOfCrs(crsID,type);
    }

    // get all Assessments of a crs
    @GetMapping("/assessment/course/{crsID}")
    public List<Assessment> getCrsAssessments(@PathVariable int crsID) {
        return assServ.getCrsAssessments(crsID);
    }
    
    // get all Assessments
    @GetMapping("/assessments") // not the 's' at the endpoint
    public List<Assessment> getAssessments() {
        return assServ.getAssessments();
    }
    
    
    // get all assignments  (for Analytics Purpose)
    @GetMapping("/assessments/assignments")
    public List<Assessment> getAllAssignments(){
        return assServ.getAllAssignments();
    }

    // get all Quizzes  (for Analytics Purpose)
    @GetMapping("/assessments/quizzes")
    public List<Assessment> getAllQuizzes(){
        return assServ.getAllQuizzes();
    }
    
    // get Questions of a Quiz
    @GetMapping("/assessment/quiz/{quizID}/qs")
    public List<Question> getQSsofQuiz(@PathVariable int quizID) {
        return assServ.getQSsofQuiz(quizID);
        
    }

    // get Assessment Submissions
    @GetMapping("/assessment/{assessID}/submissions")
    public List<Submission> getAssessmentSubmissions(@PathVariable int assessID) {
        return assServ.getAssessmentSubs(assessID);
    }
    

    //-------------------------------------------------------[PUT Data]---------------------------------------------------------//
    
    // adit an assessment
    @PutMapping("/assessment/{assessID}")
    public void editAssessment(@PathVariable int assessID , @RequestBody Assessment edAssessment){
        assServ.editAssessment(assessID, edAssessment);
    }

    //------------------------------------------------------[DELETE Data]-------------------------------------------------------//

    // DELETE Assessment by ID
    @DeleteMapping("/assessment/del/{assessID}")
    public void deleteAssessByID(@PathVariable int assessID){
        assServ.deleteAssessByID(assessID);
    }

    // DELETE All Assessements
    @DeleteMapping("/assessments")
    public void deleteAllAssessments(){
        assServ.deleteAll();
    }


}
