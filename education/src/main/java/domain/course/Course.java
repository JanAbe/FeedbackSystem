package domain.course;

import domain.Period;
import util.validators.Validate;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private CourseID id;
    private String name;
    private CourseCode code;
    private List<Period> teachingMoments; // want a better name, don't know what to change it to though

    public Course(CourseID id, String name, CourseCode code) {
        this.setID(id);
        this.setName(name);
        this.setCode(code);
        this.teachingMoments = new ArrayList<>();
    }

    public Course(CourseID id, String name,
                  CourseCode code,
                  List<Period> teachingMoments) {

        this.setID(id);
        this.setName(name);
        this.setCode(code);
        this.setTeachingMoments(teachingMoments);
    }

    // ---------- Public methods -----------

    public CourseID id() {
        return this.id;
    }

    public String name() {
        return this.name;
    }

    public CourseCode courseCode() {
        return this.code;
    }

    public List<Period> teachingMoments() {
        return this.teachingMoments;
    }

    public void addTeachingMoment(Period teachingMoment) {
        // checks here ...
        Validate.argumentNotNull(teachingMoment, "Provided teachingMoment can not be null");
        this.teachingMoments.add(teachingMoment);
    }

    // ---------- Private methods ----------

    private void setID(CourseID id) {
        // checks here ...
        Validate.argumentNotNull(id, "Provided courseID can not be null");
        this.id = id;
    }

    private void setName(String name) {
        // checks here ...
        Validate.argumentNotNull(name, "Provided name can not be null");
        this.name = name;
    }

    private void setCode(CourseCode code) {
        // checks here ...
        Validate.argumentNotNull(code, "Provided courseCode can not be null");
        this.code = code;
    }

    private void setTeachingMoments(List<Period> teachingMoments) {
        // checks here ...
        Validate.argumentNotNull(teachingMoments, "Provided teachingMoments can not be null");
        this.teachingMoments = teachingMoments;
    }
}
