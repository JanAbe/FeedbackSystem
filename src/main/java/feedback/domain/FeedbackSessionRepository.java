package feedback.domain;

import education.domain.instructor.Instructor;
import education.domain.student.Student;

import java.util.Collection;
import java.util.Optional;

public interface FeedbackSessionRepository {

    /**
     * <p>Generate a new FeedbackSessionID, which can be used to identify a feedbacksession.</p>
     * @return FeedbackSessionID
     */
    FeedbackSessionID nextID();

    /**
     * <p>Save the provided feedbacksession.</p>
     * @param feedbackSession FeedbackSession
     */
    void save(FeedbackSession feedbackSession);

    /**
     * <p>Find the feedbacksession corresponding to the provided feedbacksessionID.</p>
     * @param feedbackSessionID FeedbackSessionID
     * @return an Optional FeedbackSession or Optional.Empty if no feedbacksession found.
     */
    Optional<FeedbackSession> feedbackSessionOfID(FeedbackSessionID feedbackSessionID);

    /**
     * <p>Find the instructor that leads the provided feedbacksessionID.</p>
     * @param feedbackSessionID FeedbackSessionID
     * @return an Optional Instructor or Optional.Empty if no instructor found.
     */
    Optional<Instructor> instructorOf(FeedbackSessionID feedbackSessionID);

    /**
     * <p>Find all students who are assigned to the provided feedbacksession.</p>
     * @param feedbackSessionID FeedbackSessionID
     * @return a collection of found students, or an empty collection if none found.
     */
    Collection<Student> studentsOf(FeedbackSessionID feedbackSessionID);

    /**
     * <p>Find all questions that are asked in the provided feedbacksession.</p>
     * @param feedbackSessionID FeedbackSessionID
     * @return a collection of found questions, or an empty collection if none found.
     */
    Collection<Question> questionsOf(FeedbackSessionID feedbackSessionID);
}
