package application.student;

import domain.Email;
import domain.FullName;
import domain.Person;
import domain.student.Student;
import domain.student.StudentID;
import domain.student.StudentRepository;
import domain.university.University;
import domain.university.UniversityID;
import domain.university.UniversityRepository;
import util.exceptions.EmptyOptionalException;
import util.validators.Validate;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentService {
    private StudentRepository studentRepository;
    private UniversityRepository universityRepository;
    private final static Logger LOGGER = Logger.getLogger(StudentService.class.getName());

    public StudentService(StudentRepository studentRepository,
                          UniversityRepository universityRepository) {

        this.setStudentRepository(studentRepository);
        this.setUniversityRepository(universityRepository);
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

    public void addStudent(String sID,
                           String email,
                           String firstName,
                           String prefix,
                           String lastName) {
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

    /**
     * <p>Enroll the student that corresponds to the studentID in
     * the university that corresponds to the universityID.</p>
     * @param uID String
     * @param sID String
     */
    public void enrollIntoUniversity(String sID, String uID) {
        try {
            var universityID = new UniversityID(UUID.fromString(uID));
            var university = this.universityRepository.universityOfID(universityID);
            Validate.notEmpty(university, "University that belongs to the provided universityID does not exist");

            var studentID = new StudentID(UUID.fromString(sID));
            var student = this.studentRepository.studentOfID(studentID);
            Validate.notEmpty(student, "Student that belongs to the provided studentID does not exist");

            var updatedStudent = student.get();
            updatedStudent.enrollIntoUniversity(universityID);
            this.studentRepository.save(updatedStudent);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception occurred while enrolling a student into university: ", e);
        }
    }

    // ---------- Private methods ----------

    private void setStudentRepository(StudentRepository studentRepository) {
        Validate.argumentNotNull(studentRepository, "Provided studentRepository can not be null");
        this.studentRepository = studentRepository;
    }

    private void setUniversityRepository(UniversityRepository universityRepository) {
        Validate.argumentNotNull(universityRepository, "Provided universityRepository can not be null");
        this.universityRepository = universityRepository;
    }
}
