package org.example.service;

import org.example.response.ClassResponse;

import java.util.List;

public interface ClassService {

    List<ClassResponse> getClasses();

    void addClass(ClassResponse itClass, Long id);

    void deleteClass(Long id);

    void updateClass(ClassResponse itClass, Long id);
}
