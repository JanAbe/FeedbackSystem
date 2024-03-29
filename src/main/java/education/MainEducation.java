package education;

import common.DatabaseConfig;
import education.adapter.persistence.InMemoryStudentRepository;
import education.adapter.persistence.InMemoryUniversityRepository;
import education.adapter.persistence.PostgresStudentRepository;
import education.adapter.rest.StudentResource;
import education.domain.Email;
import education.domain.FullName;
import education.domain.Person;
import education.domain.student.Student;
import education.domain.student.StudentID;
import education.domain.university.Country;
import education.domain.university.University;
import education.domain.university.UniversityID;

import java.util.UUID;

public class MainEducation {
    public static void main(String[] args) {
        var config = new DatabaseConfig();
        var repo = new PostgresStudentRepository(config);
        var studentID2 = new StudentID(UUID.fromString("e2c7de0e-8ae8-4529-a036-6b7989e81616"));
        var student = repo.studentOfID(studentID2);
        var uniID = new UniversityID(UUID.fromString("b4ee0ded-6e2c-4d52-bfc5-f88e75f8cc07"));
        var students = repo.studentsOfUniversity(uniID);
        System.out.println(student.toString());
        System.out.println(students);
        var newStudent = new Student(new StudentID(UUID.randomUUID()),
                new Person(
                        new Email("hernandez@email.com"),
                        new FullName("hernandez", "el", "angel")
                ),
                uniID);

        repo.save(newStudent);

//        var studentRepo = new InMemoryStudentRepository();
//        var universityRepo = new InMemoryUniversityRepository();
//        var studentResource = new StudentResource(studentRepo, universityRepo);
//
//        var studentID = studentRepo.nextID();
//        var studentID1 = studentRepo.nextID();
//
//        var universityID = universityRepo.nextID();
//        var university = new University(universityID, "hogeschool utrecht", Country.THE_NETHERLANDS);
//
//        var email = "tom@email.com";
//        var email1 = "bob@email.com";
//
//        var firstName = "tom";
//        var firstName1 = "bob";
//
//        var prefix = "el";
//        var prefix1 = "xd";
//
//        var lastName = "bois";
//        var lastName1 = "toy";
//
//        universityRepo.save(university);
//        studentResource.addStudent(email, firstName, prefix, lastName);
//        studentResource.addStudent(email1, firstName1, prefix1, lastName1);
//
//        studentResource.enrollStudentIntoUniversity(studentID.id(), universityID.id());
//        studentResource.enrollStudentIntoUniversity(studentID1.id(), universityID.id());
//
//        var foundStudents = studentResource.requestStudentsOfUniversity(universityID.id());
//        System.out.println(foundStudents.getEntity());
    }
}
