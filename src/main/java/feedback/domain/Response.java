package feedback.domain;

import common.validators.Validate;
import common.Person;

import java.util.HashSet;
import java.util.Set;

public final class Response {
    private String text;
    private Set<Response> comments;
    private Person respondent;

    public Response(String text, Person respondent) {
        this.setText(text);
        this.setRespondent(respondent);
        this.comments = new HashSet<>();
    }

    // ---------- Public methods ----------

    public String text() {
        return this.text;
    }

    public Set<Response> comments() {
        return this.comments;
    }

    public Person respondent() {
        return this.respondent;
    }

    // editing a response would just be creating a new response with a different text though because it's a value object
    public void edit(String text) {
        Validate.argumentNotNull(text, "Provided text can not be null");
        this.text = text;
    }

    // adding a comment to a response would just be creating a new response with a different set of comments though because it's a value object
    // but can this method exist if i want this to be a value object?
    // this would mean your need to pass Set<Comment> as argument to the constructor, hmm
    public void placeComment(Response comment) {
        Validate.argumentNotNull(comment, "Provided comment can not be null");
        this.comments.add(comment);
    }

    // removing a comment to a response would just be creating a new response with a different set of comments though because it's a value object
    // this would mean your need to pass Set<Comment> as argument to the constructor, hmm
    public void removeComment(Response comment) {
        Validate.argumentNotNull(comment, "Provided comment can not be null");
        this.comments.remove(comment);
    }

    @Override
    public boolean equals(Object obj) {
        var isEqual = false;

        if (obj != null && obj.getClass() == this.getClass()) {
            var response = (Response) obj;
            isEqual = this.text().equals(response.text()) &&
                      this.respondent().equals(response.respondent()) &&
                      this.comments().equals(response.comments());
        }

        return isEqual;
    }

    // ---------- Private methods ----------

    private void setText(String text) {
        Validate.argumentNotNull(text, "Provided text can not be null");
        Validate.notBlank(text, "Provided text can not be blank");
        this.text = text;
    }

    private void setRespondent(Person respondent) {
        Validate.argumentNotNull(respondent, "Provided respondent can not be null");
        this.respondent = respondent;
    }
}
