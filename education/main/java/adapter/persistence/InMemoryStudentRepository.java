package adapter.persistence;

import domain.student.Student;
import domain.student.StudentID;
import domain.student.StudentRepository;
import domain.university.UniversityID;

import java.util.*;

public class InMemoryStudentRepository implements StudentRepository {
    private Map<StudentID, Student> studentDB;

    public InMemoryStudentRepository() {
        this.studentDB = new HashMap<>();
    }

    @Override
    public StudentID nextID() {
        return new StudentID(UUID.randomUUID());
    }

    @Override
    public void save(Student student) {
        studentDB.put(student.id(), student);
    }

    @Override
    public Optional<Student> studentOfID(StudentID studentID) {
        // returns Student if found, Optional.Empty if null. If a map doesn't contain key, it returns null
        return Optional.ofNullable(studentDB.get(studentID));
    }

    @Override
    public Collection<Student> studentsOfUniversity(UniversityID universityID) {
        // do i need to check here if universityID is null?
        var foundStudents = new HashSet<Student>();

        for (Student student : this.studentDB.values()) {
            if (universityID.equals(student.universityID())) {
                foundStudents.add(student);
            }
        }

        return foundStudents;
    }
}
