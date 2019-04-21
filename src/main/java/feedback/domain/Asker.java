package feedback.domain;

import education.domain.Email;
import education.domain.FullName;
import education.domain.Person;

public final class Asker {
    private Person person;

    public Asker(FullName fullName, Email email) {
        this.setPerson(fullName, email);
    }

    // ---------- Public methods ----------

    @Override
    public boolean equals(Object obj) {
        var isEqual = false;

        if (obj != null && obj.getClass() == this.getClass()) {
            var person = (Person) obj;
            isEqual = this.person.email().equals(person.email()) &&
                    this.person.fullName().equals(person.fullName());
        }

        return isEqual;
    }

    // ---------- Private methods ----------

    private void setPerson(FullName fullName, Email email) {
        this.person = new Person(email, fullName);
    }
}
