package domain.student;

import domain.Email;
import domain.FullName;
import domain.Person;
import domain.course.Course;
import domain.course.CourseID;
import domain.university.UniversityID;
import util.validators.Validate;

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

    public void enrollInto(Course course) {
        Validate.argumentNotNull(course, "Provided course can not be null");
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

    // or change name to setUniversityID(), hmmm
    private void setUniversityID(UniversityID id) {
        // checks here ...
        this.universityID = id;
    }
}
