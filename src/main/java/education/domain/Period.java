package education.domain;

import common.util.validators.Validate;

import java.time.LocalDateTime;
import java.util.TimeZone;

public final class Period {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private TimeZone timeZone;

    public Period(LocalDateTime startDate,
                  LocalDateTime endDate,
                  TimeZone timeZone) {

        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setTimeZone(timeZone);
    }

    // ---------- Public methods ----------

    public LocalDateTime startDate() {
        return this.startDate;
    }

    public LocalDateTime endDate() {
        return this.endDate;
    }

    public TimeZone timeZone() {
        return this.timeZone;
    }

    @Override
    public boolean equals(Object obj) {
        var isEqual = false;

        if (obj != null && obj.getClass() == this.getClass()) {
            var period = (Period) obj;
            isEqual = this.startDate().equals(period.startDate()) &&
                      this.endDate().equals(period.endDate()) &&
                      this.timeZone().equals(period.timeZone());
        }

        return isEqual;
    }

    // ---------- Private methods ----------

    private void setStartDate(LocalDateTime startDate) {
        Validate.argumentNotNull(startDate, "Provided startDate can not be null");
        Validate.notBefore(startDate, LocalDateTime.now(), "Provided startDate can not occur before currentDate");
        this.startDate = startDate;
    }

    private void setEndDate(LocalDateTime endDate) {
        Validate.argumentNotNull(endDate, "Provided endDate can not be null");
        Validate.notBefore(endDate, this.startDate(), "Provided endDate can not occur before startDate");
        this.endDate = endDate;
    }

    private void setTimeZone(TimeZone timeZone) {
        // checks here ...
        this.timeZone = timeZone;
    }

}
