import adapter.persistence.InMemoryStudentRepository;
import adapter.persistence.InMemoryUniversityRepository;
import adapter.rest.StudentResource;
import domain.university.Country;
import domain.university.University;

public class Main {
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
        studentResource.addStudent(studentID.id(), email, firstName, prefix, lastName);
        studentResource.addStudent(studentID1.id(), email1, firstName1, prefix1, lastName1);

        studentResource.enrollStudentIntoUniversity(studentID.id(), universityID.id());
        studentResource.enrollStudentIntoUniversity(studentID1.id(), universityID.id());

        var foundStudents = studentResource.requestStudentsOfUniversity(universityID.id());
        System.out.println(foundStudents.getEntity());
    }
}
