package education.adapter.rest;

import education.application.student.StudentService;
import education.domain.student.StudentRepository;
import education.domain.university.UniversityRepository;
import common.validators.Validate;

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

    // ---------- Public methods -----------
    // ||                                 ||
    // ||                                 ||
    // ||                                 ||
    // ||                                 ||
    // -------------------------------------

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response requestStudent(@PathParam("id") String studentID) {
        var student = this.studentService.requestStudent(studentID);
        Validate.resourceNotAbsent(student);

        System.out.println(student.toString());

        // TODO: need to find a way to return the found Student in json format
        return Response.status(Response.Status.OK).build();
//                .entity(student.get())
//                .build();
    }

    // TODO: add start and size query parameters so the
    //  client can specify the amount of students he/she wants
    @GET
    @Path("/university/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response requestStudentsOfUniversity(@PathParam("id") String universityID) {
        var students = this.studentService.requestStudentsOfUniversity(universityID);
        Validate.resourceNotAbsent(students);

        return Response.status(Response.Status.OK)
                .entity(students)
                .build();
    }

    // TODO: find out how to make the code below work.
    // atm it raises an error, stating that the args aren't annotated
    // What annotation do I need if i want to consider the args as individual json parts
    // Maybe that's where it breaks. I don't think i can do the thing described in the line above,
    // also it doesn't really make sense i think. It expects a jsonobject (because of the application_json annotation)
    // maybe i need to replace all args with Student s.
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response addStudent(String email,
//                               String firstName,
//                               String prefix,
//                               String lastName) {
//
//        var createdStudent = this.studentService.addStudent(email, firstName, prefix, lastName);
//        Validate.notNull(createdStudent, "Created student is null");
//
//        return Response.status(Response.Status.CREATED)
//                .entity(createdStudent)
//                .build();
//    }

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
    // ||                                 ||
    // ||                                 ||
    // ||                                 ||
    // ||                                 ||
    // -------------------------------------

    private void setStudentRepository(StudentRepository studentRepository) {
        Validate.argumentNotNull(studentRepository, "StudentRepository can not be null");
        this.studentRepository = studentRepository;
    }

    private void setUniversityRepository(UniversityRepository universityRepository) {
        Validate.argumentNotNull(universityRepository, "UniversityRepository can not be null");
        this.universityRepository = universityRepository;
    }
}
