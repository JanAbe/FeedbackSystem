package adapter.persistence;

import domain.Email;
import domain.FullName;
import domain.Person;
import domain.student.Student;
import domain.student.StudentID;
import domain.student.StudentRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryStudentRepositoryTest {
    private StudentRepository studentRepository = new InMemoryStudentRepository();

    @Test
    public void testSaveAndFindAStudent() {
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
}
