package org.example.servlet.dto.student;

public class StudentDto {
    private String firstName;
    private String secondName;
    private int age;
    private String from;
    private int universityId;

    public StudentDto() {
    }

    public StudentDto(String firstName, String secondName, int age, String from, int universityId) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.from = from;
        this.universityId = universityId;
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
}
