package org.example.repository.mapper.impl;

import org.example.model.Course;
import org.example.repository.mapper.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseResultSetMapperImpl implements ResultSetMapper<Course> {

    @Override
    public Course map(ResultSet resultSet) {
        Course course = new Course();
        try {
            if (resultSet.next()) {
                course.setId(resultSet.getInt(1));
                course.setCourseName(resultSet.getString(2));
                course.setStudyYear(resultSet.getInt(3));
                course.setUniversityId(resultSet.getInt(4));
            } else {
                throw new RuntimeException("Result set is empty");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return course;
    }

    @Override
    public List<Course> listMap(ResultSet resultSet) {
        List<Course> courses = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getInt(1));
                course.setCourseName(resultSet.getString(2));
                course.setStudyYear(resultSet.getInt(3));
                course.setUniversityId(resultSet.getInt(4));
                courses.add(course);
            }
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
        return courses;
    }
}
