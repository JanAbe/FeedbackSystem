package education.adapter.persistence;

import common.DatabaseConfig;
import common.JDBCRepository;
import common.Mapper;
import common.validators.Validate;
import education.domain.Email;
import education.domain.FullName;
import education.domain.Person;
import education.domain.student.Student;
import education.domain.student.StudentID;
import education.domain.student.StudentRepository;
import education.domain.university.UniversityID;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgresStudentRepository extends JDBCRepository implements StudentRepository {
    private final static Logger LOGGER = Logger.getLogger(PostgresStudentRepository.class.getName());

    public PostgresStudentRepository(DatabaseConfig databaseConfig) {
        super(databaseConfig);
    }

    @Override
    public StudentID nextID() {
        return new StudentID(UUID.randomUUID());
    }

    @Override
    public void save(Student student) {

    }

    // TODO: clean up this code
    @Override
    public Optional<Student> studentOfID(StudentID studentID) {
        final var query = "SELECT * FROM Student WHERE id=?";
        Optional<Student> student = Optional.empty();
        var columnToField = this.columnToField();

        try {
            var statement = this.prepareStatement(this.connection(), query, studentID.id());
            var resultSet = statement.executeQuery();
            var mapper = new Mapper<>(Student.class, columnToField);

            if (resultSet.next()) {
                student = Optional.of(mapper.create(resultSet));
            }

            resultSet.close();
            statement.close();
        } catch (SQLException | InstantiationException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
            LOGGER.log(Level.INFO, "Exception occurred while mapping resultSet to Student: ", e);
        }

        return student;
    }

    @Override
    public Collection<Student> studentsOfUniversity(UniversityID universityID) {
        final var query = "SELECT * FROM Student WHERE ref_university=?";
        var students = new HashSet<Student>();
        var columnToField = this.columnToField();

        try {
            var statement = this.prepareStatement(this.connection(), query, universityID.id());
            var resultSet = statement.executeQuery();
            var mapper = new Mapper<>(Student.class, columnToField);

            while (resultSet.next()) {
                students.add(mapper.create(resultSet));
            }

            resultSet.close();
            statement.close();
        } catch (SQLException | InstantiationException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
            LOGGER.log(Level.INFO, "Exception occurred while mapping resultSet to Student: ", e);
        }

        return students;
    }

    /**
     * <p>Return the mapping of column names to field names of the Student class.</p>
     * @return Map
     */
    private Map<String, String> columnToField() {
        return Map.of("id", "id",
                "email", "email",
                "firstname", "firstName",
                "prefix", "prefix",
                "lastname", "lastName",
                "ref_university", "universityID");
    }

    /**
     * <p>Make a preparedStatement and assign its values.</p>
     * @param connection Connection
     * @param query String
     * @param values String...
     * @return PreparedStatement
     */
    private PreparedStatement prepareStatement(Connection connection,
                                               String query,
                                               String... values) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            this.assignValues(statement, values);
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
    private void assignValues(PreparedStatement statement,
                              String... values) throws SQLException {
        for (int i = 1; i < values.length + 1; i++) {
            statement.setString(i, values[i-1]);
        }
    }
}
