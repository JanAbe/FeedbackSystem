package application;

import domain.FeedbackSession;
import domain.FeedbackSessionID;
import domain.FeedbackSessionRepository;
import domain.Question;
import domain.instructor.Instructor;
import domain.instructor.InstructorID;
import domain.student.Student;
import util.validators.Validate;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FeedbackSessionService {
    private FeedbackSessionRepository feedbackSessionRepository;
    private final static Logger LOGGER = Logger.getLogger(FeedbackSessionService.class.getName());

    public FeedbackSessionService(FeedbackSessionRepository feedbackSessionRepository) {
        this.setFeedbackSessionRepository(feedbackSessionRepository);
    }

    // ---------- Public methods ----------

    public FeedbackSession startFeedbackSession(String iID,
                                                String... questionsTexts) {
        FeedbackSession feedbackSession = null;
        try {
            var feedbackSessionID = this.feedbackSessionRepository.nextID();
            var instructorID = new InstructorID(UUID.fromString(iID));
            var questions = new HashSet<Question>();
            for (String questionText : questionsTexts) {
                questions.add(new Question(questionText, instructorID));
            }
            feedbackSession = new FeedbackSession(feedbackSessionID, questions, instructorID);
            this.feedbackSessionRepository.save(feedbackSession);
        } catch(Exception e) {
            LOGGER.log(Level.SEVERE, "Exception occurred while requesting a feedbacksession: ", e);
        }

        return feedbackSession;
    }

    public Optional<FeedbackSession> requestFeedbackSession(String id) {
        Optional<FeedbackSession> feedbackSession = Optional.empty();
        try {
            var feedbackSessionID = new FeedbackSessionID(UUID.fromString(id));
            feedbackSession = this.feedbackSessionRepository.feedbackSessionOfID(feedbackSessionID);
        } catch(Exception e) {
            LOGGER.log(Level.SEVERE, "Exception occurred while requesting a feedbacksession: ", e);
        }

        return feedbackSession;
    }

    public Optional<Instructor> requestInstructorOfFeedbackSession(String id) {
        Optional<Instructor> instructor = Optional.empty();
        try {
            var feedbackSessionID = new FeedbackSessionID(UUID.fromString(id));
            instructor = this.feedbackSessionRepository.instructorOf(feedbackSessionID);
        } catch(Exception e) {
            LOGGER.log(Level.SEVERE, "Exception occurred while requesting the instructor of a feedbacksession: ", e);
        }

        return instructor;
    }

    public Collection<Student> requestParticipantsOfFeedbackSession(String id) {
        Collection<Student> participants = null;
        try {
            var feedbackSessionID = new FeedbackSessionID(UUID.fromString(id));
            var feedbackSession = this.feedbackSessionRepository.feedbackSessionOfID(feedbackSessionID);
            Validate.notEmpty(feedbackSession, "Feedbacksession that belongs to the provided ID does not exist");

            participants = this.feedbackSessionRepository.studentsOf(feedbackSessionID);
        } catch(Exception e) {
            LOGGER.log(Level.SEVERE, "Exception occurred while requesting the instructor of a feedbacksession: ", e);
        }

        return participants;
    }

    public Collection<Question> requestQuestionsOfFeedbackSession(String id) {
        Collection<Question> questions = null;
        try {
            var feedbackSessionID = new FeedbackSessionID(UUID.fromString(id));
            var feedbackSession = this.feedbackSessionRepository.feedbackSessionOfID(feedbackSessionID);
            Validate.notEmpty(feedbackSession, "Feedbacksession that belongs to the provided ID does not exist");

            questions = this.feedbackSessionRepository.questionsOf(feedbackSessionID);
        } catch(Exception e) {
            LOGGER.log(Level.SEVERE, "Exception occurred while requesting the instructor of a feedbacksession: ", e);
        }

        return questions;
    }

    // ---------- Private methods ----------

    private void setFeedbackSessionRepository(FeedbackSessionRepository feedbackSessionRepository) {
        Validate.argumentNotNull(feedbackSessionRepository, "FeedbackSessionRepository can not be null");
        this.feedbackSessionRepository = feedbackSessionRepository;
    }
}
