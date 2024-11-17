package com.gpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gpm.models.DayOfWeek;
import com.gpm.models.TimeTable;

import java.time.LocalTime;
import java.util.List;

public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {

    // Method to find overlapping periods for validation
    boolean existsByDayAndTimeAndClassroom(DayOfWeek day, LocalTime time, String classroom);

    // Method to validate unique time per teacher
    boolean existsByDayAndTimeAndTeacherName(DayOfWeek day, LocalTime time, String teacherName);

    // Method to find timetable by teacher, day, and time
    List<TimeTable> findByTeacherNameAndDayAndTime(String teacherName, DayOfWeek day, LocalTime time);

    // Other methods for searching by different combinations
    List<TimeTable> findByDayAndTime(DayOfWeek day, LocalTime time);

    List<TimeTable> findBySubjectName(String subjectName);

    List<TimeTable> findBySemesterName(String semesterName);

    List<TimeTable> findByTeacherName(String teacherName);
}
