package fcaicu.aswe.lms.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fcaicu.aswe.lms.Models.Assessment;
import fcaicu.aswe.lms.Models.Question;
import fcaicu.aswe.lms.Repos.QuestionRepository;

@Service
public class QuestionService {
    
    @Autowired
    QuestionRepository qsRepo;
    
    //------------------------------------------------------[POST Data]----------------------------------------------------------//
    
    // create question
    public void addQs(Question qs){
        qsRepo.save(qs);
    }

    // edit a Question
    public void editQs(int qid, Question edQS){
        Question existingQs = getQsByID(qid);

        if (edQS.getType() != null) {
            existingQs.setType(edQS.getType());  
        }
        if (edQS.getTopic() != null) {
            existingQs.setTopic(edQS.getTopic());  
        }
        if (edQS.getChoices() != null) {
            existingQs.setChoices(edQS.getChoices());  
        }
        if (edQS.getCorrectAns() != null) {
            existingQs.setCorrectAns(edQS.getCorrectAns());  
        }
        if (edQS.getAssessment() != null) {
            existingQs.setAssessment(edQS.getAssessment());  
        }
        if (edQS.getQHeader() != null) {
            existingQs.setQHeader(edQS.getQHeader());  
        }

        qsRepo.save(existingQs);

    }
    
    //------------------------------------------------------[GET Data]-----------------------------------------------------------//
    
    // get qs by id
    public Question getQsByID(int qid){
        
        return qsRepo.findById(qid).orElseThrow(()-> 
        new RuntimeException("[getQsByID()] --> there is no Question with ID: "+ qid)
        );
        
    }
    
    // get the assessment of a qustion
    public Assessment getAssessmentofQs(int qid){
        
        Question qs = qsRepo.findById(qid).orElseThrow(()-> 
        new RuntimeException("[getQsByID()] --> there is no Question with ID: " + qid)
        );
        
        return qs.getAssessment();
    }

    // get questions by topic of a certain type	// query params???
    public List<Question> getQsByTopic(String topic){
        return qsRepo.getQsByTopic(topic);
    }

    // get correct ans of a qs
    public String getQsAns(int qid){

        Question qs = getQsByID(qid);
        return qs.getCorrectAns();

    }
    
    
    //------------------------------------------------------[DELETE Data]--------------------------------------------------------//
    
    
    // delete question by id
    public void deleteQsByID(int qid){

        qsRepo.findById(qid).orElseThrow( () ->
            new RuntimeException("[deleteQsByID] --> Question of ID: " + qid +" Does Not Exist!")
        );

        qsRepo.deleteById(qid);

    }

    // delete all questions of a certain Topic
    public void deleteQSsByTopic(String topic){
        qsRepo.deleteQSsByTopic(topic);
    }

    // delete all questions
    public void deleteAll(){
        qsRepo.deleteAll();
    }

}
