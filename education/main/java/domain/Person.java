package domain;

public final class Person {
    private Email email;
    private FullName fullName;

    public Person(Email email, FullName fullName) {
        this.setEmail(email);
        this.setFullName(fullName);
    }

    // ---------- Public methods ----------

    public Email email() {
        return this.email;
    }

    public FullName fullName() {
        return this.fullName;
    }

    @Override
    public boolean equals(Object obj) {
        var isEqual = false;

        if (obj != null && obj.getClass() == this.getClass()) {
            var person = (Person) obj;
            isEqual = this.email().equals(person.email()) &&
                      this.fullName().equals(person.fullName());
        }

        return isEqual;
    }

    // ---------- Private methods ----------

    private void setEmail(Email email) {
        // checks here ...
        this.email = email;
    }

    private void setFullName(FullName fullName) {
        // checks here ...
        this.fullName = fullName;
    }

}
