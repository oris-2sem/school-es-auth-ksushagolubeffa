package org.example.service;

import org.example.response.TimetableResponse;

import java.util.List;

public interface TimetableService {

    List<TimetableResponse> getTimetableRecords();

    void addTimetableRecord(TimetableResponse timetable, Long teacherId, Long classId);

    void deleteTimetableRecord(Long id);

    void updateTimetableRecord(TimetableResponse timetable, Long id);
}
