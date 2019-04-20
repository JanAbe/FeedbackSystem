package domain;

import domain.instructor.Instructor;
import domain.student.Student;

import java.util.Collection;

public interface FeedbackSessionRepository {

    /**
     * <p>Generate a new FeedbackSessionID, which can be used to identify a feedbacksession.</p>
     * @return FeedbackSessionID
     */
    FeedbackSessionID nextID();

    /**
     * <p>Save the provided feedbacksession</p>
     * @param feedbackSession FeedbackSession
     */
    void save(FeedbackSession feedbackSession);

    /**
     * <p>Find the instructor that leads the provided feedbacksession</p>
     * @param feedbackSessionID FeedbackSessionID
     * @return Instructor
     */
    Instructor instructorOf(FeedbackSessionID feedbackSessionID);

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
