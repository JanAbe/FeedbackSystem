import domain.*;
import domain.instructor.InstructorID;

import java.util.HashSet;
import java.util.UUID;

public class MainFeedback {
    public static void main(String[] args) {
        var asker = createAsker();
        var question = new Question("How did you like the course?", asker);
        var question1 = new Question("What did you think about the teamwork?", asker);
        var question2 = new Question("Cocaine?", asker);
        var questions = new HashSet<Question>();
        questions.add(question);
        questions.add(question1);
        questions.add(question2);

        var respondent = createRespondent();
        var response = new Response("It was great!", respondent);
        question.answer(response);

        var commenter = createCommenter();
        var comment = new Comment("Your opinion doesn't matter!", commenter);
        response.placeComment(comment);

        var feedbackSessionID = new FeedbackSessionID(UUID.randomUUID());
        var instructorID = new InstructorID(UUID.randomUUID());
        var feedbackSession = new FeedbackSession(feedbackSessionID, questions, instructorID);
    }

    private static Asker createAsker() {
        var fullName = new FullName("Tom", "Hernandez");
        var email = new Email("tom@hernandez.com");
        return new Asker(fullName, email);
    }

    private static Respondent createRespondent() {
        var fullName = new FullName("Bobby", "Boat");
        var email = new Email("bobby@boat.com");
        return new Respondent(fullName, email);
    }

    private static Commenter createCommenter() {
        var fullName = new FullName("Bobby", "Boat");
        var email = new Email("bobby@boat.com");
        return new Commenter(fullName, email);
    }
}
