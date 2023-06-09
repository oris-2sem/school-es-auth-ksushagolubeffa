package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.model.Class;
import org.springframework.stereotype.Service;
import org.example.repository.ClassRepository;
import org.example.repository.TeacherRepository;
import org.example.response.ClassResponse;
import org.example.service.ClassService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {

    private final ClassRepository repository;
    private final TeacherRepository teacherRepository;

    @Override
    public List<ClassResponse> getClasses() {
        List<ClassResponse> response = new ArrayList<>();
        for (Class itClass : repository.findAll()) {
            response.add(new ClassResponse(
                    itClass.getDate(),
                    itClass.getLetter()));
        }
        return response;
    }

    @Override
    public void addClass(ClassResponse itClass, Long id) {
        repository.save(Class
                .builder()
                .date(itClass.getDate())
                .letter(itClass.getLetter())
                .teacher(teacherRepository.getById(id))
                .build());
    }

    @Override
    public void deleteClass(Long id) {
        repository.delete(repository.getById(id));
    }

    @Override
    public void updateClass(ClassResponse itClass, Long id) {
        Class oldClass = repository.getById(id);
        oldClass.setDate(itClass.getDate());
        oldClass.setLetter(itClass.getLetter());
        repository.save(oldClass);
    }
}
