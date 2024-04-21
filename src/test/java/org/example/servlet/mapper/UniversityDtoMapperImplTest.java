package org.example.servlet.mapper;

import org.example.model.Course;
import org.example.model.University;
import org.example.model.Student;
import org.example.servlet.dto.university.UniversityDto;
import org.example.servlet.dto.university.IncomingUniversityDto;
import org.example.servlet.mapper.university.UniversityDtoMapper;
import org.example.servlet.mapper.university.UniversityDtoMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UniversityDtoMapperImplTest {

    private UniversityDtoMapper universityDtoMapper = new UniversityDtoMapperImpl();
    private University university;
    private UniversityDto universityDto;
    private IncomingUniversityDto incomingUniversityDto;
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

        incomingUniversityDto = new IncomingUniversityDto(
                "Test",
                "Test",
                "Test"
        );
    }


    @Test
    void toDtoTest_whenGetUniversity_shouldReturnUniversityDto() {
        UniversityDto dto = universityDtoMapper.toDto(university);

        assertEquals(dto.getCity(), universityDto.getCity());
        assertEquals(dto.getCountry(), universityDto.getCountry());
        assertEquals(dto.getName(), universityDto.getName());
    }


    @Test
    void toEntityTest_whenGetIncomingUniversityDto_shouldReturnUniversity() {
        University entity = universityDtoMapper.toEntity(incomingUniversityDto);

        assertEquals(entity.getCity(), university.getCity());
        assertEquals(entity.getName(), university.getName());
        assertEquals(entity.getCountry(), university.getCountry());
    }
}
