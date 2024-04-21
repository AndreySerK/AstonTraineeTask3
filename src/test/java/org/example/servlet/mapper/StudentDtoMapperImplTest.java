package org.example.servlet.mapper;

import org.example.model.Course;
import org.example.model.Student;
import org.example.servlet.dto.student.StudentDto;
import org.example.servlet.mapper.student.StudentDtoMapper;
import org.example.servlet.mapper.student.StudentDtoMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentDtoMapperImplTest {

    private StudentDtoMapper studentDtoMapper = new StudentDtoMapperImpl();
    private Student student;
    private StudentDto studentDto;

    @BeforeEach
    void setup() {
        List<Course> courses = new ArrayList<>();
        student = new Student(
                1,
                "Test",
                "Test",
                20,
                "Test",
                1,
                courses
        );

        studentDto = new StudentDto(
                "Test",
                "Test",
                20,
                "Test",
                1
        );
    }


    @Test
    void toDtoTest_whenGetStudent_shouldReturnStudentDto() {
        StudentDto dto = studentDtoMapper.toDto(student);

        assertEquals(dto.getFirstName(), studentDto.getFirstName());
        assertEquals(dto.getSecondName(), studentDto.getSecondName());
        assertEquals(dto.getAge(), studentDto.getAge());
        assertEquals(dto.getFrom(), studentDto.getFrom());
        assertEquals(dto.getUniversityId(), studentDto.getUniversityId());
    }


    @Test
    void toEntityTest_whenGetStudentDto_shouldReturnStudent() {
        Student entity = studentDtoMapper.toEntity(studentDto);

        assertEquals(entity.getFirstName(), student.getFirstName());
        assertEquals(entity.getSecondName(), student.getSecondName());
        assertEquals(entity.getAge(), student.getAge());
        assertEquals(entity.getFrom(), student.getFrom());
        assertEquals(entity.getUniversityId(), student.getUniversityId());
    }
}
