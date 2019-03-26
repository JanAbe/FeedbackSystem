package adapter.persistence;

import domain.Email;
import domain.FullName;
import domain.Person;
import domain.student.Student;
import domain.student.StudentID;
import domain.student.StudentRepository;
import domain.university.Country;
import domain.university.University;
import domain.university.UniversityID;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

public class InMemoryStudentRepositoryTest {
    private StudentRepository studentRepository;

    @Test
    public void testNextID() {
        this.studentRepository = new InMemoryStudentRepository();
        var studentID = studentRepository.nextID();

        assertNotNull(studentID);
    }

    @Test
    public void testSaveAndFindAStudent() {
        this.studentRepository = new InMemoryStudentRepository();

        var uuid = UUID.randomUUID();
        var studentID = new StudentID(uuid);
        var email = new Email("tom@email.com");
        var fullName = new FullName("tom", "le", "fromage");
        var person = new Person(email, fullName);
        var student = new Student(studentID, person);

        studentRepository.save(student);
        Optional<Student> foundStudent = studentRepository.studentOfID(studentID);

        assertTrue(foundStudent.isPresent()); // check if student is found
        assertEquals(foundStudent.get(), student); // check if found student is the correct one
    }

    @Test
    public void testStudentsFromUniversity() {
        this.studentRepository = new InMemoryStudentRepository();

        var universityID = new UniversityID(UUID.randomUUID());
        var studentID1 = new StudentID(UUID.randomUUID());
        var studentID2 = new StudentID(UUID.randomUUID());
        var studentID3 = new StudentID(UUID.randomUUID());
        var email1 = new Email("student1@email.com");
        var email2 = new Email("student2@email.com");
        var email3 = new Email("student3@email.com");
        var fullname1 = new FullName("tom", "de", "fiets");
        var fullname2 = new FullName("bob", "kaas");
        var fullname3 = new FullName("test", "student");
        var person1 = new Person(email1, fullname1);
        var person2 = new Person(email2, fullname2);
        var person3 = new Person(email3, fullname3);
        var student1 = new Student(studentID1, person1, universityID);
        var student2 = new Student(studentID2, person2, universityID);
        var student3 = new Student(studentID3, person3);

        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);

        assertThat(studentRepository.studentsOfUniversity(universityID), hasSize(2));
    }
}
