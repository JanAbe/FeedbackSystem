package domain.university;

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
        this.id = id;
    }

    private void setName(String name) {
        // checks here ...
        this.name = name;
    }

    private void setCountry(Country country) {
        // checks here ...
        this.country = country;
    }
}
