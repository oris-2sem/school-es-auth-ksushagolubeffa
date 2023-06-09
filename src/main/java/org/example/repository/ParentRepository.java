package org.example.repository;

import org.example.model.Parent;
import org.example.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Long> {

    Parent getParentByStudent(Student student);
}
