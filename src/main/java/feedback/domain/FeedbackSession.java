package feedback.domain;

import education.domain.instructor.InstructorID;
import education.domain.student.StudentID;
import common.util.validators.Validate;

import java.util.HashSet;
import java.util.Set;

public class FeedbackSession {
    private FeedbackSessionID id;
    private Set<Question> questions;
    private InstructorID supervisor;
    private Set<StudentID> studentIDs;

    public FeedbackSession(FeedbackSessionID id,
                           Set<Question> questions,
                           InstructorID supervisor) {
        this.setID(id);
        this.setQuestions(questions);
        this.setInstructorID(supervisor);
    }

    // ---------- Public methods ----------

    public FeedbackSessionID id() {
        return this.id;
    }

    public Set<Question> questions() {
        return this.questions;
    }

    public InstructorID supervisor() {
        return this.supervisor;
    }

    public Set<StudentID> studentIDS() {
        return this.studentIDs;
    }

    // don't know how to do this one, how do i make it so the instructor can add new questions
    // do i need to add a method in the instructor class? or does this validation not happen at this level?
    public void addQuestion(Question question) {
        Validate.argumentNotNull(question, "Provided question can not be null");
        this.questions.add(question);
    }

    /**
     * <p>returns the questions that have no response.</p>
     * @return Set of Questions
     */
    public Set<Question> unansweredQuestions() {
        Set<Question> unansweredQuestions = new HashSet<>();

        for (Question question : this.questions) {
            if (!question.hasResponses()) {
                unansweredQuestions.add(question);
            }
        }

        return  unansweredQuestions;
    }

    // ---------- Private methods ----------

    private void setID(FeedbackSessionID id) {
        Validate.argumentNotNull(id, "Provided feedbacksessionID can not be null");
        this.id = id;
    }

    private void setQuestions(Set<Question> questions) {
        Validate.argumentNotNull(questions, "Provided questions can not be null");
        Validate.notEmpty(questions, "Provided questions can not be empty");
        this.questions = questions;
    }

    private void setInstructorID(InstructorID supervisor) {
        Validate.argumentNotNull(supervisor, "Provided supervisor can not be null");
        this.supervisor = supervisor;
    }
}
