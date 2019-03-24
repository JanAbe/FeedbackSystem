package domain.student;

import domain.Email;
import domain.FullName;
import domain.Person;
import domain.course.Course;
import domain.course.CourseID;

import java.util.HashSet;
import java.util.Set;

public class Student {
    private StudentID id;
    private Person person;
    private Set<CourseID> enrolledCourses;

    public Student(StudentID id, Person person) {
        this.setID(id);
        this.setPerson(person);
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

    public Set<CourseID> enrolledCourses() {
        return this.enrolledCourses;
    }

    public void enrollInto(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Provided course can not be null");
        }

        this.enrolledCourses.add(course.id());
    }

    public void changeEmail(Email newEmail) {
        this.setPerson(new Person(newEmail, this.fullName()));
    }

    // ---------- Private methods ----------
    private void setID(StudentID id) {
        // checks here ...
        this.id = id;
    }

    private void setPerson(Person person) {
        // checks here ...
        this.person = person;
    }
}
