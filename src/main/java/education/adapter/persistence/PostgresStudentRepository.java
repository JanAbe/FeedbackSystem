package education.adapter.persistence;

import common.DatabaseConfig;
import common.JDBCRepository;
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

    @Override
    public Optional<Student> studentOfID(StudentID studentID) {
        final var query = "SELECT * FROM Student WHERE id=?";
        Optional<Student> student = Optional.empty();
        try {
            PreparedStatement statement = this.connection().prepareStatement(query);
            statement.setString(1, studentID.id());

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                var id = resultSet.getString("id");
                var email = resultSet.getString("email");
                var firstName = resultSet.getString("firstname");
                var prefix = resultSet.getString("prefix");
                var lastName = resultSet.getString("lastname");
                var ref_university = resultSet.getString("ref_university");

                var person = new Person(new Email(email), new FullName(firstName, prefix, lastName));
                var sID = new StudentID(UUID.fromString(id));
                var uID = new UniversityID(UUID.fromString(ref_university));

                student = Optional.of(new Student(sID, person, uID));
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
