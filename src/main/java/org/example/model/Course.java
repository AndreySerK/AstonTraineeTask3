package org.example.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "study_year")
    private int studyYear;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "courses_students",
            joinColumns = {@JoinColumn (name = "course_id")},
            inverseJoinColumns = {@JoinColumn (name = "student_id")}
    )
    private List<Student> students = new ArrayList<>();

    @Column(name = "university_id")
    private int universityId;

    public Course() {}

    public Course(int id, String name, int studyYear, List<Student> students, int universityId) {
        this.id = id;
        this.courseName = name;
        this.studyYear = studyYear;
        this.students = students;
        this.universityId = universityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
