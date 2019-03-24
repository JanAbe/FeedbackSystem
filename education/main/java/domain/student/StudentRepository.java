package domain.student;

import java.util.Optional;

public interface StudentRepository {

    StudentID nextID();

    void save(Student student);

    Optional<Student> studentOfID(StudentID studentID);

    // maybe add Collection<Student> studentsOfUniversity(UniversityID universityID)
}
