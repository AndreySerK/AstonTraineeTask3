package org.example.repository.impl;

import org.example.db.ConnectionManager;
import org.example.db.impl.ConnectionManagerImpl;
import org.example.model.Student;
import org.example.repository.StudentRepository;
import org.example.repository.mapper.ResultSetMapper;
import org.example.repository.mapper.impl.StudentResultSetMapperImpl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {
    public static final String FIND_STUDENT_BY_ID =
            "SELECT * FROM students WHERE id = ?";
    public static final String DELETE_FROM_STUDENTS_COURSES =
            "DELETE FROM students_courses WHERE student_id = ?";
    public static final String DELETE_FROM_STUDENTS =
            "DELETE FROM students WHERE id = ?";
    private ResultSetMapper<Student> studentMapper = new StudentResultSetMapperImpl();
    private ConnectionManager connectionManager;
    private ResultSet resultSet;

    public StudentRepositoryImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Student findById(Integer id) {
        // Здесь используем try with resources
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement st1 = conn.prepareStatement(FIND_STUDENT_BY_ID)) {
            st1.setInt(1, id);
            ResultSet rs1 = st1.executeQuery();
            return studentMapper.map(rs1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        try (Connection conn = connectionManager.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement st1 = conn.prepareStatement(DELETE_FROM_STUDENTS_COURSES);
                 PreparedStatement st2 = conn.prepareStatement(DELETE_FROM_STUDENTS)) {
                st1.setInt(1, id);
                st2.setInt(1, id);
                st1.executeUpdate();
                st2.executeUpdate();
                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM students")) {
            resultSet = preparedStatement.executeQuery();
            return studentMapper.listMap(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Student student) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO students (first_name,second_name,age,from_city, university_id) values (?,?,?,?,?)")) {
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getSecondName());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setString(4, student.getFrom());
            preparedStatement.setInt(5, student.getUniversityId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
