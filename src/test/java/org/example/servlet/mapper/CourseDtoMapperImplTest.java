package org.example.servlet.mapper;

import org.example.model.Course;
import org.example.model.Student;
import org.example.servlet.dto.course.CourseDto;
import org.example.servlet.dto.course.CourseForUniversityDto;
import org.example.servlet.dto.course.IncomingCourseDto;
import org.example.servlet.mapper.course.CourseDtoMapper;
import org.example.servlet.mapper.course.CourseDtoMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseDtoMapperImplTest {

    private CourseDtoMapper courseDtoMapper = new CourseDtoMapperImpl();
    private Course course;
    private CourseDto courseDto;
    private IncomingCourseDto incomingCourseDto;
    private CourseForUniversityDto courseForUniversityDto;
    private List<Student> studentList;

    @BeforeEach
    void setup() {
        studentList = new ArrayList<>();
        course = new Course(
                1,
                "Test",
                2020,
                studentList,
                1
        );

        courseDto = new CourseDto(
                "Test",
                2020,
                1,
                null
        );

        courseForUniversityDto = new CourseForUniversityDto(
                "Test",
                2020
        );

        incomingCourseDto = new IncomingCourseDto(
                "Test",
                2020,
                1
        );
    }


    @Test
    void toDtoTest_whenGetCourse_shouldReturnCourseDto() {
        CourseDto dto = courseDtoMapper.toDto(course);

        assertEquals(dto.getCourseName(), courseDto.getCourseName());
        assertEquals(dto.getStudyYear(), courseDto.getStudyYear());
        assertEquals(dto.getUniversityId(), courseDto.getUniversityId());
    }

    @Test
    void toUniversityDtoTest_whenGetCourse_shouldReturnCourseForUniversityDto() {
        CourseForUniversityDto dto = courseDtoMapper.toUniversityDto(course);

        assertEquals(dto.getCourseName(), courseForUniversityDto.getCourseName());
        assertEquals(dto.getStudyYear(), courseForUniversityDto.getStudyYear());
    }


    @Test
    void toEntityTest_whenGetIncomingCourseDto_shouldReturnCourse() {
        Course entity = courseDtoMapper.toEntity(incomingCourseDto);

        assertEquals(entity.getCourseName(), course.getCourseName());
        assertEquals(entity.getStudyYear(), course.getStudyYear());
        assertEquals(entity.getUniversityId(), course.getUniversityId());
    }
}