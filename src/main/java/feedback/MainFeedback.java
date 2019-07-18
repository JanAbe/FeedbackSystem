package feedback;

import common.Email;
import common.FullName;
import common.Person;
import education.domain.instructor.InstructorID;
import feedback.domain.*;

import java.util.HashSet;
import java.util.UUID;

public class MainFeedback {
    public static void main(String[] args) {
        var asker = createAsker();
        var id = new InstructorID(UUID.randomUUID());
        var question = new Question("How did you like the course?", id);
        var question1 = new Question("What did you think about the teamwork?", id);
        var question2 = new Question("Cocaine?", id);
        var questions = new HashSet<Question>();
        questions.add(question);
        questions.add(question1);
        questions.add(question2);

        var respondent = createRespondent();
        var response = new Response("It was great!", respondent);
        question.answer(response);

        var commenter = createCommenter();
        var comment = new Response("Your opinion doesn't matter!", commenter);
        response.placeComment(comment);

        var feedbackSessionID = new FeedbackSessionID(UUID.randomUUID());
        var instructorID = new InstructorID(UUID.randomUUID());
        var feedbackSession = new FeedbackSession(feedbackSessionID, questions, instructorID);
    }

    private static Person createAsker() {
        var fullName = new FullName("Tom", "Hernandez");
        var email = new Email("tom@hernandez.com");
        return new Person(email, fullName);
    }

    private static Person createRespondent() {
        var fullName = new FullName("Bobby", "Boat");
        var email = new Email("bobby@boat.com");
        return new Person(email, fullName);
    }

    private static Person createCommenter() {
        var fullName = new FullName("Bobby", "Boat");
        var email = new Email("bobby@boat.com");
        return new Person(email, fullName);
    }
}
