package domain.instructor;

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
        // checks here ...
        if (id == null) {
            throw new IllegalArgumentException("Provided id can not be null");
        }

        this.id = id.toString();
    }
}
