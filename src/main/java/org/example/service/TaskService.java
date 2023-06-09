package org.example.service;

import org.example.response.TaskResponse;

import java.util.List;

public interface TaskService {

    List<TaskResponse> getTasksByStudent(Long id);

    List<TaskResponse> getTasks();

    void addTask(TaskResponse task, Long studentId, Long lessonId);

    void deleteTask(Long id);

    void updateTask(TaskResponse task, Long id);
}
