package org.example.repository.impl;

import org.example.db.ConnectionManager;
import org.example.db.impl.ConnectionManagerImpl;
import org.example.model.Course;
import org.example.model.Student;
import org.example.model.University;
import org.example.repository.CourseRepository;
import org.example.repository.StudentRepository;
import org.example.repository.UniversityRepository;
import org.example.repository.mapper.ResultSetMapper;
import org.example.repository.mapper.impl.CourseResultSetMapperImpl;
import org.example.repository.mapper.impl.StudentResultSetMapperImpl;
import org.example.repository.mapper.impl.UniversityResultSetMapperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UniversityRepositoryImpl implements UniversityRepository {
    public static final String FIND_UNIVER_BY_ID =
            "SELECT * FROM universities WHERE id = ?";
    public static final String FIND_COURSES_BY_UNIVER_ID =
            "SELECT * FROM courses WHERE university_id = ?";
    public static final String FIND_STUDENTS_BY_UNIVER_ID =
            "SELECT * FROM students WHERE university_id = ?";
    public static final String DELETE_FROM_UNIVERS =
            "DELETE FROM universities WHERE id = ?";
    private ResultSetMapper<University> univerMapper = new UniversityResultSetMapperImpl();
    private ResultSetMapper<Course> courseMapper = new CourseResultSetMapperImpl();
    private ResultSetMapper<Student> studentMapper = new StudentResultSetMapperImpl();
    private CourseRepository courseRepository= new CourseRepositoryImpl(new ConnectionManagerImpl());
    private StudentRepository studentRepository = new StudentRepositoryImpl(new ConnectionManagerImpl());
    private ConnectionManager connectionManager;
    private ResultSet resultSet;
    private University university;

    public UniversityRepositoryImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public University findById(Integer id) {
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement st1 = conn.prepareStatement(FIND_UNIVER_BY_ID);
             PreparedStatement st2 = conn.prepareStatement(FIND_COURSES_BY_UNIVER_ID);
             PreparedStatement st3 = conn.prepareStatement(FIND_STUDENTS_BY_UNIVER_ID)) {
            st1.setInt(1, id);
            st2.setInt(1, id);
            st3.setInt(1, id);
            ResultSet rs1 = st1.executeQuery();
            ResultSet rs2 = st2.executeQuery();
            ResultSet rs3 = st3.executeQuery();
            university = univerMapper.map(rs1);
            university.setCourses(courseMapper.listMap(rs2));
            university.setStudents(studentMapper.listMap(rs3));
            return university;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement("DELETE FROM universities WHERE id=?")) {
            st.setInt(1, id);
            deleteCoursesByUniversityId(id);
            deleteStudentsByUniversityId(id);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteStudentsByUniversityId (int id) {
        List<Student> students = findById(id).getStudents();
        students.forEach(s-> studentRepository.deleteById(s.getId()));
    }
    private void deleteCoursesByUniversityId (int id) {
        List<Course> courses = findById(id).getCourses();
        courses.forEach(c-> courseRepository.deleteById(c.getId()));
    }


    @Override
    public List<University> findAll() {
        List<University> universities = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM universities")) {
            resultSet = preparedStatement.executeQuery();
            return univerMapper.listMap(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(University obj) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO universities (name, city, country) values (?,?,?)")) {
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setString(2, obj.getCity());
            preparedStatement.setString(3, obj.getCountry());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
