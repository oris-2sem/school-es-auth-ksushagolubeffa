package org.example.service;

import org.example.response.LessonResponse;

import java.util.List;

public interface LessonService {

    List<LessonResponse> getLessons();

    List<LessonResponse> getLessonsByTeacher(Long id);

    void addLesson(LessonResponse lesson, Long id);

    void deleteLesson(Long id);

    void updateLesson(LessonResponse lesson, Long id);
}
