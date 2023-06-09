package org.example.cotroller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.example.response.TimetableResponse;
import org.example.service.TimetableService;

import java.util.List;

@RestController
@RequestMapping("/timetable")
@RequiredArgsConstructor
public class TimetableController {
    private final TimetableService service;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<TimetableResponse>> getAll() {
        return ResponseEntity.ok(service.getTimetableRecords());
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @PostMapping
    public ResponseEntity<?> add(@RequestBody TimetableResponse timetableResponse, @RequestParam("idTeacher")Long idTeacher,
                                 @RequestParam("idClass")Long idClass) {
        service.addTimetableRecord(timetableResponse, idTeacher, idClass);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id) {
        service.deleteTimetableRecord(id);
        return ResponseEntity.accepted().build();
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(TimetableResponse timetableResponse, @PathVariable("id") Long id) {
        service.updateTimetableRecord(timetableResponse, id);
        return ResponseEntity.accepted().build();
    }
}
