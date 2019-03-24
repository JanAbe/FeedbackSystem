package domain;

public final class Email {
    private String emailAddress;

    public Email(String emailAddress) {
        this.setEmailAddress(emailAddress);
    }

    // ---------- Public methods ----------

    public String emailAddress() {
        return this.emailAddress;
    }

    @Override
    public boolean equals(Object obj) {
        var isEqual = false;

        if (obj != null && obj.getClass() == this.getClass()) {
            var email = (Email) obj;
            isEqual = this.emailAddress().equals(email.emailAddress());
        }

        return isEqual;
    }

    // ---------- Private methods ----------

    private void setEmailAddress(String emailAddress) {
        // checks here ...
        this.emailAddress = emailAddress;
    }
}
