package application;

import adapter.persistence.InMemoryStudentRepository;
import application.student.StudentService;
import domain.student.StudentID;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    @Test
    public void testAddStudent() {
        var studentIDString = UUID.randomUUID().toString();
        var studentRepository = new InMemoryStudentRepository();
        var studentService = new StudentService(studentRepository);
        var student = studentRepository.studentOfID(new StudentID(UUID.fromString(studentIDString)));
        assertSame(student, Optional.empty());
        studentService.addStudent(studentIDString, "tom@email.com", "tom", "el", "bois");
        var student1 = studentRepository.studentOfID(new StudentID(UUID.fromString(studentIDString)));
        assertNotNull(student1.get());
    }

    @Test
    public void testRequestStudent() {
        var studentIDString = UUID.randomUUID().toString();
        var studentRepository = new InMemoryStudentRepository();
        var studentService = new StudentService(studentRepository);
        studentService.addStudent(studentIDString, "tom@email.com", "tom", "el", "bois");
        var student = studentService.requestStudent(studentIDString);

        assertNotSame(student, Optional.empty());
    }

}
