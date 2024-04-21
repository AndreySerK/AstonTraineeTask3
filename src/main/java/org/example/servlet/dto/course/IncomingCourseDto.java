package org.example.servlet.dto.course;

public class IncomingCourseDto {
    private String courseName;
    private int studyYear;
    private int universityId;

    public IncomingCourseDto() {
    }

    public IncomingCourseDto(String courseName, int studyYear, int universityId) {
        this.courseName = courseName;
        this.studyYear = studyYear;
        this.universityId = universityId;
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
}
