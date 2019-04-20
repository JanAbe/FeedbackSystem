package application;

import adapter.persistence.InMemoryStudentRepository;
import adapter.persistence.InMemoryUniversityRepository;
import application.student.StudentService;
import domain.student.StudentID;
import domain.university.Country;
import domain.university.University;
import domain.university.UniversityID;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    @Test
    public void testAddStudent() {
        var studentIDString = UUID.randomUUID().toString();
        var studentRepository = new InMemoryStudentRepository();
        var universityRepository = new InMemoryUniversityRepository();
        var studentService = new StudentService(studentRepository, universityRepository);
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
        var universityRepository = new InMemoryUniversityRepository();
        var studentService = new StudentService(studentRepository, universityRepository);

        studentService.addStudent(studentIDString, "tom@email.com", "tom", "el", "bois");
        var student = studentService.requestStudent(studentIDString);

        assertNotSame(student, Optional.empty());
    }

    @Test
    public void testEnrollStudentIntoUniversity() {
        var studentIDString = UUID.randomUUID().toString();
        var universityIDString = UUID.randomUUID().toString();
        var studentRepository = new InMemoryStudentRepository();
        var universityRepository = new InMemoryUniversityRepository();
        var studentService = new StudentService(studentRepository, universityRepository);

        studentService.addStudent(studentIDString, "tom@email.com", "tom", "el", "bois");
        universityRepository.save(new University(new UniversityID(UUID.fromString(universityIDString)), "Hogeschool Utrecht", Country.THE_NETHERLANDS));

        studentService.enrollIntoUniversity(studentIDString, universityIDString);

        var student = studentService.requestStudent(studentIDString);
        var university = universityRepository.universityOfID(new UniversityID(UUID.fromString(universityIDString)));

        assertEquals(student.get().universityID(), university.get().id());
    }

//     TODO: write tests that check if the enrollStudentIntoUniversity() method raises an error if the ids don't exist
//     Why does the following test not work? am i dumb?
//    @Test
//    public void testEnrollStudentIntoUniversityThrowsEOE() {
//        var studentIDString = UUID.randomUUID().toString();
//        var universityIDString = UUID.randomUUID().toString();
//        var studentRepository = new InMemoryStudentRepository();
//        var universityRepository = new InMemoryUniversityRepository();
//        var studentService = new StudentService(studentRepository, universityRepository);
//
//        studentService.addStudent(studentIDString, "tom@email.com", "tom", "el", "bois");
//        // universityRepository.save(new University(new UniversityID(UUID.fromString(universityIDString)), "Hogeschool Utrecht", Country.THE_NETHERLANDS));
//
//        assertThrows(EmptyOptionalException.class, () -> {
//            studentService.enrollIntoUniversity(studentIDString, universityIDString);
//        });
//    }
}
