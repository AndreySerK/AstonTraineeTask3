package org.example.repository.mapper;

import org.example.model.Student;
import org.example.repository.mapper.impl.StudentResultSetMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentResultSetMapperImplTest extends Mockito {

    private StudentResultSetMapperImpl mapper;
    private ResultSet resultSet;
    private Student actualStudent = new Student();

    @BeforeEach
    void init() {
        mapper = new StudentResultSetMapperImpl();
        resultSet = mock(ResultSet.class);
        actualStudent.setId(1);
        actualStudent.setFirstName("Test");
        actualStudent.setSecondName("Test");
        actualStudent.setAge(20);
        actualStudent.setFrom("Test");
        actualStudent.setUniversityId(1);
    }

    @Test
    void mapTest() throws SQLException {

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(1);
        when(resultSet.getString(2)).thenReturn("Test");
        when(resultSet.getString(3)).thenReturn("Test");
        when(resultSet.getInt(4)).thenReturn(20);
        when(resultSet.getString(5)).thenReturn("Test");
        when(resultSet.getInt(6)).thenReturn(1);

        Student expected = mapper.map(resultSet);

        assertEquals (expected, actualStudent);
    }
}
