package adapter.persistence;

import domain.university.University;
import domain.university.UniversityID;
import domain.university.UniversityRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InMemoryUniversityRepository implements UniversityRepository {
    private Map<String, University> universityDB;

    public InMemoryUniversityRepository() {
        this.universityDB = new HashMap<>();
    }

    @Override
    public UniversityID nextID() {
        return new UniversityID(UUID.randomUUID());
    }

    @Override
    public void save(University university) {
        universityDB.put(university.id().id(), university);
    }

    @Override
    public Optional<University> universityOfID(UniversityID universityID) {
        return Optional.ofNullable(universityDB.get(universityID.id()));
    }
}
