package domain;

import util.validators.Validate;

import java.util.HashSet;
import java.util.Set;

public final class Response {
    private String text;
    private Set<Comment> comments;
    private Respondent respondent;

    public Response(String text, Respondent respondent) {
        this.setText(text);
        this.setRespondent(respondent);
        this.comments = new HashSet<>();
    }

    // ---------- Public methods ----------

    public String text() {
        return this.text;
    }

    public Set<Comment> comments() {
        return this.comments;
    }

    public Respondent respondent() {
        return this.respondent;
    }

    public void edit(String text) {
        Validate.argumentNotNull(text, "Provided text can not be null");
        this.text = text;
    }

    // but can this method exist if i want this to be a value object?
    public void placeComment(Comment comment) {
        Validate.argumentNotNull(comment, "Provided comment can not be null");
        this.comments.add(comment);
    }

    public void removeComment(Comment comment) {
        Validate.argumentNotNull(comment, "Provided comment can not be null");
        this.comments.remove(comment);
    }

    // ---------- Private methods ----------

    private void setText(String text) {
        Validate.argumentNotNull(text, "Provided text can not be null");
        Validate.notBlank(text, "Provided text can not be blank");
        this.text = text;
    }

    private void setRespondent(Respondent respondent) {
        Validate.argumentNotNull(respondent, "Provided respondent can not be null");
        this.respondent = respondent;
    }
}
