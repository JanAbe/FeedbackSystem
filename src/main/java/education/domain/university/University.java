package education.domain.university;

import common.util.validators.Validate;

public class University {
    private UniversityID id;
    private String name;
    private Country country;

    public University(UniversityID id, String name, Country country) {
        this.setID(id);
        this.setName(name);
        this.setCountry(country);
    }

    // ---------- Public methods ----------

    public UniversityID id() {
        return this.id;
    }

    public String name() {
        return this.name;
    }

    public Country country() {
        return this.country;
    }

    // ---------- Private methods ----------

    private void setID(UniversityID id) {
        // checks here ...
        Validate.argumentNotNull(id, "Provided universityID can not be null");
        this.id = id;
    }

    private void setName(String name) {
        // checks here ...
        Validate.argumentNotNull(name, "Provided name can not be null");
        this.name = name;
    }

    private void setCountry(Country country) {
        // checks here ...
        Validate.argumentNotNull(country, "Provided name can not be null");
        this.country = country;
    }
}
