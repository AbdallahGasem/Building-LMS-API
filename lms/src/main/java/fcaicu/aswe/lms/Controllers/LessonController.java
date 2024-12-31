package fcaicu.aswe.lms.Controllers;
import fcaicu.aswe.lms.Models.Lesson;
import fcaicu.aswe.lms.Services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonController {
    @Autowired
    private LessonService lessonService;

    @PostMapping
    public Lesson createLesson(@RequestBody Lesson lesson) {
        return lessonService.createLesson(lesson);
    }
    @PutMapping("/{lessonId}")
    public Lesson updateLesson(@PathVariable int lessonId, @RequestBody Lesson updatedLesson) {
        return lessonService.updateLesson(lessonId,updatedLesson);
    }
    @DeleteMapping("/{lessonId}")
    public String deleteLesson(@PathVariable int lessonId) {
        lessonService.deleteLesson(lessonId);
        return "Course deleted successfully!";
    }
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Lesson>>getLessonsByCourse(@PathVariable int courseId){
        List<Lesson> lessons = lessonService.getLessonsByCourse(courseId);
        return ResponseEntity.ok(lessons);
    }

    @GetMapping("/{lessonId}")
    public Lesson getLesson(@PathVariable int lessonId) {
        return lessonService.getLesson(lessonId);
    }
}
