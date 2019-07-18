package feedback.domain;

import education.domain.instructor.InstructorID;
import education.domain.student.StudentID;
import common.validators.Validate;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FeedbackSession {
    private FeedbackSessionID id;
    private Set<Question> questions;
    private InstructorID supervisorID;
    private Set<StudentID> studentIDs;

    public FeedbackSession(FeedbackSessionID id, InstructorID supervisorID) {
        this.id = id;
        this.supervisorID = supervisorID;
        this.questions = new HashSet<>();
    }

    public FeedbackSession(FeedbackSessionID id,
                           Set<Question> questions,
                           InstructorID supervisorID) {
        this.setID(id);
        this.setQuestions(questions);
        this.setSupervisorID(supervisorID);
    }

    // ---------- Public methods ----------

    public FeedbackSessionID id() {
        return this.id;
    }

    public Set<Question> questions() {
        return this.questions;
    }

    public InstructorID supervisorID() {
        return this.supervisorID;
    }

    public Set<StudentID> studentIDS() {
        return this.studentIDs;
    }

    public void addQuestion(Question question, InstructorID supervisorID) {
        if (this.supervisorID != supervisorID) {
            throw new IllegalArgumentException("Only the assigned supervisor of this feedbacksession can add questions");
        }
        Validate.argumentNotNull(question, "Provided question can not be null");
        this.questions.add(question);
    }

    /**
     * <p>returns the questions that have no response.</p>
     * @return Set of Questions
     */ // Maybe make it so, there can be filtered on the unanswered questions. For example, return only the questions that aren't answered by anyone
        // but also make it possible to filter on questions that are not answered by each participant (participants - 1)
        // e.g bob has answered the question, but tommy hasn't. Filter on questions that are only answered by some participants.
        // maybe add as argument a predicate or something
    public Set<Question> unansweredQuestions() {
        return this.questions.stream().filter(q -> !q.hasResponses()).collect(Collectors.toSet());
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

    private void setSupervisorID(InstructorID supervisorID) {
        Validate.argumentNotNull(supervisorID, "Provided supervisorID can not be null");
        this.supervisorID = supervisorID;
    }
}
