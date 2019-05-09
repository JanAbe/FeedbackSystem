package education.adapter.persistence;

import common.DatabaseConfig;
import common.JDBCRepository;
import common.dbUtil;
import common.mappers.Mapper;
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
    private Mapper<Student> mapper = new StudentMapper();

    public PostgresStudentRepository(DatabaseConfig databaseConfig) {
        super(databaseConfig);
    }

    @Override
    public StudentID nextID() {
        return new StudentID(UUID.randomUUID());
    }

    @Override
    public void save(Student student) {
        final var insertQuery = "INSERT INTO Student(id, email, firstname, prefix, lastname, ref_university) VALUES(?, ?, ?, ?, ?, ?)";
        final var updateQuery = "UPDATE Student SET email=?, firstname=?, prefix=?, lastname=?, ref_university=? WHERE id=?";
        var currentStudent = this.studentOfID(student.id());

        if (currentStudent.isPresent()) {
            if (currentStudent.get().equals(student)) {
                return; // Nothing has changed, no reason to update
            }

            try {
                var statement = dbUtil.prepareStatement(this.connection(), updateQuery,
                        student.email().email(),
                        student.fullName().firstName(),
                        student.fullName().prefix(),
                        student.fullName().lastName(),
                        student.universityID().id(),
                        student.id().id());

                statement.executeUpdate();
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.INFO, "Exception occurred while updating a student: ", e);
            }
        } else {
            try {
                var statement = dbUtil.prepareStatement(this.connection(), insertQuery,
                        student.id().id(),
                        student.email().email(),
                        student.fullName().firstName(),
                        student.fullName().prefix(),
                        student.fullName().lastName(),
                        student.universityID().id());

                statement.executeUpdate();
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.INFO, "Exception occurred while inserting a student: ", e);
            }
        }
    }

    @Override
    public Optional<Student> studentOfID(StudentID studentID) {
        final var query = "SELECT id, email, firstname, prefix, lastname, ref_university FROM Student WHERE id=?";
        Optional<Student> student = Optional.empty();

        try {
            var statement = dbUtil.prepareStatement(this.connection(), query, studentID.id());
            var resultSet = statement.executeQuery();

            if (resultSet.next()) {
                student = Optional.of(this.mapper.createFrom(resultSet));
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
        final var query = "SELECT id, email, firstname, prefix, lastname, ref_university FROM Student WHERE ref_university=?";
        var students = new HashSet<Student>();

        try {
            var statement = dbUtil.prepareStatement(this.connection(), query, universityID.id());
            var resultSet = statement.executeQuery();

            while (resultSet.next()) {
                students.add(this.mapper.createFrom(resultSet));
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Exception occurred while mapping resultSet to Student: ", e);
        }

        return students;
    }

}
