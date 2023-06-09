package org.example.cotroller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.example.response.TaskResponse;
import org.example.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/getAll")
    public ResponseEntity<List<TaskResponse>> getAll() {
        return ResponseEntity.ok(service.getTasks());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/getByStudent/{id}")
    public ResponseEntity<List<TaskResponse>> getByStudent(@PathVariable("id")Long id) {
        return ResponseEntity.ok(service.getTasksByStudent(id));
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @PostMapping
    public ResponseEntity<?> add(@RequestBody TaskResponse taskResponse, @RequestParam("idStudent")Long idStudent,
                                 @RequestParam("idLesson")Long idLesson) {
        service.addTask(taskResponse, idStudent, idLesson);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id) {
        service.deleteTask(id);
        return ResponseEntity.accepted().build();
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(TaskResponse taskResponse, @PathVariable("id")Long id) {
        service.updateTask(taskResponse, id);
        return ResponseEntity.accepted().build();
    }
}
