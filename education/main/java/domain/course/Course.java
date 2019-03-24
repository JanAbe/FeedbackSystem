package domain.course;

import domain.Period;

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
        this.teachingMoments.add(teachingMoment);
    }

    // ---------- Private methods ----------

    private void setID(CourseID id) {
        // checks here ...
        this.id = id;
    }

    private void setName(String name) {
        // checks here ...
        this.name = name;
    }

    private void setCode(CourseCode code) {
        // checks here ...
        this.code = code;
    }

    private void setTeachingMoments(List<Period> teachingMoments) {
        // checks here ...
        this.teachingMoments = teachingMoments;
    }
}
