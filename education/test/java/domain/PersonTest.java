package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    @Test
    public void testCreate() {
        var email = new Email("tom@email.com");
        var fullName = new FullName("tom", "el", "bois");
        var person = new Person(email, fullName);

        assertNotNull(person);
        assertEquals(person.email(), email);
        assertEquals(person.fullName(), fullName);
    }

    @Test
    public void testEquality() {
        var email1 = new Email("tom@email.com");
        var fullName1 = new FullName("tom", "el", "bois");
        var person1 = new Person(email1, fullName1);

        var email2 = new Email("bob@email.com");
        var fullName2 = new FullName("bob", "capitan");
        var person2 = new Person(email2, fullName2);

        var email3 = new Email("tom@email.com");
        var fullName3 = new FullName("tom", "el", "bois");
        var person3 = new Person(email3, fullName3);

        assertNotEquals(person1, person2);
        assertEquals(person1, person3);
    }

    @Test
    public void testSetFullNameNullThrowsIAE() {
        var email = new Email("tom@email.com");

        assertThrows(IllegalArgumentException.class, () -> {
            // the constructor of Person uses the setFullName() method
            new Person(email, null);
        });
    }

    @Test
    public void testSetEmailNullThrowsIAE() {
        var fullName = new FullName("tom", "el", "bois");

        assertThrows(IllegalArgumentException.class, () -> {
            /// the constructor of Person uses the setEmail() method
            new Person(null, fullName);
        });
    }
}
