package org.example.repository.impl;

import org.example.db.ConnectionManager;
import org.example.model.Student;
import org.example.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.DriverManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
public class StudentRepositoryImplTest {

    StudentRepository studentRepository;

    @Container
    public static final PostgreSQLContainer<?> container =
            new PostgreSQLContainer<>("postgres:15")
                    .withDatabaseName("test")
                    .withUsername("test")
                    .withInitScript("db-migration.sql")
                    .withPassword("test");

    @BeforeEach
    void setUp() {
        ConnectionManager connectionManager = () -> DriverManager.getConnection(
                container.getJdbcUrl(),
                container.getUsername(),
                container.getPassword()
        );
        studentRepository = new StudentRepositoryImpl(connectionManager);
    }


    @Test
    void findById() {
        Student student = studentRepository.findById(1);

        assertEquals(1, student.getId());
    }

    @Test
    void deleteById() {
        studentRepository.deleteById(1);

        assertEquals(7, studentRepository.findAll().size());
    }

    @Test
    void findAll() {
        List<Student> actualStudents = studentRepository.findAll();

        assertEquals(7,actualStudents.size());
    }

    @Test
    void save() {
        Student student = new Student();
        student.setFirstName("Test");
        student.setSecondName("Test");
        student.setFrom("Test");
        student.setAge(20);
        student.setUniversityId(1);

        studentRepository.save(student);

        assertEquals(8, studentRepository.findAll().size());
    }
}
