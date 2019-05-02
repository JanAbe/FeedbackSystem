package common.mappers;

import education.domain.Email;
import education.domain.FullName;
import education.domain.Person;
import education.domain.student.Student;
import education.domain.student.StudentID;
import education.domain.university.UniversityID;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentMapper implements Mapper<Student> {
    private final static Logger LOGGER = Logger.getLogger(StudentMapper.class.getName());

    // possible problems: what if a student doesn't have a prefix. This will throw a nullPointerException
    public Student createFrom(ResultSet rs) {
        Student student = null;

        try {
            var id = rs.getString("id");
            var email = rs.getString("email");
            var firstName = rs.getString("firstname");
            var prefix = rs.getString("prefix");
            var lastName = rs.getString("lastname");
            var ref_university = rs.getString("ref_university");

            var person = new Person(new Email(email), new FullName(firstName, prefix, lastName));
            var sID = new StudentID(UUID.fromString(id));
            var uID = new UniversityID(UUID.fromString(ref_university));

            student = new Student(sID, person, uID);
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Exception occurred while mapping a resultSet to a student: ", e);
        }

        return student;
    }
}
