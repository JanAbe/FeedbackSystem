package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class JDBCRepository {
    private DatabaseConfig config;
    private Connection connection;
    private final static Logger LOGGER = Logger.getLogger(JDBCRepository.class.getName());

    public JDBCRepository(DatabaseConfig config) {
        this.config = config;
        this.connect();
    }

    public Connection connection() {
        return this.connection;
    }

    private void connect() {
        try {
            this.connection = DriverManager.getConnection(config.connectionString(), config.username(), config.password());
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Exception occurred while connecting to database: ", e);
        }
    }
}
