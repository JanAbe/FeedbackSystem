package feedback.domain;

import common.Email;
import common.FullName;
import common.Person;
import org.junit.jupiter.api.Test;
import education.domain.instructor.InstructorID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import java.util.UUID;

public class FeedbackSessionTest {

    @Test
    public void testUnansweredQuestionsWithUnansweredQuestion() {
        var id = new FeedbackSessionID(UUID.randomUUID());
        var supervisorID = new InstructorID(UUID.randomUUID());
        var feedbackSession = new FeedbackSession(id, supervisorID);
        var question = new Question("Did you like the course?", supervisorID);
        feedbackSession.addQuestion(question, supervisorID);

        var unansweredQuestions = feedbackSession.unansweredQuestions();
        assertThat(unansweredQuestions, hasSize(1));
    }

    @Test
    public void testUnansweredQuestionsWithAnsweredQuestion() {
        var id = new FeedbackSessionID(UUID.randomUUID());
        var supervisorID = new InstructorID(UUID.randomUUID());
        var feedbackSession = new FeedbackSession(id, supervisorID);
        var question = new Question("Did you like the course?", supervisorID);
        question.answer(new Response("not really", new Person(new Email("kingbob@email.com"), new FullName("King", "Bob"))));
        feedbackSession.addQuestion(question, supervisorID);

        var unansweredQuestions = feedbackSession.unansweredQuestions();
        assertThat(unansweredQuestions, hasSize(0));
    }
}