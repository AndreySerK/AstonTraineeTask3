package org.example.servlet.mapper;

import org.example.model.Course;
import org.example.model.Student;
import org.example.model.University;
import org.example.servlet.dto.student.StudentDto;
import org.example.servlet.dto.university.IncomingUniversityDto;
import org.example.servlet.dto.university.UniversityDto;
import org.example.servlet.mapper.university.UniversityDtoMapper;
import org.example.servlet.mapper.university.UniversityDtoMapperImpl;
import org.example.servlet.mapper.university.UniversityListMapper;
import org.example.servlet.mapper.university.UniversityListMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UniversityListDtoMapperImpl {

    private UniversityListMapper mapper = new UniversityListMapperImpl();

    private University university;
    private UniversityDto universityDto;
    private List<Student> students;
    private List<Course> courses;

    @BeforeEach
    void setup() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
        university = new University(
                1,
                "Test",
                "Test",
                "Test",
                students,
                courses
        );

        universityDto = new UniversityDto(
                "Test",
                "Test",
                "Test",
                null,
                null
        );

    }

    @Test
    void toDtoListTest_whenGetUniversityList_shouldReturnUniversityDtoList() {
        List<University> universities = new ArrayList<>();
        universities.add(university);

        List<UniversityDto> expectedList = new ArrayList<>();
        expectedList.add(universityDto);

        List<UniversityDto> actualDtoList = mapper.toDtoList(universities);

        assertEquals(expectedList.size(), actualDtoList.size());
        assertEquals(expectedList.get(0).getName(), actualDtoList.get(0).getName());
        assertEquals(expectedList.get(0).getCity(), actualDtoList.get(0).getCity());
        assertEquals(expectedList.get(0).getCountry(), actualDtoList.get(0).getCountry());
    }
}
