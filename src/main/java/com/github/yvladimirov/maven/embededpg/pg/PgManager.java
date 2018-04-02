package com.github.yvladimirov.maven.embededpg.pg;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.apache.maven.project.MavenProject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * yvladimirov on 26.03.18.
 */
public class PgManager {
    private static final PgManager instance = new PgManager();
    private EmbeddedPostgres process;

    public static PgManager getInstance() {
        return instance;
    }

    public void start(int dbPort) {
        try {
            this.process = EmbeddedPostgres.builder().setPort(dbPort).start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        try {
            process.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void createDB(String dbName) {
        try (Connection connection = process.getPostgresDatabase().getConnection();
             Statement statement = connection.createStatement()) {
            connection.createStatement().execute("CREATE DATABASE " + dbName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void populateProperty(MavenProject project, String dbName) {
        Properties prop = project.getProperties();
        prop.setProperty("embededpg.jdbcurl", process.getJdbcUrl("postgres", dbName));
        prop.setProperty("embededpg.port", "" + process.getPort());
        prop.setProperty("embededpg.user", "postgres");

    }
}
