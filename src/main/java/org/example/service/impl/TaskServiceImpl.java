package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.model.Task;
import org.springframework.stereotype.Service;
import org.example.repository.LessonRepository;
import org.example.repository.StudentRepository;
import org.example.repository.TaskRepository;
import org.example.response.TaskResponse;
import org.example.service.TaskService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;

    @Override
    public List<TaskResponse> getTasksByStudent(Long id) {
        List<TaskResponse> response = new ArrayList<>();
        for (Task task : repository.findTasksByStudent(studentRepository.getById(id))) {
            response.add(new TaskResponse(
                    task.getComment(),
                    task.getDescription(),
                    task.getMark(),
                    task.getType()));
        }
        return response;
    }

    @Override
    public List<TaskResponse> getTasks() {
        List<TaskResponse> response = new ArrayList<>();
        for (Task task : repository.findAll()) {
            response.add(new TaskResponse(
                    task.getComment(),
                    task.getDescription(),
                    task.getMark(),
                    task.getType()));
        }
        return response;
    }

    @Override
    public void addTask(TaskResponse task, Long studentId, Long lessonId) {
        repository.save(Task
                .builder()
                .comment(task.getComment())
                .description(task.getDescription())
                .mark(task.getMark())
                .type(task.getType())
                .student(studentRepository.getById(studentId))
                .lesson(lessonRepository.getById(lessonId))
                .build());
    }

    @Override
    public void deleteTask(Long id) {
        repository.delete(repository.getById(id));
    }

    @Override
    public void updateTask(TaskResponse task, Long id) {
        Task oldTask = repository.findById(id).orElse(null);
        if(oldTask!=null) {
            oldTask.setComment(task.getComment());
            oldTask.setDescription(task.getDescription());
            oldTask.setMark(task.getMark());
            oldTask.setType(task.getType());
            repository.save(oldTask);
        }
    }
}
