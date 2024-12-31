package fcaicu.aswe.lms.Services;
import fcaicu.aswe.lms.Models.Lesson;

import fcaicu.aswe.lms.Repos.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;

    public Lesson createLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }
    // Update an existing lesson
    public Lesson updateLesson(int lessonId, Lesson updatedLesson) {
        Optional<Lesson> existingLessonOpt = lessonRepository.findById(lessonId);
        if (existingLessonOpt.isEmpty()) {
            throw new RuntimeException("Lesson not found!");
        }

        Lesson existingLesson = existingLessonOpt.get();
        existingLesson.setTitle(updatedLesson.getTitle());
        existingLesson.setDescription(updatedLesson.getDescription());
        existingLesson.setMediaURL(updatedLesson.getMediaURL());

        return lessonRepository.save(existingLesson);
    }

    public void deleteLesson(int lessonId) {
        if (!lessonRepository.existsById(lessonId)) {
            throw new RuntimeException("Lesson not found!");
        }
        lessonRepository.deleteById(lessonId);
    }

    // Get all lessons for a course
    public List<Lesson> getLessonsByCourse(int courseId) {
        return lessonRepository.findByCourseId(courseId);
    }

    public Lesson getLesson(int lessonId) {
        return lessonRepository.findById(lessonId).orElse(null);
    }

}
