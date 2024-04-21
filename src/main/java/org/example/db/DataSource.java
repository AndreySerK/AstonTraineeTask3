package org.example.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSource {

    private static HikariConfig config = new HikariConfig(
            "/db.properties");
    public static HikariDataSource ds = new HikariDataSource(config);

    private DataSource() {}
}
