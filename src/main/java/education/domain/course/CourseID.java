package education.domain.course;

import common.validators.Validate;

import java.util.UUID;

public final class CourseID {
    private String id;

    public CourseID(UUID id) {
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
            var courseID = (CourseID) obj;
            isEqual = this.id().equals(courseID.id());
        }

        return isEqual;
    }

    // ---------- Private methods ----------

    private void setID(UUID id) {
        Validate.argumentNotNull(id, "Provided id can not be null");
        this.id = id.toString();
    }
}
