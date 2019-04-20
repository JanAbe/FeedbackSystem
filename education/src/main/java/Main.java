import adapter.persistence.InMemoryStudentRepository;
import adapter.persistence.InMemoryUniversityRepository;
import adapter.rest.StudentResource;

public class Main {
    public static void main(String[] args) {
        var studentRepo = new InMemoryStudentRepository();
        var universityRepo = new InMemoryUniversityRepository();
        var studentResource = new StudentResource(studentRepo, universityRepo);

        var studentID = studentRepo.nextID();
        var universityID = universityRepo.nextID();
        var email = "tom@email.com";
        var firstName = "tom";
        var prefix = "el";
        var lastName = "bois";
        studentResource.addStudent(studentID.id(), email, firstName, prefix, lastName);

        var foundStudent = studentResource.requestStudent(studentID.id());
        System.out.println(foundStudent.getEntity());
    }
}
