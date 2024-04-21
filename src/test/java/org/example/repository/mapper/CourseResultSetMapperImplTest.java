package org.example.repository.mapper;

import org.example.model.Course;
import org.example.repository.mapper.impl.CourseResultSetMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseResultSetMapperImplTest {

    private CourseResultSetMapperImpl mapper;
    private ResultSet resultSet;
    private Course actualCourse = new Course();

    @BeforeEach
    void init() {
        mapper = new CourseResultSetMapperImpl();
        resultSet = mock(ResultSet.class);
        actualCourse.setId(1);
        actualCourse.setCourseName("Test");
        actualCourse.setStudyYear(2020);
        actualCourse.setUniversityId(1);
    }

    @Test
    void mapTest() throws SQLException {

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(1);
        when(resultSet.getString(2)).thenReturn("Test");
        when(resultSet.getInt(3)).thenReturn(2020);
        when(resultSet.getInt(4)).thenReturn(1);

        Course expected = mapper.map(resultSet);

        assertEquals (expected, actualCourse);
    }
}