package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.model.Lesson;
import org.springframework.stereotype.Service;
import org.example.repository.LessonRepository;
import org.example.repository.TeacherRepository;
import org.example.repository.TimetableRepository;
import org.example.response.LessonResponse;
import org.example.service.LessonService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository repository;
    private final TimetableRepository timetableRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public List<LessonResponse> getLessons() {
        List<LessonResponse> response = new ArrayList<>();
        for (Lesson lesson : repository.findAll()) {
            response.add(new LessonResponse(
                    lesson.getSubject(),
                    lesson.getTheme()));
        }
        return response;
    }

    @Override
    public List<LessonResponse> getLessonsByTeacher(Long id) {
        List<LessonResponse> response = new ArrayList<>();
        for (Lesson lesson : repository.findLessonsByTeacher(teacherRepository.getById(id))) {
            response.add(new LessonResponse(
                    lesson.getSubject(),
                    lesson.getTheme()));
        }
        return response;
    }

    @Override
    public void addLesson(LessonResponse lesson, Long id) {
        repository.save(Lesson.builder()
                .subject(lesson.getSubject())
                .theme(lesson.getTheme())
                .timetable(timetableRepository.getById(id))
                .build());
    }

    @Override
    public void deleteLesson(Long id) {
        repository.delete(repository.getById(id));
    }

    @Override
    public void updateLesson(LessonResponse lesson, Long id) {
        Lesson oldLesson = repository.findById(id).orElse(null);
        if(oldLesson!=null) {
            oldLesson.setSubject(lesson.getSubject());
            oldLesson.setTheme(lesson.getTheme());
            repository.save(oldLesson);
        }
    }
}
