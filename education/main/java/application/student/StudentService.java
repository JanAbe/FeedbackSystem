package application.student;

import domain.Email;
import domain.FullName;
import domain.Person;
import domain.student.Student;
import domain.student.StudentID;
import domain.student.StudentRepository;
import domain.university.UniversityID;
import domain.university.UniversityRepository;
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

    /**
     * <p>Request the student that belongs to the provided studentID.</p>
     * @param id String
     * @return Optional Student
     */
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

    /**
     * <p>Add a student to the system.</p>
     * @param sID String
     * @param email String
     * @param firstName String
     * @param prefix String
     * @param lastName String
     */
    public Student addStudent(String sID,
                           String email,
                           String firstName,
                           String prefix,
                           String lastName) {

        Student student = null;
        try {
            var studentID = new StudentID(UUID.fromString(sID));
            student = new Student(studentID,
                            new Person(
                                new Email(email),
                                new FullName(firstName, prefix, lastName)
                            ));
            this.studentRepository.save(student); // or do i need to set student after it has been saved, for when an error occurs while saving? Otherwise you might think it has been updated but it hasn't.
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception occurred while requesting a student: ", e);
        }

        return student;
    }

    /**
     * <p>Enroll the student that corresponds to the studentID in
     * the university that corresponds to the universityID.</p>
     * @param uID String
     * @param sID String
     */
    public Student enrollIntoUniversity(String sID, String uID) {
        Student updatedStudent = null;
        try {
            var universityID = new UniversityID(UUID.fromString(uID));
            var university = this.universityRepository.universityOfID(universityID);
            Validate.notEmpty(university, "University that belongs to the provided universityID does not exist");

            var studentID = new StudentID(UUID.fromString(sID));
            var student = this.studentRepository.studentOfID(studentID);
            Validate.notEmpty(student, "Student that belongs to the provided studentID does not exist");

            updatedStudent = student.get();
            updatedStudent.enrollIntoUniversity(universityID);
            this.studentRepository.save(updatedStudent); // or do i need to set updatedStudent after it has been saved, for when an error occurs while saving? Otherwise you might think it has been updated but it hasn't.
        } catch (Exception e) { // or should the try/catch blocks be placed not here, but in the resource classes like studentResource?
            LOGGER.log(Level.SEVERE, "Exception occurred while enrolling a student into university: ", e);
        }

        return updatedStudent;
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
