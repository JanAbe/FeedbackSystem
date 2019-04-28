package education.adapter.persistence;

import common.DatabaseConfig;
import common.JDBCRepository;
import common.Mapper;
import education.domain.Email;
import education.domain.FullName;
import education.domain.Person;
import education.domain.student.Student;
import education.domain.student.StudentID;
import education.domain.student.StudentRepository;
import education.domain.university.UniversityID;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
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

    // TODO: clean up this code, don't like the double try/catch blocks
    @Override
    public Optional<Student> studentOfID(StudentID studentID) {
        final var query = "SELECT * FROM Student WHERE id=?";
        Optional<Student> student = Optional.empty();

        try {
            PreparedStatement statement = this.connection().prepareStatement(query);
            statement.setString(1, studentID.id());

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                var mapper = new Mapper<>(Student.class, resultSet);

                try {
                    student = Optional.of(mapper.create());
                } catch (Exception e) {
                    LOGGER.log(Level.INFO, "Exception occurred while mapping resultSet to Student: ", e);
                }
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Exception occurred while creating a statement: ", e);
        }

        return student;
    }

    @Override
    public Collection<Student> studentsOfUniversity(UniversityID universityID) {
        return null;
    }
}
