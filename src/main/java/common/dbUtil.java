package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dbUtil {
    private final static Logger LOGGER = Logger.getLogger(dbUtil.class.getName());

    /**
     * <p>Make a preparedStatement and assign its values.</p>
     * @param connection Connection
     * @param query String
     * @param values String...
     * @return PreparedStatement
     */
    public static PreparedStatement prepareStatement(Connection connection,
                                               String query,
                                               String... values) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            assignValues(statement, values);
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Exception occurred while making a preparedStatement: ", e);
        }

        return statement;
    }

    /**
     * <p>Assign values to the preparedStatement.</p>
     * @param statement PreparedStatement
     * @param values String...
     * @throws SQLException e
     */
    private static void assignValues(PreparedStatement statement,
                              String... values) throws SQLException {
        for (int i = 1; i < values.length + 1; i++) {
            statement.setString(i, values[i-1]);
        }
    }
}
