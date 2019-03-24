package domain.student;

import domain.university.UniversityID;

import java.util.Collection;
import java.util.Optional;

public interface StudentRepository {

    StudentID nextID();

    void save(Student student);

    Optional<Student> studentOfID(StudentID studentID);

    Collection<Student> studentsOfUniversity(UniversityID universityID);
}
