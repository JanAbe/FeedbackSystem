package education.domain.instructor;

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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InstructorTest {

    @Test
    public void testCreate() {
        var uuid = UUID.randomUUID();
        var instructorID = new InstructorID(uuid);
        var email = new Email("bob@email.com");
        var fullName = new FullName("bob", "le", "fromage");
        var person = new Person(email, fullName);
        var instructor = new Instructor(instructorID, person);

        assertNotNull(instructor);
        assertEquals(instructorID, instructor.id());
        assertEquals(email, instructor.email());
        assertEquals(fullName, instructor.fullName());
        assertNotNull(instructor.taughtCourses());
        assertThat(instructor.taughtCourses(), hasSize(0));
    }

    @Test
    public void testChangeEmail() {
        var uuid = UUID.randomUUID();
        var instructorID = new InstructorID(uuid);
        var email = new Email("bob@email.com");
        var fullName = new FullName("bob", "le", "fromage");
        var person = new Person(email, fullName);
        var instructor = new Instructor(instructorID, person);

        assertEquals(instructor.email().emailAddress(), "bob@email.com");

        var newEmail = new Email("bob_cool@email.com");
        instructor.changeEmail(newEmail);

        assertEquals(instructor.email().emailAddress(), "bob_cool@email.com");
    }

    @Test
    public void testTeachNewCourse() {
        var iUUID = UUID.randomUUID();
        var instructorID = new InstructorID(iUUID);
        var email = new Email("bob@email.com");
        var fullName = new FullName("bob", "le", "fromage");
        var person = new Person(email, fullName);
        var instructor = new Instructor(instructorID, person);

        assertThat(instructor.taughtCourses(), hasSize(0));

        var cUUID = UUID.randomUUID();
        var courseID = new CourseID(cUUID);
        var courseCode = new CourseCode("ASD-19");
        var course = new Course(courseID, "Advanced Software Design", courseCode);

        instructor.teach(course);

        assertThat(instructor.taughtCourses(), hasSize(1));
        assertThat(instructor.taughtCourses(), contains(course.id()));
    }
}
