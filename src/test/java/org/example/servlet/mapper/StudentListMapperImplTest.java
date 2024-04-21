package org.example.servlet.mapper;

import org.example.model.Course;
import org.example.model.Student;
import org.example.servlet.dto.student.StudentDto;
import org.example.servlet.mapper.student.StudentListMapper;
import org.example.servlet.mapper.student.StudentListMapperImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentListMapperImplTest {

    private StudentListMapper studentListMapper = new StudentListMapperImpl();
    List<Course> courses = new ArrayList<>();
    private Student student = new Student(
            1,
            "Test",
            "Test",
            20,
            "Test",
            1,
            courses
    );

    private StudentDto studentDto = new StudentDto(
            "Test",
            "Test",
            20,
            "Test",
            1
    );

    @Test
    void toDtoListTest_whenGetStudentList_shouldReturnStudentDtoList() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);

        List<StudentDto> expectedList = new ArrayList<>();
        expectedList.add(studentDto);

        List<StudentDto> actualDtoList = studentListMapper.toDtoList(studentList);

        assertEquals(expectedList.size(), actualDtoList.size());
        assertEquals(expectedList.get(0).getFirstName(), actualDtoList.get(0).getFirstName());
        assertEquals(expectedList.get(0).getSecondName(), actualDtoList.get(0).getSecondName());
        assertEquals(expectedList.get(0).getFrom(), actualDtoList.get(0).getFrom());
        assertEquals(expectedList.get(0).getAge(), actualDtoList.get(0).getAge());
    }
}
