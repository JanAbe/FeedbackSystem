package education.domain.instructor;

import common.util.validators.Validate;

import java.util.UUID;

public final class InstructorID {
    private String id;

    public InstructorID(UUID id) {
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
            var instructorID = (InstructorID) obj;
            isEqual = this.id().equals(instructorID.id());
        }

        return isEqual;
    }

    // ---------- Private methods ----------

    private void setID(UUID id) {
        Validate.argumentNotNull(id, "Provided id can not be null");
        this.id = id.toString();
    }
}
