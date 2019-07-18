package education.domain;

import common.FullName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FullNameTest {

    @Test
    public void testCreateWithPrefix() {
        var fullName = new FullName("tom", "le", "bois");

        assertNotNull(fullName);
        assertEquals(fullName.firstName(), "tom");
        assertEquals(fullName.prefix(), "le");
        assertEquals(fullName.lastName(), "bois");
    }

    @Test
    public void testCreate() {
        var fullName = new FullName("tom", "bois");

        assertNotNull(fullName);
        assertEquals(fullName.firstName(), "tom");
        assertEquals(fullName.prefix(), null);
        assertEquals(fullName.lastName(), "bois");
    }

    @Test
    public void testSetFirstNameThrowsIAE() {
        assertThrows(IllegalArgumentException.class, () -> {
            // the constructor uses the setFirstName() method
            new FullName(null, "el", "bois");
        });
    }

    @Test
    public void testSetPrefixThrowsIAE() {
        assertThrows(IllegalArgumentException.class, () -> {
            // the constructor uses the setPrefix() method
            new FullName("tom", null, "bois");
        });
    }

    @Test
    public void testSetLastNameThowsIAE() {
        assertThrows(IllegalArgumentException.class, () -> {
            // the constructor uses the setLastName() method
            new FullName("tom", "le", null);
        });
    }

    @Test
    public void testEquality() {
        var fullName1 = new FullName("tom", "le", "bois");
        var fullName2 = new FullName("bob", "sneeze");
        var fullName3 = new FullName("tom", "le", "bois");

        assertNotEquals(fullName1, fullName2);
        assertEquals(fullName1, fullName3);
    }
}
