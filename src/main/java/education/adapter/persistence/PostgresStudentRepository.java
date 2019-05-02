package education.adapter.persistence;

import common.DatabaseConfig;
import common.JDBCRepository;
import common.dbUtil;
import common.mappers.StudentMapper;
import education.domain.student.Student;
import education.domain.student.StudentID;
import education.domain.student.StudentRepository;
import education.domain.university.UniversityID;

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

    // also want this method to function as an update
    @Override
    public void save(Student student) {
        final var query = "INSERT INTO Student(id, email, firstname, prefix, lastname, ref_university) VALUES(?, ?, ?, ?, ?, ?)";

        try {
            var statement = dbUtil.prepareStatement(this.connection(), query,
                    student.id().id(),
                    student.email().email(),
                    student.fullName().firstName(),
                    student.fullName().prefix(),
                    student.fullName().lastName(),
                    student.universityID().id());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Exception occurred while saving a student: ", e);
        }
    }

    @Override
    public Optional<Student> studentOfID(StudentID studentID) {
        final var query = "SELECT * FROM Student WHERE id=?";
        Optional<Student> student = Optional.empty();

        try {
            var statement = dbUtil.prepareStatement(this.connection(), query, studentID.id());
            var resultSet = statement.executeQuery();
            var mapper = new StudentMapper();

            if (resultSet.next()) {
                student = Optional.of(mapper.createFrom(resultSet));
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Exception occurred while getting a student based on ID: ", e);
        }

        return student;
    }

    @Override
    public Collection<Student> studentsOfUniversity(UniversityID universityID) {
        final var query = "SELECT * FROM Student WHERE ref_university=?";
        var students = new HashSet<Student>();

        try {
            var statement = dbUtil.prepareStatement(this.connection(), query, universityID.id());
            var resultSet = statement.executeQuery();
            var mapper = new StudentMapper();

            while (resultSet.next()) {
                students.add(mapper.createFrom(resultSet));
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Exception occurred while mapping resultSet to Student: ", e);
        }

        return students;
    }

}
