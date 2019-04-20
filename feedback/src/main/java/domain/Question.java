package domain;

import domain.instructor.InstructorID;
import util.validators.Validate;

import java.util.HashSet;
import java.util.Set;

public final class Question {
    private String text;
    private Set<Response> responses;
    private InstructorID asker;

    public Question(String text, InstructorID asker) {
        this.setQuestionText(text);
        this.setAsker(asker);
        this.responses = new HashSet<>();
    }

    // ---------- Public methods -----------
    public String text() {
        return this.text;
    }

    public InstructorID asker() {
        return this.asker;
    }

    public Set<Response> responses() {
        return this.responses;
    }

    public boolean hasResponses() {
        return !responses.isEmpty();
    }

    // or is Question an entity, because you need to be able to add and change responses?
    public void addResponse(Response response) {
        Validate.argumentNotNull(response, "Provided response can not be null");
        this.responses.add(response);
    }

    // Don't know how equals behaves with a collection method
    @Override
    public boolean equals(Object obj) {
        var isEqual = false;

        if (obj != null && obj.getClass() == this.getClass()) {
            var question = (Question) obj;
            isEqual = this.text().equals(question.text()) &&
                      this.asker().equals(question.asker()) &&
                      this.responses.equals(question.responses);
        }

        return isEqual;
    }

    // ---------- Private methods ----------

    private void setQuestionText(String questionText) {
        Validate.argumentNotNull(questionText, "Provided question text can not be null");
        Validate.notBlank(questionText, "Provided question text can not be blank");
        this.text = questionText;
    }

    private void setAsker(InstructorID asker) {
        Validate.argumentNotNull(asker, "Provided asker can not be null");
        this.asker = asker;
    }
}
