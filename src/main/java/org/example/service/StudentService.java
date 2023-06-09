package org.example.service;

import org.example.response.StudentResponse;

import java.util.List;

public interface StudentService {

    void addStudent(StudentResponse student, Long classId, Long parentId);

    void updateStudent(StudentResponse student, Long id);

    StudentResponse getStudent(Long id);

    List<StudentResponse> getStudentsByClass(Long id);

    void deleteStudent(Long id);

    List<StudentResponse> getAllStudents();

}
