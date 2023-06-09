package org.example.repository;

import org.example.model.Lesson;
import org.example.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Query("select l from Lesson l where :teacher = (select t from Teacher t JOIN t.timetables tt WHERE tt.id = :timetableId)")
    List<Lesson> findLessonsByTeacher(Teacher teacher);
}
