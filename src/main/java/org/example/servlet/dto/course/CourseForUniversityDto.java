package org.example.servlet.dto.course;


public class CourseForUniversityDto {

    private String courseName;
    private int studyYear;

    public CourseForUniversityDto() {
    }

    public CourseForUniversityDto(String courseName, int studyYear) {
        this.courseName = courseName;
        this.studyYear = studyYear;
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
}
