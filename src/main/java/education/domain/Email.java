package education.domain;

import common.cleaners.Clean;
import common.validators.Validate;

public final class Email {
    private String email;

    public Email(String email) {
        this.setEmail(email);
    }

    // ---------- Public methods ----------

    public String email() {
        return this.email;
    }

    @Override
    public boolean equals(Object obj) {
        var isEqual = false;

        if (obj != null && obj.getClass() == this.getClass()) {
            var email = (Email) obj;
            isEqual = this.email().equals(email.email());
        }

        return isEqual;
    }

    // ---------- Private methods ----------

    private void setEmail(String email) {
        // checks here ...
        Validate.argumentNotNull(email, "Provided email can not be null");
        this.email = Clean.string(email);
    }
}
