package common;

import education.adapter.persistence.InMemoryStudentRepository;
import education.adapter.persistence.InMemoryUniversityRepository;
import education.adapter.rest.StudentResource;
import education.domain.student.Student;
import education.domain.student.StudentID;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

// Entry point of the webapp
@ApplicationPath("/services")
public class FeedbackSystem extends Application {
    private Set<Object> singletons = new HashSet<>();
    private Set<Class<?>> empty = new HashSet<>();

    public FeedbackSystem() {
        var sr = new InMemoryStudentRepository();
        var ur = new InMemoryUniversityRepository();
        sr.save(new Student(new StudentID(UUID.fromString("e2c7de0e-8ae8-4529-a036-6b7989e81616")), new Person(new Email("bob@email.com"), new FullName("bob", "fisher"))));
        singletons.add(new StudentResource(sr, ur));
    }

    @Override
    public Set<Class<?>> getClasses() {
        return empty;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
