package adapter.persistence;

import domain.student.Student;
import domain.student.StudentID;
import domain.student.StudentRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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
}
