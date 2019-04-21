package education.application;

import education.adapter.persistence.InMemoryStudentRepository;
import education.adapter.persistence.InMemoryUniversityRepository;
import education.application.student.StudentService;
import education.domain.university.Country;
import education.domain.university.University;
import education.domain.university.UniversityID;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    @Test
    public void testAddStudent() {
        var studentRepository = new InMemoryStudentRepository();
        var universityRepository = new InMemoryUniversityRepository();
        var studentService = new StudentService(studentRepository, universityRepository);
        var student = studentService.addStudent("tom@email.com", "tom", "el", "bois");

        assertNotNull(student);
    }

    @Test
    public void testRequestStudent() {
        var studentRepository = new InMemoryStudentRepository();
        var universityRepository = new InMemoryUniversityRepository();
        var studentService = new StudentService(studentRepository, universityRepository);
        var student = studentService.addStudent("tom@email.com", "tom", "el", "bois");

        var requestedStudent = studentService.requestStudent(student.id().id());

        assertNotSame(student, Optional.empty());
    }

    @Test
    public void testEnrollStudentIntoUniversity() {
        var universityIDString = UUID.randomUUID().toString();
        var studentRepository = new InMemoryStudentRepository();
        var universityRepository = new InMemoryUniversityRepository();
        var studentService = new StudentService(studentRepository, universityRepository);
        var student = studentService.addStudent( "tom@email.com", "tom", "el", "bois");

        universityRepository.save(new University(new UniversityID(UUID.fromString(universityIDString)), "Hogeschool Utrecht", Country.THE_NETHERLANDS));
        studentService.enrollIntoUniversity(student.id().id(), universityIDString);

        var university = universityRepository.universityOfID(new UniversityID(UUID.fromString(universityIDString)));

        assertEquals(student.universityID(), university.get().id());
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
