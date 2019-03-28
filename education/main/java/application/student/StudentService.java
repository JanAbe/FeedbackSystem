package application.student;

import domain.Email;
import domain.FullName;
import domain.Person;
import domain.student.Student;
import domain.student.StudentID;
import domain.student.StudentRepository;
import domain.university.UniversityID;
import util.validators.Validate;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentService {
    private StudentRepository studentRepository;
    private final static Logger LOGGER = Logger.getLogger(StudentService.class.getName());

    public StudentService(StudentRepository studentRepository) {
        this.setStudentRepository(studentRepository);
    }

    // ---------- Public methods ----------

    public Optional<Student> requestStudent(String id) {
        Optional<Student> student = Optional.empty();

        try {
            var studentID = new StudentID(UUID.fromString(id));
            student = this.studentRepository.studentOfID(studentID);
        } catch (IllegalArgumentException iae) {
            LOGGER.log(Level.SEVERE, "Exception occurred while requesting a student: ", iae);
        }

        return student;
    }

    public void addStudent(String sID, String email,
                           String firstName, String prefix, String lastName) {
        try {
            var studentID = new StudentID(UUID.fromString(sID));
            var student = new Student(studentID,
                            new Person(
                                new Email(email),
                                new FullName(firstName, prefix, lastName)
                            ));
            this.studentRepository.save(student);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception occurred while requesting a student: ", e);
        }
    }

    public void enrollIntoUniversity(String uID, String sID) {
        // prolly should be a check to see if universityID exists?
        try {
            var universityID = new UniversityID(UUID.fromString(uID));
            var studentID = new StudentID(UUID.fromString(sID));
            var foundStudent = this.studentRepository.studentOfID(studentID);
            foundStudent.ifPresent(student -> student.enrollIntoUniversity(universityID));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception occurred while enrolling a student into university: ", e);
        }
    }

    // ---------- Private methods ----------

    private void setStudentRepository(StudentRepository studentRepository) {
        Validate.argumentNotNull(studentRepository,
                "Provided studentRepository can not be null");
        this.studentRepository = studentRepository;
    }
}
