package education;

import education.adapter.persistence.InMemoryStudentRepository;
import education.adapter.persistence.InMemoryUniversityRepository;
import education.adapter.rest.StudentResource;
import education.domain.university.Country;
import education.domain.university.University;

public class MainEducation {
    public static void main(String[] args) {
        var studentRepo = new InMemoryStudentRepository();
        var universityRepo = new InMemoryUniversityRepository();
        var studentResource = new StudentResource(studentRepo, universityRepo);

        var studentID = studentRepo.nextID();
        var studentID1 = studentRepo.nextID();

        var universityID = universityRepo.nextID();
        var university = new University(universityID, "hogeschool utrecht", Country.THE_NETHERLANDS);

        var email = "tom@email.com";
        var email1 = "bob@email.com";

        var firstName = "tom";
        var firstName1 = "bob";

        var prefix = "el";
        var prefix1 = "xd";

        var lastName = "bois";
        var lastName1 = "toy";

        universityRepo.save(university);
        studentResource.addStudent(email, firstName, prefix, lastName);
        studentResource.addStudent(email1, firstName1, prefix1, lastName1);

        studentResource.enrollStudentIntoUniversity(studentID.id(), universityID.id());
        studentResource.enrollStudentIntoUniversity(studentID1.id(), universityID.id());

        var foundStudents = studentResource.requestStudentsOfUniversity(universityID.id());
        System.out.println(foundStudents.getEntity());
    }
}