package com.gpm.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpm.models.DayOfWeek;
import com.gpm.models.TimeTable;
import com.gpm.repository.TimeTableRepository;

import jakarta.validation.Valid;
import java.time.LocalTime;
import java.util.List;

@Service
public class TimeTableService {

    @Autowired
    private TimeTableRepository timeTableRepository;

    // Create a timetable with validations
    public TimeTable createTimeTable(@Valid TimeTable timeTable) throws Exception {
    	
    	if (timeTable.getDuration() == null) {
            timeTable.setDuration(1); // Default duration to 1 hour
        }
        
        // Validate day and time restrictions
        validateTimeTable(timeTable);

        // Save to database
        return timeTableRepository.save(timeTable);
    }

    // Validation method
    private void validateTimeTable(TimeTable timeTable) throws Exception {
        // Check if day is Monday to Saturday
        if (timeTable.getDay().getValue() > DayOfWeek.SATURDAY.getValue()) {
            throw new Exception("Invalid day: Only Monday to Saturday allowed.");
        }

        // Check time constraints
        LocalTime startTime = LocalTime.of(10, 0); // 10:00 AM
        LocalTime endTime = LocalTime.of(15, 0);   // 3:00 PM
        if (timeTable.getTime().isBefore(startTime) || timeTable.getTime().isAfter(endTime)) {
            throw new Exception("Invalid time: Only between 10 AM and 3 PM allowed.");
        }

        // Check overlapping periods for the same classroom
        if (timeTableRepository.existsByDayAndTimeAndClassroom(timeTable.getDay(), timeTable.getTime(), timeTable.getClassroom())) {
            throw new Exception("Time slot is already booked for this classroom.");
        }

        // Check if the teacher is available
        if (timeTableRepository.existsByDayAndTimeAndTeacherName(timeTable.getDay(), timeTable.getTime(), timeTable.getTeacherName())) {
            throw new Exception("Teacher is already assigned for this time slot.");
        }
    }

    // Additional methods to find timetable by teacher, day, time, etc.
    public List<TimeTable> getTimetableByTeacherAndDayAndTime(String teacherName, DayOfWeek day, LocalTime time) {
        return timeTableRepository.findByTeacherNameAndDayAndTime(teacherName, day, time);
    }

    public List<TimeTable> getTimetableByDayAndTime(DayOfWeek day, LocalTime time) {
        return timeTableRepository.findByDayAndTime(day, time);
    }

    public List<TimeTable> getTimetableBySubject(String subjectName) {
        return timeTableRepository.findBySubjectName(subjectName);
    }

    public List<TimeTable> getTimetableBySemester(String semesterName) {
        return timeTableRepository.findBySemesterName(semesterName);
    }

    public List<TimeTable> getTimetableByTeacher(String teacherName) {
        return timeTableRepository.findByTeacherName(teacherName);
    }
    
    
    public List<TimeTable> getAllTimeTable(){
    	return timeTableRepository.findAll();
    }	
    
    public TimeTable updateTimeTable(Long id, TimeTable updatedTimeTable) {
        // Check if the timetable with given ID exists
        TimeTable existingTimeTable = timeTableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TimeTable not found with id " + id));
        
        // Update the properties of the existing timetable with the new data
        existingTimeTable.setTeacherName(updatedTimeTable.getTeacherName());
        existingTimeTable.setSubjectName(updatedTimeTable.getSubjectName());
        existingTimeTable.setSemesterName(updatedTimeTable.getSemesterName());
        existingTimeTable.setDay(updatedTimeTable.getDay());
        existingTimeTable.setTime(updatedTimeTable.getTime());
        existingTimeTable.setClassroom(updatedTimeTable.getClassroom());
        existingTimeTable.setDuration(updatedTimeTable.getDuration());

        // Save the updated timetable back to the database
        return timeTableRepository.save(existingTimeTable);
    }
    
    public TimeTable getTimeTableById(Long id) {
        return timeTableRepository.findById(id).orElse(null); // Return null if not found
    }
    
    public void deleteTimeTableById(Long id) {
    	
    	  TimeTable existingTimeTable = timeTableRepository.findById(id)
                  .orElseThrow(() -> new RuntimeException("TimeTable not found with id " + id));
          
    	  this.timeTableRepository.delete(existingTimeTable);
    }
    
}
