package org.example.repository.mapper.impl;

import org.example.model.Student;
import org.example.repository.mapper.ResultSetMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentResultSetMapperImpl implements ResultSetMapper<Student> {
    @Override
    public Student map(ResultSet resultSet) {
        Student student = new Student();
        try {
            if (resultSet.next()) {
                student.setId(resultSet.getInt(1));
                student.setFirstName(resultSet.getString(2));
                student.setSecondName(resultSet.getString(3));
                student.setAge(resultSet.getInt(4));
                student.setFrom(resultSet.getString(5));
                student.setUniversityId(resultSet.getInt(6));
            } else {
                throw new RuntimeException("Result set is empty");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    @Override
    public List<Student> listMap(ResultSet resultSet) {
        List<Student> students = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt(1));
                student.setFirstName(resultSet.getString(2));
                student.setSecondName(resultSet.getString(3));
                student.setAge(resultSet.getInt(4));
                student.setFrom(resultSet.getString(5));
                student.setUniversityId(resultSet.getInt(6));
                students.add(student);
            }
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }
}
