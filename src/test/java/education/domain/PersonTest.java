package education.domain;

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
        var person1 = new Person(new Email("tom@email.com"),
                                 new FullName("tom", "el", "bois"));

        var person2 = new Person(new Email("bob@email.com"),
                                 new FullName("bob", "capitan"));

        var person3 = new Person(new Email("tom@email.com"),
                                 new FullName("tom", "el", "bois"));

        var person4 = new Person(new Email("tom@EMAIL.com  "),
                                 new FullName("toM", "el", "bOIs"));

        assertNotEquals(person1, person2);
        assertEquals(person1, person3);
        assertEquals(person1, person4);
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
