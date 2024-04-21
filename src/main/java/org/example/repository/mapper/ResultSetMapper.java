package org.example.repository.mapper;

import org.example.model.Course;

import java.sql.ResultSet;
import java.util.List;

public interface ResultSetMapper<T> {
    T map(ResultSet resultSet);
    List<T> listMap(ResultSet resultSet);
}
