package education.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmailTest {

    @Test
    public void testCreate() {
        var email = new Email("  tom@eMAIl.com");

        assertNotNull(email);
        assertEquals(email.email(), "tom@email.com");
    }

    @Test
    public void testSetEmailAddressThrowsIAE() {
        assertThrows(IllegalArgumentException.class, () -> {
            // the constructor of Email uses the setEmail() method
            new Email(null);
        });
    }

    @Test
    public void testEquality() {
        var email1 = new Email("tom@email.com");
        var email2 = new Email("bob@email.com");
        var email3 = new Email("tom@email.com");

        assertNotEquals(email1, email2);
        assertEquals(email1, email3);
    }
}
