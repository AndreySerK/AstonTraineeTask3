package org.example.servlet.dto.university;

import org.example.servlet.dto.course.CourseForUniversityDto;
import org.example.servlet.dto.student.StudentDto;

import java.util.List;

public class UniversityDto {

    private String name;
    private String city;
    private String country;
    private List<StudentDto> students;
    private List<CourseForUniversityDto> courses;

    public UniversityDto() {
    }

    public UniversityDto(String name, String city, String country, List<StudentDto> students, List<CourseForUniversityDto> courses) {
        this.name = name;
        this.city = city;
        this.country = country;
        this.students = students;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<StudentDto> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDto> students) {
        this.students = students;
    }

    public List<CourseForUniversityDto> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseForUniversityDto> courses) {
        this.courses = courses;
    }
}
