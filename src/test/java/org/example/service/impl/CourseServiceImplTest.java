package org.example.service.impl;

import org.example.config.AppConfig;
import org.example.model.Course;
import org.example.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CourseServiceImplTest extends Mockito {

    CourseRepository repository;

    Course course = new Course();

    @BeforeEach
    void setup() {
        repository = mock(CourseRepository.class);
        course.setId(1);
        course.setCourseName("Test");
        course.setStudyYear(2020);
        course.setUniversityId(1);
    }

    @Test
    void saveTest() {
        repository.save(course);

        verify(repository, times(1)).save(course);
    }

    @Test
    void findByIdTest() {
        when(repository.findById(3).orElseThrow()).thenReturn(course);

        Course expectedCourse = repository.findById(3).orElseThrow();

        verify(repository, times(1)).findById(3);
        assertEquals(expectedCourse, course);
    }

    @Test
    void deleteByIdTest() {
        repository.deleteById(1);

        verify(repository, times(1)).deleteById(1);
    }

    @Test
    void findAllTest() {
        List<Course> courses = new ArrayList<>();
        courses.add(course);
        when(repository.findAll()).thenReturn(courses);

        List<Course> expectedResult = repository.findAll();

        verify(repository, times(1)).findAll();
        assertEquals(1, expectedResult.size());
    }
}