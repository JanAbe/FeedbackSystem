package education.domain.course;

import education.domain.Period;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CourseTest {

    @Test
    public void testCreate() {
        var course = new Course(new CourseID(UUID.randomUUID()),
                                "Advanced Software Design",
                                new CourseCode("ASD-19"));

        assertNotNull(course);
        assertNotNull(course.id());
        assertNotNull(course.courseCode());
        assertNotNull(course.name());
        assertNotNull(course.teachingMoments());
    }

    @Test
    public void testCreateWithPeriods() {
        var period1 = new Period(LocalDateTime.now().plusDays(1),
                                 LocalDateTime.now().plusMonths(3),
                                 TimeZone.getDefault());

        var period2 = new Period(LocalDateTime.now().plusDays(4),
                                 LocalDateTime.now().plusMonths(2),
                                 TimeZone.getDefault());

        var periods = List.of(period1, period2);

        var course = new Course(new CourseID(UUID.randomUUID()),
                                "Advanced Software Design",
                                new CourseCode("ASD-19"),
                                periods);

        assertNotNull(course);
        assertNotNull(course.id());
        assertNotNull(course.courseCode());
        assertNotNull(course.name());
        assertNotNull(course.teachingMoments());
    }

    @Test
    public void testAddTeachingMoment() {
        var course = new Course(new CourseID(UUID.randomUUID()),
                                "Advanced Software Design",
                                new CourseCode("ASD-19"));

        var period1 = new Period(LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusMonths(3),
                TimeZone.getDefault());

        assertThat(course.teachingMoments(), hasSize(0));

        course.addTeachingMoment(period1);

        assertThat(course.teachingMoments(), hasSize(1));
    }

    @Test
    public void testAddTeachingMomentThrowsIAE() {
        var course = new Course(new CourseID(UUID.randomUUID()),
                "Advanced Software Design",
                new CourseCode("ASD-19"));

        assertThrows(IllegalArgumentException.class, () -> {
            course.addTeachingMoment(null);
        });
    }

    @Test
    public void testSetIDThrowsIAE() {
        assertThrows(IllegalArgumentException.class, () -> {
            // constructor of Course uses setID() method
            new Course(null, "Advanced Software Design", new CourseCode("ASD-19"));
        });
    }

    @Test
    public void testSetNameThrowsIAE() {
        assertThrows(IllegalArgumentException.class, () -> {
            // constructor of Course uses setID() method
            new Course(new CourseID(UUID.randomUUID()), null, new CourseCode("ASD-19"));
        });
    }

    @Test
    public void testSetCodeThrowsIAE() {
        assertThrows(IllegalArgumentException.class, () -> {
            // constructor of Course uses setID() method
            new Course(new CourseID(UUID.randomUUID()), "Advanced Software Design", null);
        });
    }

    @Test
    public void testSetPeriodsThrowsIAE() {
        assertThrows(IllegalArgumentException.class, () -> {
            // constructor of Course uses setID() method
            new Course(new CourseID(UUID.randomUUID()),
                    "Advanced Software Design",
                    new CourseCode("ASD-19"),
                    null);
        });
    }
}
