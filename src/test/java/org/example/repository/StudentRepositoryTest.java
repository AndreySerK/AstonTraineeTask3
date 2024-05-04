package org.example.repository;

import jakarta.persistence.EntityNotFoundException;
import org.example.config.AppConfig;
import org.example.model.Student;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
public class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Container
    public static final PostgreSQLContainer<?> container =
            new PostgreSQLContainer<>("postgres:15")
                    .withDatabaseName("test")
                    .withUsername("test")
                    .withPassword("test");

    @BeforeAll
    static void beforeAll() {
        container.start();
    }

    @AfterAll
    static void afterAll() {
        container.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("database.url", container::getJdbcUrl);
        registry.add("database.username", container::getUsername);
        registry.add("database.password", container::getPassword);
    }


    @Test
    void findById() {
        Student student = studentRepository
                .findById(1)
                .orElseThrow(EntityNotFoundException::new);

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
