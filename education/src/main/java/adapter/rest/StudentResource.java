package adapter.rest;

import application.student.StudentService;
import domain.student.StudentRepository;
import domain.university.UniversityRepository;
import util.validators.Validate;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/students")
public class StudentResource {
    private StudentRepository studentRepository;
    private UniversityRepository universityRepository;
    private StudentService studentService;

    public StudentResource(StudentRepository studentRepository,
                           UniversityRepository universityRepository) {

        this.setStudentRepository(studentRepository);
        this.setUniversityRepository(universityRepository);
        this.studentService = new StudentService(this.studentRepository, this.universityRepository);
    }

    // ---------- Public methods ----------

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response requestStudent(@PathParam("id") String studentID) {
        var student = this.studentService.requestStudent(studentID);
        Validate.resourceNotAbsent(student);

        return Response.status(Response.Status.OK)
                       .entity(student.get())
                       .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addStudent(String studentID,
                               String email,
                               String firstName,
                               String prefix,
                               String lastName) {

        var createdStudent = this.studentService.addStudent(studentID, email, firstName, prefix, lastName);
        Validate.notNull(createdStudent, "Created student is null");

        return Response.status(Response.Status.CREATED)
                       .entity(createdStudent)
                       .build();
    }

    @PATCH
    @Path("/enroll/{studentID}/{universityID}") // I believe this is not really REST like....
    @Produces(MediaType.APPLICATION_JSON)
    public Response enrollStudentIntoUniversity(@PathParam("studentID") String studentID,
                                                @PathParam("universityID") String universityID) {

        var updatedStudent = this.studentService.enrollIntoUniversity(studentID, universityID);
        Validate.notNull(updatedStudent, "Updated student is null");

        return Response.status(Response.Status.OK)
                .entity(updatedStudent)
                .build();
    }

    // ---------- Private methods ----------

    private void setStudentRepository(StudentRepository studentRepository) {
        Validate.argumentNotNull(studentRepository, "StudentRepository can not be null");
        this.studentRepository = studentRepository;
    }

    private void setUniversityRepository(UniversityRepository universityRepository) {
        Validate.argumentNotNull(universityRepository, "UniversityRepository can not be null");
        this.universityRepository = universityRepository;
    }
}
