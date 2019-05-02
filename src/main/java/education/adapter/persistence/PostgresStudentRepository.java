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

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
