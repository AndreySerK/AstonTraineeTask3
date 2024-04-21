package org.example.service.impl;

import org.example.model.University;
import org.example.repository.UniversityRepository;
import org.example.repository.impl.UniversityRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UniversityServiceImplTest extends Mockito {

    UniversityRepository repository;
    University university = new University();

    @BeforeEach
    void setup() {
        repository = mock(UniversityRepositoryImpl.class);
        university.setId(1);
        university.setName("Test");
        university.setCity("Test");
        university.setCountry("Test");
    }

    @Test
    void saveTest() {
        repository.save(university);

        verify(repository, times(1)).save(university);
    }

    @Test
    void findByIdTest() {
        when(repository.findById(1)).thenReturn(university);

        University expectedUniversity = repository.findById(1);

        verify(repository, times(1)).findById(1);
        assertEquals(expectedUniversity, university);
    }

    @Test
    void deleteByIdTest() {
        when(repository.deleteById(1)).thenReturn(true);

        boolean expectedResult = repository.deleteById(1);

        verify(repository, times(1)).deleteById(1);
        assertTrue(expectedResult);
    }

    @Test
    void findAllTest() {
        List<University> universitys = new ArrayList<>();
        universitys.add(university);
        when(repository.findAll()).thenReturn(universitys);

        List<University> expectedResult = repository.findAll();

        verify(repository, times(1)).findAll();
        assertEquals(1, expectedResult.size());
    }
}
