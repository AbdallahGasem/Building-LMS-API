package fcaicu.aswe.lms.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import fcaicu.aswe.lms.Models.Assessment;
import fcaicu.aswe.lms.Models.Question;
import fcaicu.aswe.lms.Services.QuestionService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;






@RestController
public class QuestionController {

    @Autowired
    QuestionService qsServ;
    //------------------------------------------------------[POST Data]----------------------------------------------------------//

    // create question
    @PostMapping("/questions")
    public void addQs(@RequestBody Question qs) {
        qsServ.addQs(qs);
    }
    

    //-------------------------------------------------------[GET Data]----------------------------------------------------------//
       
    // get qs by id
    @GetMapping("/questions/{qid}")
    public Question getQsByID( @PathVariable int qid ) {
        return qsServ.getQsByID(qid); 
    }
    
    // get the assessment of a qustion
    @GetMapping("/questions/{qid}/assessment")
    public Assessment getAssessmentofQs(@PathVariable int qid) {
        return qsServ.getAssessmentofQs(qid);
    }

    // get Questions by topic (filtering so use Query param not path variables)
    @GetMapping("/questions")
    public List<Question> getQsByTopic(@RequestParam String topic) {
        return qsServ.getQsByTopic(topic);
    }
    
    // get correct ans of a qs
    @GetMapping("/questions/{qid}/ans")
    public String getQsAns(@PathVariable int qid) {
        return qsServ.getQsAns(qid);
    }

    //-------------------------------------------------------[PUT Data]----------------------------------------------------------//

    // edit qs
    @PutMapping("/questions/{qid}")
    public void putMethodName(@PathVariable int qid, @RequestBody Question qs) {
        qsServ.editQs(qid,qs);
    }

    //------------------------------------------------------[DELETE Data]--------------------------------------------------------//
    
    // delete question by id
    @DeleteMapping("/questions/del/{qid}")
    public void deleteQsByID(@PathVariable int qid) {
        qsServ.deleteQsByID(qid);
    }
    
    // delete all questions of a certain Topic
    @DeleteMapping("/questions/del-topic/{topic}")
    public void deleteQSsByTopic(@PathVariable String topic) {
        qsServ.deleteQSsByTopic(topic);
    }

    // delete all questions
    @DeleteMapping("/questions/del")
    public void deleteAllQSs() {
        qsServ.deleteAll();
    }

    
    
}