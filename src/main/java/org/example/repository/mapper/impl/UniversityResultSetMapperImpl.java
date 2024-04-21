package org.example.repository.mapper.impl;

import org.example.model.University;
import org.example.repository.mapper.ResultSetMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UniversityResultSetMapperImpl implements ResultSetMapper<University> {


    @Override
    public University map(ResultSet resultSet) {
        University univer = new University();
        try {
            if (resultSet.next()) {
                univer.setId(resultSet.getInt(1));
                univer.setName(resultSet.getString(2));
                univer.setCity(resultSet.getString(3));
                univer.setCountry(resultSet.getString(4));
            } else {
                throw new RuntimeException("Result set is empty");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return univer;
    }

    @Override
    public List<University> listMap(ResultSet resultSet) {
        List<University> univers = new ArrayList<>();
        try {
            while (resultSet.next()) {
                University univer = new University();
                univer.setId(resultSet.getInt(1));
                univer.setName(resultSet.getString(2));
                univer.setCity(resultSet.getString(3));
                univer.setCountry(resultSet.getString(4));
                univers.add(univer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return univers;
    }
}
