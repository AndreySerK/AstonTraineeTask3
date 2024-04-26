package org.example.service.impl;

import org.example.model.University;
import org.example.repository.UniversityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UniversityServiceImplTest extends Mockito {

    UniversityRepository repository;
    University university = new University();

    @BeforeEach
    void setup() {
        repository = mock(UniversityRepository.class);
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
        when(repository.findById(2).orElseThrow()).thenReturn(university);

        University expectedUniversity = repository.findById(2).orElseThrow();

        verify(repository, times(1)).findById(2);
        assertEquals(expectedUniversity, university);
    }

    @Test
    void deleteByIdTest() {

        repository.deleteById(1);

        verify(repository, times(1)).deleteById(1);
    }

    @Test
    void findAllTest() {
        List<University> universities = new ArrayList<>();
        universities.add(university);
        when(repository.findAll()).thenReturn(universities);

        List<University> expectedResult = repository.findAll();

        verify(repository, times(1)).findAll();
        assertEquals(1, expectedResult.size());
    }
}
