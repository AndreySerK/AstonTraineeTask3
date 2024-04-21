package org.example.repository.impl;

import org.example.db.ConnectionManager;
import org.example.db.impl.ConnectionManagerImpl;
import org.example.model.Course;
import org.example.model.Student;
import org.example.repository.CourseRepository;
import org.example.repository.mapper.impl.CourseResultSetMapperImpl;
import org.example.repository.mapper.ResultSetMapper;
import org.example.repository.mapper.impl.StudentResultSetMapperImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CourseRepositoryImpl implements CourseRepository {
    public static final String FIND_COURSE_BY_ID =
            "SELECT * FROM courses WHERE id = ?";
    public static final String FIND_STUDENTS_BY_COURSE_ID =
            "SELECT students.* FROM students " +
                    "JOIN students_courses " +
                    "ON students.id = students_courses.student_id " +
                    "WHERE students_courses.course_id = ?";
    public static final String DELETE_FROM_STUDENTS_COURSES =
            "DELETE FROM students_courses WHERE course_id = ?";
    public static final String DELETE_FROM_COURSES =
            "DELETE FROM courses WHERE id = ?";
    private ResultSetMapper<Course> courseMapper = new CourseResultSetMapperImpl();
    private ResultSetMapper<Student> studentMapper = new StudentResultSetMapperImpl();
    private ConnectionManager connectionManager;
    private ResultSet resultSet;
    private Course course;

    public CourseRepositoryImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Course findById(Integer id) {
        // Здесь используем try with resources
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement st1 = conn.prepareStatement(FIND_COURSE_BY_ID);
             PreparedStatement st2 = conn.prepareStatement(FIND_STUDENTS_BY_COURSE_ID)) {
            st1.setInt(1, id);
            st2.setInt(1, id);
            ResultSet rs1 = st1.executeQuery();
            ResultSet rs2 = st2.executeQuery();
            course = courseMapper.map(rs1);
            course.setStudents(studentMapper.listMap(rs2));
            return course;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        try (Connection conn = connectionManager.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement st1 = conn.prepareStatement(DELETE_FROM_STUDENTS_COURSES);
                 PreparedStatement st2 = conn.prepareStatement(DELETE_FROM_COURSES)) {
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
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM courses")) {
            resultSet = preparedStatement.executeQuery();
            return courseMapper.listMap(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Course course) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO courses (course_name, study_year, university_id) values (?,?,?)")) {
            preparedStatement.setString(1, course.getCourseName());
            preparedStatement.setInt(2, course.getStudyYear());
            preparedStatement.setInt(3, course.getUniversityId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
