package org.example.db.impl;

import org.example.db.ConnectionManager;
import org.example.db.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManagerImpl implements ConnectionManager {

    @Override
    public Connection getConnection() throws SQLException {
        return DataSource.ds.getConnection();
    }
}
