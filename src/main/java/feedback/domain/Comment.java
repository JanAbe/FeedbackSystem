package feedback.domain;

import common.util.validators.Validate;

import java.util.HashSet;
import java.util.Set;

public final class Comment {
    private String text;
    private Commenter commenter;
    private Set<Comment> comments;

    public Comment(String text, Commenter commenter) {
        this.setText(text);
        this.setCommenter(commenter);
        this.comments = new HashSet<>();
    }

    // ---------- Public methods ----------

    public String text() {
        return this.text;
    }

    public Commenter commenter() {
        return this.commenter;
    }

    public Set<Comment> comments() {
        return this.comments;
    }

    public void placeComment(Comment comment) {
        Validate.argumentNotNull(comment, "Provided comment can not be null");
        this.comments.add(comment);
    }

    public void removeComment(Comment comment) {
        Validate.argumentNotNull(comment, "Provided comment can not be null");
        this.comments.remove(comment);
    }

    public void edit(String text) {
        Validate.argumentNotNull(text, "Provided text can not be null");
        this.text = text;
    }

    // ---------- Private methods ----------

    private void setText(String text) {
        Validate.argumentNotNull(text, "Provided text can not be null");
        Validate.notBlank(text, "Provided text can not be blank");
        this.text = text;
    }

    private void setCommenter(Commenter commenter) {
        Validate.argumentNotNull(commenter, "Provided respondent can not be null");
        this.commenter = commenter;
    }
}
