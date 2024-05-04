package org.example.repository;

import jakarta.persistence.EntityNotFoundException;
import org.example.config.AppConfig;
import org.example.model.Course;
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
class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

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
        Course course = courseRepository
                .findById(1)
                .orElseThrow(EntityNotFoundException::new);

        assertEquals(1, course.getId());
    }

    @Test
    void deleteById() {
        courseRepository.deleteById(1);

        assertEquals(5, courseRepository.findAll().size());
    }

    @Test
    void findAll() {
        List<Course> actualCourses = courseRepository.findAll();

        assertEquals(5, actualCourses.size());
    }

    @Test
    void save() {
        Course course = new Course();
        course.setCourseName("Test");
        course.setStudyYear(2020);
        course.setUniversityId(1);

        courseRepository.save(course);

        assertEquals(6, courseRepository.findAll().size());
    }
}