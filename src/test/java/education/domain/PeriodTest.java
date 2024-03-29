package education.domain;

import org.junit.jupiter.api.Test;
import common.exceptions.IllegalDateException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PeriodTest {

    @Test
    public void testCreate() {
        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        var startDate = LocalDateTime.parse(LocalDateTime.now().plusHours(1).format(formatter), formatter);
        var endDate = LocalDateTime.parse(LocalDateTime.now().plusMonths(3).format(formatter), formatter);
        var period = new Period(startDate, endDate, TimeZone.getDefault());

        assertNotNull(period);
    }

    @Test
    public void testSetStartDateThrowsIAE() {
        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        var endDate = LocalDateTime.parse(LocalDateTime.now().plusMonths(3).format(formatter), formatter);

        assertThrows(IllegalArgumentException.class, () -> {
            // the constructor of Period uses the setStartDate() method
            new Period(null, endDate, TimeZone.getDefault());
        });
    }

    @Test
    public void testSetStartDateThrowsIDE() {
        // StartDate before CurrentDate
        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        var startDate = LocalDateTime.parse("12-01-2014 12:30:32", formatter);
        var endDate = LocalDateTime.parse(LocalDateTime.now().plusMonths(3).format(formatter), formatter);

        assertThrows(IllegalDateException.class, () -> {
            // the constructor of Period uses the setStartDate() method
            new Period(startDate, endDate, TimeZone.getDefault());
        });
    }

    @Test
    public void testSetEndDateThrowsIAE() {
        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        var startDate = LocalDateTime.parse(LocalDateTime.now().plusHours(1).format(formatter), formatter);

        assertThrows(IllegalArgumentException.class, () -> {
            // the constructor of Period uses the setEndDate() method
            new Period(startDate, null, TimeZone.getDefault());
        });
    }

    @Test
    public void testSetEndDateThrowsIDE() {
        // EndDate before StartDate
        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        var startDate = LocalDateTime.parse(LocalDateTime.now().plusHours(1).format(formatter), formatter);
        var endDate = LocalDateTime.parse("12-01-2014 12:30:32", formatter);

        assertThrows(IllegalDateException.class, () -> {
            // the constructor of Period uses the setEndDate() method
            new Period(startDate, endDate, TimeZone.getDefault());
        });
    }
}
