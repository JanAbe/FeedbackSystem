package feedback.domain;

import common.validators.Validate;

import java.util.UUID;

public final class FeedbackSessionID {
    private String id;

    public FeedbackSessionID(UUID id) {
        this.setID(id);
    }

    public String id() {
        return this.id;
    }

    // ---------- Public methods -----------

    @Override
    public boolean equals(Object obj) {
        var isEqual = false;

        if (obj != null && obj.getClass() == this.getClass()) {
            var feedbackSessionID = (FeedbackSessionID) obj;
            isEqual = this.id().equals(feedbackSessionID.id());
        }

        return isEqual;
    }

    // ---------- Private methods -----------

    private void setID(UUID id) {
        Validate.argumentNotNull(id, "Provided id can not be null");
        this.id = id.toString();
    }
}
