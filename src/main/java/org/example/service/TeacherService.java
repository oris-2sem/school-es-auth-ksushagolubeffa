package org.example.service;

import org.example.response.TeacherResponse;

import java.util.List;

public interface TeacherService {

    List<TeacherResponse> getTeachers();

    void addTeacher(TeacherResponse teacher);

    void deleteTeacher(Long id);

    void updateTeacher(TeacherResponse teacher, Long id);
}
