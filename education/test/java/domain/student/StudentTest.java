package domain.student;

import domain.Email;
import domain.FullName;
import domain.Person;
import domain.course.Course;
import domain.course.CourseCode;
import domain.course.CourseID;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

        assertEquals(student.email().emailAddress(), "tom@email.com");

        var newEmail = new Email("tom_cool@email.com");
        student.changeEmail(newEmail);

        assertEquals(student.email().emailAddress(), "tom_cool@email.com");
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

        student.enrollInto(course);

        assertThat(student.enrolledCourses(), hasSize(1));
        assertThat(student.enrolledCourses(), contains(course.id()));
    }
}
