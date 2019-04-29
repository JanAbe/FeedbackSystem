package education.domain.student;

import education.domain.Email;
import education.domain.FullName;
import education.domain.Person;
import education.domain.course.Course;
import education.domain.course.CourseCode;
import education.domain.course.CourseID;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    public void testCreate() {
        var uuid = UUID.randomUUID();
        var studentID = new StudentID(uuid);
        var email = new Email("tom@email.com");
        var fullName = new FullName("tom", "le", "fromage");
        var person = new Person(email, fullName);
        var student = new Student(studentID, person);

        assertNotNull(student);
        assertEquals(studentID, student.id());
        assertEquals(email, student.email());
        assertEquals(fullName, student.fullName());
        assertNotNull(student.enrolledCourses());
        assertThat(student.enrolledCourses(), hasSize(0));
    }

    @Test
    public void testChangeEmail() {
        var uuid = UUID.randomUUID();
        var studentID = new StudentID(uuid);
        var email = new Email("tom@email.com");
        var fullName = new FullName("tom", "le", "fromage");
        var person = new Person(email, fullName);
        var student = new Student(studentID, person);

        assertEquals(student.email().email(), "tom@email.com");

        var newEmail = new Email("tom_cool@email.com");
        student.changeEmail(newEmail);

        assertEquals(student.email().email(), "tom_cool@email.com");
    }

    @Test
    public void testEnrollIntoCourse() {
        var sUUID = UUID.randomUUID();
        var studentID = new StudentID(sUUID);
        var email = new Email("tom@email.com");
        var fullName = new FullName("tom", "le", "fromage");
        var person = new Person(email, fullName);
        var student = new Student(studentID, person);

        assertThat(student.enrolledCourses(), hasSize(0));

        var cUUID = UUID.randomUUID();
        var courseID = new CourseID(cUUID);
        var courseCode = new CourseCode("ASD-19");
        var course = new Course(courseID, "Advanced Software Design", courseCode);

        student.enrollIntoCourse(course);

        assertThat(student.enrolledCourses(), hasSize(1));
        assertThat(student.enrolledCourses(), contains(course.id()));
    }

    @Test
    public void testEnrollIntoCourseNullThrowsIAE() {
        var studentID = new StudentID(UUID.randomUUID());
        var email = new Email("tom@email.com");
        var fullName = new FullName("tom", "le", "fromage");
        var person = new Person(email, fullName);
        var student = new Student(studentID, person);

        assertThat(student.enrolledCourses(), hasSize(0));

        assertThrows(IllegalArgumentException.class, () -> {
            student.enrollIntoCourse(null);
        });
    }
}
