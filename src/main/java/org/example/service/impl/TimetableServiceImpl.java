package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.model.Timetable;
import org.springframework.stereotype.Service;
import org.example.repository.ClassRepository;
import org.example.repository.TeacherRepository;
import org.example.repository.TimetableRepository;
import org.example.response.TimetableResponse;
import org.example.service.TimetableService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimetableServiceImpl implements TimetableService {

    private final TimetableRepository repository;
    private final ClassRepository classRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public List<TimetableResponse> getTimetableRecords() {
        List<TimetableResponse> response = new ArrayList<>();
        for (Timetable timetable : repository.findAll()) {
            response.add(new TimetableResponse(
                    timetable.getDate(),
                    timetable.getCabinet(),
                    timetable.getTeacher().getSubject()));
        }
        return response;
    }

    @Override
    public void addTimetableRecord(TimetableResponse timetable, Long teacherId, Long classId) {
        repository.save(Timetable
                .builder()
                .cabinet(timetable.getCabinet())
                .date(timetable.getDate())
                .teacher(teacherRepository.getById(teacherId))
                .schoolClass(classRepository.getById(classId))
                .build());
    }

    @Override
    public void deleteTimetableRecord(Long id) {
        repository.delete(repository.getById(id));
    }

    @Override
    public void updateTimetableRecord(TimetableResponse timetable, Long id) {
        Timetable oldTimetable = repository.findById(id).orElse(null);
        if(oldTimetable!=null) {
            oldTimetable.getTeacher().setSubject(timetable.getSubject());
            oldTimetable.setDate(timetable.getDate());
            oldTimetable.setCabinet(timetable.getCabinet());
            repository.save(oldTimetable);
        }
    }
}
