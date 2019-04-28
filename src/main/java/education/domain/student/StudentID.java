package education.domain.student;

import common.validators.Validate;

import java.util.UUID;

public final class StudentID {
    private String id; // why can't this field be final if i want to assign a value using a setter, but can be if i assign it regulary in the constructor?

    public StudentID(UUID id) {
        this.setID(id);
    }

    // ---------- Public methods ----------

    public String id() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        var isEqual = false;

        if (obj != null && obj.getClass() == this.getClass()) {
            var studentID = (StudentID) obj;
            isEqual = this.id().equals(studentID.id());
        }

        return isEqual;
    }

    // ---------- Private methods ----------

    private void setID(UUID id) {
        Validate.argumentNotNull(id, "Provided id can not be null");
        this.id = id.toString();
    }
}
