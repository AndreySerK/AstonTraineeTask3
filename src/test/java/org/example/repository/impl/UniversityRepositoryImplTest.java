package org.example.repository.impl;

import org.example.db.ConnectionManager;
import org.example.model.University;
import org.example.repository.UniversityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.DriverManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
public class UniversityRepositoryImplTest {

    UniversityRepository universityRepository;

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
        universityRepository = new UniversityRepositoryImpl(connectionManager);
    }


    @Test
    void findById() {
        University university = universityRepository.findById(1);

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
