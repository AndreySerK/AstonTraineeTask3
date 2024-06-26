package org.example.repository;

import jakarta.persistence.EntityNotFoundException;
import org.example.config.AppConfig;
import org.example.model.University;
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
public class UniversityRepositoryTest {

    @Autowired
    UniversityRepository universityRepository;

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
        University university = universityRepository
                .findById(1)
                .orElseThrow(EntityNotFoundException::new);

        assertEquals(1, university.getId());
    }

    @Test
    void deleteById() {
        universityRepository.deleteById(3);

        assertEquals(2, universityRepository.findAll().size());
    }

    @Test
    void findAll() {
        List<University> actualUniversities = universityRepository.findAll();

        assertEquals(2,actualUniversities.size());
    }

    @Test
    void save() {
        University university = new University();
        university.setName("Test");
        university.setCountry("Test");
        university.setCity("Test");

        universityRepository.save(university);

        assertEquals(3, universityRepository.findAll().size());
    }


}
