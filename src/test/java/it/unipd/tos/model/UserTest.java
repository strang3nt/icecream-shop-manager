package it.unipd.tos.model;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class UserTest {
    User customer = new User("Mario Rossi", LocalDate.of(1990, 10, 10));
    User minor = new User("Giovannino Verdi", LocalDate.of(2006, 7, 25));

    @Test
    public void equals_checkIfEquals_expectedBehaviour() {
        User copyCustomer = new User("Mario Rossi", LocalDate.of(1990, 10, 10));

        assertEquals(false, customer.equals(minor));
        assertEquals(true, customer.equals(copyCustomer));
    }

    @Test
    public void isMinor_bothMinorAndAdult_expectedBehaviour() {
        assertEquals(false, minor.isAdult());
        assertEquals(true, customer.isAdult());
    }

    @Test
    public void isMinor_barelyLegal_isAnAdult() {
        User justAdult = new User("Luca De Rossi", LocalDate.now().minusYears(18));

        assertEquals(true, justAdult.isAdult());
    }
 
}
