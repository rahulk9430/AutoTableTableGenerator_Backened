package com.gpm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gpm.models.DayOfWeek;
import com.gpm.models.TimeTable;
import com.gpm.service.TimeTableService;

import jakarta.validation.Valid;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/timetable")
@CrossOrigin("*")
public class TimeTableController {

    @Autowired
    private TimeTableService timeTableService;

    // Create timetable endpoint
    @PostMapping("/create")
    public ResponseEntity<?> createTimeTable(@Valid @RequestBody TimeTable timeTable) {
        try {
            return ResponseEntity.ok(timeTableService.createTimeTable(timeTable));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // View timetable by teacher, day, and time
    @GetMapping("/by-teacher-day-time")
    public List<TimeTable> getTimetableByTeacherAndDayAndTime(
            @RequestParam String teacherName,
            @RequestParam DayOfWeek day,
            @RequestParam LocalTime time) {
        return timeTableService.getTimetableByTeacherAndDayAndTime(teacherName, day, time);
    }
    
    @GetMapping("/getAll")
    public List<TimeTable> getAllTimetables() {
        return timeTableService.getAllTimeTable();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TimeTable> updateTimeTable(@PathVariable("id") Long id, @RequestBody TimeTable updatedTimeTable) {
        try {
            // Call the service to update the timetable
            TimeTable updated = timeTableService.updateTimeTable(id, updatedTimeTable);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            // If the timetable is not found or any other exception, return 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TimeTable> getTimeTableById(@PathVariable Long id) {
        TimeTable timeTable = timeTableService.getTimeTableById(id);
        if (timeTable == null) {
            return ResponseEntity.notFound().build(); // Return 404 if not found
        }
        return ResponseEntity.ok(timeTable); // Return the timetable if found
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTimeTableById(@PathVariable Long id) {
        timeTableService.deleteTimeTableById(id);
        return ResponseEntity.ok("Success");
    }
    
    

    // Add other GET endpoints for searching by subject, semester, teacher, etc.
}
