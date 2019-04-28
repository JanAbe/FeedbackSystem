package education.domain.student;

import education.domain.Email;
import education.domain.FullName;
import education.domain.Person;
import education.domain.course.Course;
import education.domain.course.CourseID;
import education.domain.university.UniversityID;
import common.validators.Validate;

import java.util.HashSet;
import java.util.Set;

public class Student {
    private StudentID id;
    private Person person;
    private Set<CourseID> enrolledCourses; // maybe change to enrolledCourseIDs ?
    private UniversityID universityID;

    public Student(StudentID id, Person person) {
        this.setID(id);
        this.setPerson(person);
        this.enrolledCourses = new HashSet<>();
        this.universityID = null;
    }

    public Student(StudentID id, Person person, UniversityID universityID) {
        this.setID(id);
        this.setPerson(person);
        this.setUniversityID(universityID);
        this.enrolledCourses = new HashSet<>();
    }

    // ---------- Public methods ----------

    public StudentID id() {
        return this.id;
    }

    public Email email() {
        return this.person.email();
    }

    public FullName fullName() {
        return this.person.fullName();
    }

    public UniversityID universityID() {
        return this.universityID;
    }

    public Set<CourseID> enrolledCourses() {
        return this.enrolledCourses;
    }

    public void enrollIntoCourse(Course course) {
        Validate.argumentNotNull(course, "Provided course can not be null");
        this.enrolledCourses.add(course.id());
    }

    public void enrollIntoUniversity(UniversityID universityID) {
        this.setUniversityID(universityID);
    }

    public void changeEmail(Email newEmail) {
        this.setPerson(new Person(newEmail, this.fullName()));
    }

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder();
        return stringBuilder.append("Student Info: ")
                .append(this.id().id()).append(" | ")
                .append(this.email().emailAddress()).append(" | ")
                .append(this.fullName().firstName()).append(" ")
                .append(this.fullName().prefix()).append(" ")
                .append(this.fullName().lastName()).toString();

    }

    // ---------- Private methods ----------
    private void setID(StudentID id) {
        Validate.argumentNotNull(id, "Provided studentID can not be null");
        this.id = id;
    }

    private void setPerson(Person person) {
        // checks here ...
        Validate.argumentNotNull(person, "Provided person can not be null");
        this.person = person;
    }

    private void setUniversityID(UniversityID id) {
        // checks here ...
        Validate.argumentNotNull(id, "Provided universityID can not be null");
        this.universityID = id;
    }
}
