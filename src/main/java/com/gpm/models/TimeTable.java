package com.gpm.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Teacher name is required.")
    private String teacherName;

    @NotBlank(message = "Subject name is required.")
    private String subjectName;

    @NotBlank(message = "Semester is required.")
    private String semesterName;

    @NotNull(message = "Day is required.")
    @Enumerated(EnumType.STRING)
    private DayOfWeek day;

    @NotNull(message = "Time is required.")
    private LocalTime time;

    @NotBlank(message = "Classroom is required.")
    private String classroom;
    
    
    private Integer duration; 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSemesterName() {
		return semesterName;
	}

	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
	}

	public DayOfWeek getDay() {
		return day;
	}

	public void setDay(DayOfWeek day) {
		this.day = day;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getClassroom() {
		return classroom;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}

	
	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	
	public TimeTable(Long id, @NotBlank(message = "Teacher name is required.") String teacherName,
			@NotBlank(message = "Subject name is required.") String subjectName,
			@NotBlank(message = "Semester is required.") String semesterName,
			@NotNull(message = "Day is required.") DayOfWeek day,
			@NotNull(message = "Time is required.") LocalTime time,
			@NotBlank(message = "Classroom is required.") String classroom, Integer duration) {
		super();
		this.id = id;
		this.teacherName = teacherName;
		this.subjectName = subjectName;
		this.semesterName = semesterName;
		this.day = day;
		this.time = time;
		this.classroom = classroom;
		this.duration = duration;
	}

	public TimeTable() {
		
	}

    
}
