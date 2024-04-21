package org.example.service.impl;

import org.example.model.Student;
import org.example.repository.StudentRepository;
import org.example.repository.impl.StudentRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StudentServiceImplTest extends Mockito {

    StudentRepository repository;
    Student student = new Student();

    @BeforeEach
    void setup() {
        repository = mock(StudentRepositoryImpl.class);
        student.setId(1);
        student.setFirstName("Test");
        student.setSecondName("Test");
        student.setFrom("Test");
        student.setAge(20);
        student.setUniversityId(1);
    }

    @Test
    void saveTest() {
        repository.save(student);

        verify(repository, times(1)).save(student);
    }

    @Test
    void findByIdTest() {
        when(repository.findById(1)).thenReturn(student);

        Student expectedStudent = repository.findById(1);

        verify(repository, times(1)).findById(1);
        assertEquals(expectedStudent, student);
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
        List<Student> students = new ArrayList<>();
        students.add(student);
        when(repository.findAll()).thenReturn(students);

        List<Student> expectedResult = repository.findAll();

        verify(repository, times(1)).findAll();
        assertEquals(1, expectedResult.size());
    }
}
