package org.example.repository.mapper;

import org.example.model.University;
import org.example.repository.mapper.impl.UniversityResultSetMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UniversityResultSetMapperImplTest extends Mockito {
    
    private UniversityResultSetMapperImpl mapper;
    private ResultSet resultSet;
    private University actualUniversity = new University();

    @BeforeEach
    void init() {
        mapper = new UniversityResultSetMapperImpl();
        resultSet = mock(ResultSet.class);
        actualUniversity.setId(1);
        actualUniversity.setName("Test");
        actualUniversity.setCity("Test");
        actualUniversity.setCountry("Test");
    }

    @Test
    void mapTest() throws SQLException {

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(1);
        when(resultSet.getString(2)).thenReturn("Test");
        when(resultSet.getString(3)).thenReturn("Test");
        when(resultSet.getString(4)).thenReturn("Test");

        University expected = mapper.map(resultSet);

        assertEquals (expected, actualUniversity);
    }
}
