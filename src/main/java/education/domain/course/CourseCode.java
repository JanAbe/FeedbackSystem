package education.domain.course;

public final class CourseCode {
    private String code;

    public CourseCode(String code) {
        this.setCode(code);
    }

    // ---------- Public methods ----------

    public String code() {
        return this.code;
    }

    @Override
    public boolean equals(Object obj) {
        var isEqual = false;

        if (obj != null && obj.getClass() == this.getClass()) {
            var courseCode = (CourseCode) obj;
            isEqual = this.code().equals(courseCode.code());
        }

        return isEqual;
    }

    // ---------- Private methods ----------

    private void setCode(String code) {
        // checks here ...
        this.code = code;
    }
}
