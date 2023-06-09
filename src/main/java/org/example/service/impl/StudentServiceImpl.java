package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.model.Student;
import org.springframework.stereotype.Service;
import org.example.repository.ClassRepository;
import org.example.repository.ParentRepository;
import org.example.repository.StudentRepository;
import org.example.response.StudentResponse;
import org.example.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;
    private final ClassRepository classRepository;
    private final ParentRepository parentRepository;

    @Override
    public void addStudent(StudentResponse student, Long classId, Long parentId) {
        repository.save(Student
                .builder()
                .name(student.getName())
                .surname(student.getSurname())
                .parent(parentRepository.getById(parentId))
                .itClass(classRepository.getById(classId))
                .build());
    }

    @Override
    public void updateStudent(StudentResponse student, Long id) {
        Student oldStudent = repository.findById(id).orElse(null);
        if(oldStudent!=null) {
            oldStudent.setName(student.getName());
            oldStudent.setSurname(student.getSurname());
            repository.save(oldStudent);
        }
    }

    @Override
    public StudentResponse getStudent(Long id) {
        return toResponse.apply(repository.getById(id));
    }

    @Override
    public List<StudentResponse> getStudentsByClass(Long id) {
        List<StudentResponse> response = new ArrayList<>();
        for (Student student : repository.findStudentsByItClass(classRepository.getById(id))) {
            response.add(new StudentResponse(
                    student.getName(),
                    student.getSurname()));
        }
        return response;
    }

    @Override
    public void deleteStudent(Long id) {
        repository.delete(repository.getById(id));
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        List<StudentResponse> response = new ArrayList<>();
        for (Student student : repository.findAll()) {
            response.add(new StudentResponse(
                    student.getName(),
                    student.getSurname()));
        }
        return response;
    }

    private final Function<Student, StudentResponse> toResponse = ent -> {
        return new StudentResponse(ent.getName(), ent.getSurname());
    };
}
