package org.example.cotroller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.example.response.TeacherResponse;
import org.example.service.TeacherService;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService service;

    @PermitAll
    @GetMapping
    public ResponseEntity<List<TeacherResponse>> getAll() {
        return ResponseEntity.ok(service.getTeachers());
    }

    @PermitAll
    @PostMapping
    public ResponseEntity<?> add(@RequestBody TeacherResponse teacherResponse) {
        service.addTeacher(teacherResponse);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id) {
        service.deleteTeacher(id);
        return ResponseEntity.accepted().build();
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(TeacherResponse teacherResponse, @PathVariable("id")Long id) {
        service.updateTeacher(teacherResponse, id);
        return ResponseEntity.accepted().build();
    }
}
