package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.model.Teacher;
import org.springframework.stereotype.Service;
import org.example.repository.TeacherRepository;
import org.example.response.TeacherResponse;
import org.example.service.TeacherService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository repository;

    @Override
    public List<TeacherResponse> getTeachers() {
        List<TeacherResponse> response = new ArrayList<>();
        for (Teacher teacher : repository.findAll()) {
            response.add(new TeacherResponse(
                    teacher.getName(),
                    teacher.getSurname(),
                    teacher.getSubject()));
        }
        return response;
    }

    @Override
    public void addTeacher(TeacherResponse teacher) {
        repository.save(Teacher
                .builder()
                .name(teacher.getName())
                .surname(teacher.getSurname())
                .subject(teacher.getSubject())
                .build());
    }

    @Override
    public void deleteTeacher(Long id) {
        repository.delete(repository.getById(id));
    }

    @Override
    public void updateTeacher(TeacherResponse teacher, Long id) {
        Teacher oldTeacher = repository.findById(id).orElse(null);
        if(oldTeacher!=null) {
            oldTeacher.setSubject(teacher.getSubject());
            oldTeacher.setSurname(teacher.getSurname());
            oldTeacher.setName(teacher.getName());
            repository.save(oldTeacher);
        }
    }
}
