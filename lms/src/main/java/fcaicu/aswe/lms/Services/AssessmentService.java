package fcaicu.aswe.lms.Services;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import fcaicu.aswe.lms.Models.Assessment;
import fcaicu.aswe.lms.Models.Course;
import fcaicu.aswe.lms.Models.Question;
import fcaicu.aswe.lms.Models.Submission;
import fcaicu.aswe.lms.Repos.AssessmentRepository;
import fcaicu.aswe.lms.Repos.CourseRepository;
import fcaicu.aswe.lms.Repos.QuestionRepository;

@Service
public class AssessmentService { 

    @Autowired
    private AssessmentRepository assessRepo;

    @Autowired
    private QuestionRepository qsRepo;

    @Autowired
    private CourseRepository crsRepo;
    
    //------------------------------------------------------[POST Data]----------------------------------------------------------//
    
    // create an Assessment
    public void addAssessment(Assessment assessment){   // assignment / filebased quiz


        Course course = crsRepo.findById(assessment.getCourse().getCrsID())
        .orElseThrow(() -> new RuntimeException("Course with ID " + assessment.getCourse().getCrsID() + " not found!"));

        // Set the Course object (ensures proper relationship setup)
        assessment.setCourse(course);
        assessRepo.save(assessment);
    }
    
    // Generate N random Question with each Api Call , N is a request param?!
    public void addRandqQuiz(Assessment assessment, int N, String topic, String qtype){

        // // 1) get the list of random questions by a topic
        // List<Question> questions = qsRepo.getQsByTopic(topic);

        // if (questions.isEmpty()) {
        //     throw new RuntimeException("There is no Questions of this Topic: "+ topic);
        // }

        // // 2) randomize the selection
        // Collections.shuffle(questions);

        // // 3) Select the N questions or fewer if there are not enough and Filter them by type : SA, MCQ, T/F...
        // List<Question> SelectedQSs =questions.subList(0, Math.min(N, questions.size()));

        // List<Question> FQQS = new ArrayList<>();    // Final Quiz Questions
        // for (Question question : SelectedQSs) {
        //     if (question.getType().equals(qtype)) {
        //         FQQS.add(question);
        //     }
            
        // }
        
        // // 4) set(Associate) the list questions to the passed assessment
        // assessment.setQuestions(FQQS); 

        // // 5) Associate the Course
        // Course course = crsRepo.findById(assessment.getCourse().getCrsID())
        // .orElseThrow(() -> new RuntimeException("Course with ID " + assessment.getCourse().getCrsID() + " not found!"));

        // // Set the Course object (ensures proper relationship setup)
        // assessment.setCourse(course);

        // // 6) save
        // assessRepo.save(assessment);
        //_________________________________________________________________________________________________________________________//
     
        // Fetch random questions by topic
        List<Question> questions = qsRepo.getQsByTopic(topic);
    
        if (questions.isEmpty()) {
            throw new RuntimeException("There are no Questions for this Topic: " + topic);
        }
    
        // Shuffle and filter questions
        Collections.shuffle(questions);
        List<Question> selectedQuestions = questions.stream()
            .filter(q -> q.getType().equals(qtype))
            .limit(N)
            .collect(Collectors.toList());
    
        if (selectedQuestions.isEmpty()) {
            throw new RuntimeException("No questions match the requested type: " + qtype);
        }
    
        // Associate the questions with the assessment
        for (Question question : selectedQuestions) {
            question.setAssessment(assessment); // Synchronize the relationship
        }
        assessment.setQuestions(selectedQuestions);
    
        // Associate the course with the assessment
        Course course = crsRepo.findById(assessment.getCourse().getCrsID())
            .orElseThrow(() -> new RuntimeException("Course with ID " + assessment.getCourse().getCrsID() + " not found!"));
        assessment.setCourse(course);
    
        // Save the assessment (questions will cascade if configured properly)
        assessRepo.save(assessment);
        
        
    }
   
    //------------------------------------------------------[GET Data]-----------------------------------------------------------//
   
    // get Assessment by id
    public Assessment getAssessbyID(int assessID){
        return assessRepo.findById(assessID).orElseThrow(() -> 
        new RuntimeException("\n [getAssessbyID()] --> there is no Assessment by this ID: " + assessID)
        );
    }

    // get all Assessments of a Certain type(Assign\Quiz) of a course
    public List<Assessment> getAssessmentsByTypeOfCrs(int crsID, String type ){
        
        
        // CHECK ([Type] IN ('Assign','Quiz')) validatation!
        if (!type.equalsIgnoreCase("Assign") && !type.equalsIgnoreCase("Quiz")) {
            throw new IllegalArgumentException("Invalid assessment type: " + type);
        }
        else if (type.toLowerCase().contains("assign")) {
            return assessRepo.getAssessmentsByTypeOfCrs(crsID , "Assign");
        }
        else {
            return assessRepo.getAssessmentsByTypeOfCrs(crsID , "Quiz");
        }
        
    }

    // get all Assessments of a Course
    public List<Assessment> getCrsAssessments(int crsID){
        return assessRepo.getCrsAssessments(crsID);
    }

    // get all Assessments
    public List<Assessment> getAssessments(){
       return assessRepo.findAll();
    }

    // get all Assignments
    public List<Assessment> getAllAssignments(){
        return assessRepo.getAllAssignments();
    }
    
    // get all Quizzes
    public List<Assessment> getAllQuizzes(){
        return assessRepo.getAllQuizzes();
    }

    // get Questions of a quiz
    public List<Question> getQSsofQuiz(int quizID){     // quizID == Assessment id
       
        // verfy that this id exists !
        Assessment quiz = assessRepo.findById(quizID).orElseThrow(() -> 
            new RuntimeException("\n [getQSsofQuiz()] --> there is no a quiz or an Assessment by this ID: " + quizID)
        );

        // verfiy that the AssessentID(quizID) lead to an actual Assessment of type Quiz!
        if (!quiz.getType().equals("Quiz")) {
            throw new RuntimeException("\n [] --> Assessment with ID: "+ quizID + " is not a Quiz!");
        }

        return quiz.getQuestions();
    }

    // get Assessment Submissions
    public List<Submission> getAssessmentSubs(int assessID){

        // verfy that this id exists !
        Assessment assessment = assessRepo.findById(assessID).orElseThrow(() -> 
            new RuntimeException("\n [getQSsofQuiz()] --> there is no a quiz or an Assessment by this ID: " + assessID)
        );

        return assessment.getSubmissions();  
    }

    //--------------------------------------------------------[PUT Data]---------------------------------------------------------//
    // Edit Assessment
    public void editAssessment(int assessID , Assessment edAssessment){
        Assessment existingAssessment = getAssessbyID(assessID);

        if (edAssessment.getAssessFILE() != null) {
            existingAssessment.setAssessFILE(edAssessment.getAssessFILE()); 
        }
        if (edAssessment.getCourse() != null) {
            existingAssessment.setCourse(edAssessment.getCourse()); 
        }
        if (edAssessment.getDeadline() != null) {
            existingAssessment.setDeadline(edAssessment.getDeadline()); 
        }
        if (edAssessment.getTopic() != null) {
            existingAssessment.setTopic(edAssessment.getTopic()); 
        }
        if (edAssessment.getType() != null) {
            existingAssessment.setType(edAssessment.getType()); 
        }
        if (edAssessment.getQuestions() != null) {
            existingAssessment.setQuestions(edAssessment.getQuestions()); 
        }
        if (edAssessment.getSubmissions() != null) {
            existingAssessment.setSubmissions(edAssessment.getSubmissions()); 
        }

        assessRepo.save(existingAssessment);
    }
    
    
    
    //-----------------------------------------------------[DELETE Data]---------------------------------------------------------//

    // delete an  assessment by id
    public void deleteAssessByID(int assessID){

        // handling no Existence of a certain assessment with assessID

        assessRepo.findById(assessID).orElseThrow(() ->
            new RuntimeException("[deleteAssessByID()] , No Assessemnt with ID: " + assessID + " found!")
        );

        assessRepo.deleteById(assessID);
    } 

    // delete all Assessemnt Records
    public void deleteAll(){
        assessRepo.deleteAll();
    }


}
