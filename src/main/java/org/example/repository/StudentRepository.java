package org.example.repository;

import org.example.model.Class;
import org.example.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findStudentsByItClass(Class itClass);
}
