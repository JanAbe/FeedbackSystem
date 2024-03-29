package education.domain.student;

import education.domain.university.UniversityID;

import java.util.Collection;
import java.util.Optional;

public interface StudentRepository {

    /**
     * <p>Generate a new StudentID, which can be used to identify a student.</p>
     * @return StudentID
     */
    StudentID nextID();

    /**
     * <p>Save the provided student.</p>
     * @param student Student
     */
    void save(Student student);

    /**
     * <p>Find the student corresponding to the provided studentID.</p>
     * @param studentID StudentID
     * @return an Optional Student or Optional.Empty if no student found.
     */
    Optional<Student> studentOfID(StudentID studentID);

    /**
     * <p>Find and return all students of the provided university.</p>
     * @param universityID UniversityID
     * @return a collection of found students, or an empty collection if none found.
     */
    Collection<Student> studentsOfUniversity(UniversityID universityID);
}
