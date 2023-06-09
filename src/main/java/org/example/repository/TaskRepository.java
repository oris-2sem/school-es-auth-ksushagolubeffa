package org.example.repository;

import org.example.model.Student;
import org.example.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {


    List<Task> findTasksByStudent(Student student);
}
