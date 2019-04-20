package domain;

import domain.student.StudentID;
import util.validators.Validate;

import java.util.HashSet;
import java.util.Set;

public final class Response {
    private String text;
    private Set<Comment> comments;
    private StudentID answerer;

    public Response(String text, StudentID answerer) {
        this.setText(text);
        this.setAnswerer(answerer);
        this.comments = new HashSet<>();
    }

    // ---------- Public methods ----------

    public String text() {
        return this.text;
    }

    public Set<Comment> comments() {
        return this.comments;
    }

    public StudentID answerer() {
        return this.answerer;
    }

    public void placeComment(Comment comment) {
        Validate.argumentNotNull(comment, "Provided comment can not be null");

    }

    // ---------- Private methods ----------

    private void setText(String text) {
        Validate.argumentNotNull(text, "Provided text can not be null");
        Validate.notBlank(text, "Provided text can not be blank");
        this.text = text;
    }

    private void setAnswerer(StudentID answerer) {
        Validate.argumentNotNull(answerer, "Provided answerer can not be null");
        this.answerer = answerer;
    }
}
