package education.domain.instructor;

import education.domain.Email;
import education.domain.FullName;
import education.domain.Person;
import education.domain.course.Course;
import education.domain.course.CourseID;
import common.util.validators.Validate;

import java.util.HashSet;
import java.util.Set;

public class Instructor {
    private InstructorID id;
    private Person person;
    private Set<CourseID> taughtCourses;

    public Instructor(InstructorID id, Person person) {
        this.setID(id);
        this.setPerson(person);
        this.taughtCourses = new HashSet<>();
    }

    // ---------- Public methods ----------

    public InstructorID id() {
        return this.id;
    }

    public Set<CourseID> taughtCourses() {
        return this.taughtCourses;
    }

    public Email email() {
        return person.email();
    }

    public FullName fullName() {
        return person.fullName();
    }

    public void teach(Course course) {
        Validate.argumentNotNull(course, "Provided course can not be null");
        this.taughtCourses.add(course.id());
    }

    public void changeEmail(Email newEmail) {
        this.setPerson(new Person(newEmail, this.fullName()));
    }

    // ---------- Private methods ----------

    private void setID(InstructorID id) {
        // checks here ...
        this.id = id;
    }

    private void setPerson(Person person) {
        // checks here ...
        this.person = person;
    }
}
