package org.example.servlet.mapper;

import org.example.model.Course;
import org.example.model.Student;
import org.example.servlet.dto.course.CourseForUniversityDto;
import org.example.servlet.mapper.course.CourseListMapper;
import org.example.servlet.mapper.course.CourseListMapperImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CourseListMapperImplTest {

    private CourseListMapper courseListMapper = new CourseListMapperImpl();
    private List<Student> studentList = new ArrayList<>();
    private Course course = new Course(
            1,
            "Test",
            2020,
            studentList,
            1
    );

    private CourseForUniversityDto courseForUniversityDto = new CourseForUniversityDto(
            "Test",
            2020
    );

    @Test
    void toDtoListTest_whenGetCourseList_shouldReturnCourseDtoList() {
        List<Course> courseList = new ArrayList<>();
        courseList.add(course);

        List<CourseForUniversityDto> expectedList = new ArrayList<>();
        expectedList.add(courseForUniversityDto);

        List<CourseForUniversityDto> actualDtoList = courseListMapper.toDtoList(courseList);

        assertEquals(expectedList.size(), actualDtoList.size());
        assertEquals(expectedList.get(0).getCourseName(), actualDtoList.get(0).getCourseName());
        assertEquals(expectedList.get(0).getStudyYear(), actualDtoList.get(0).getStudyYear());
    }
}
