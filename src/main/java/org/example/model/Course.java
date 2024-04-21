package org.example.model;

import java.util.List;
import java.util.Objects;

public class Course {
    private java.lang.Integer id;
    private String courseName;
    private int studyYear;
    private List<Student> students;
    private int universityId;

    public Course() {}

    public Course(java.lang.Integer id, String name, int studyYear, List<Student> students, int universityId) {
        this.id = id;
        this.courseName = name;
        this.studyYear = studyYear;
        this.students = students;
        this.universityId = universityId;
    }

    public java.lang.Integer getId() {
        return id;
    }

    public void setId(java.lang.Integer id) {
        this.id = id;
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

    public List<Student> getStudents() {
        return students;
    }

    public int getUniversityId() {
        return universityId;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return studyYear == course.studyYear && universityId == course.universityId && Objects.equals(id, course.id) && Objects.equals(courseName, course.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseName, studyYear, universityId);
    }
}
