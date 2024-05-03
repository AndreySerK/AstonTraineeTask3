package org.example.model;


import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    private int age;

    @Column(name = "from_city")
    private String from;

    @Column(name = "university_id")
    private int universityId;

    @ManyToMany(mappedBy = "students", cascade = CascadeType.REMOVE)
    private List<Course> courses;

    public Student() {
    }

    public Student(int id, String firstName, String secondName, int age, String from, int universityId, List<Course> courses) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.from = from;
        this.universityId = universityId;
        this.courses = courses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && age == student.age && universityId == student.universityId && Objects.equals(firstName, student.firstName) && Objects.equals(secondName, student.secondName) && Objects.equals(from, student.from);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, secondName, age, from, universityId);
    }
}
