package education.domain.university;

import java.util.Optional;

public interface UniversityRepository {

    /**
     * <p>Generate a new UniversityID, which can be used to identify a university.</p>
     * @return UniversityID
     */
    UniversityID nextID();

    /**
     * <p>Save the provded university.</p>
     * @param university University
     */
    void save(University university);

    /**
     * <p>Find the university corresponding to the provided universityID.</p>
     * @param universityID UniversityID
     * @return an Optional University or an Optional.Empty if no university found.
     */
    Optional<University> universityOfID(UniversityID universityID);
}
