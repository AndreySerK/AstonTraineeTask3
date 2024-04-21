package org.example.servlet.dto.course;


import org.example.servlet.dto.student.StudentDto;

import java.util.List;

public class CourseDto {

    private String courseName;
    private int studyYear;
    private int universityId;
    private List<StudentDto> students;

    public CourseDto() {
    }

    public CourseDto(String courseName, int studyYear, int universityId, List<StudentDto> students) {
        this.courseName = courseName;
        this.studyYear = studyYear;
        this.universityId = universityId;
        this.students = students;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public List<StudentDto> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDto> students) {
        this.students = students;
    }
}
