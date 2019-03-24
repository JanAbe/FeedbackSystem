package domain;

public final class FullName {
    private String firstName;
    private String prefix;
    private String lastName;

    public FullName(String firstName, String prefix, String lastName) {
        this.setFirstName(firstName);
        this.setPrefix(prefix);
        this.setLastName(lastName);
    }

    public FullName(String firstName, String lastName) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    // ---------- Public methods ----------

    public String firstName() {
        return this.firstName;
    }

    public String prefix() {
        return this.prefix;
    }

    public String lastName() {
        return this.lastName;
    }

    @Override
    public boolean equals(Object obj) {
        var isEqual = false;

        if (obj != null && obj.getClass() == this.getClass()) {
            var fullName = (FullName) obj;
            isEqual = this.firstName().equals(fullName.firstName()) &&
                      this.prefix().equals(fullName.prefix()) &&
                      this.lastName().equals(fullName.lastName());
        }

        return isEqual;
    }

    // ---------- Private methods ----------

    private void setFirstName(String firstName) {
        // checks here ...
        this.firstName = firstName;
    }

    private void setPrefix(String prefix) {
        // checks here ...
        this.prefix = prefix;
    }

    private void setLastName(String lastName) {
        // checks here ...
        this.lastName = lastName;
    }
}
