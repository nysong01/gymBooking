package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestBooking {
    private Booking testBooking;

    @BeforeEach
    void runBefore() {
        testBooking = new Booking("Nayoung", "1056691804", "6:00pm", "MONDAY");
    }

    @Test
    void testGetName() {
        assertEquals("Nayoung",testBooking.getName());
    }

    @Test
    void testGetPhoneNumber() {
        assertEquals("1056691804",testBooking.getPhoneNumber());
    }

    @Test
    void testGetTime() {
        assertEquals("6:00pm",testBooking.getTime());
    }

    @Test
    void testGetDay() {
        assertEquals("MONDAY",testBooking.getDay());
    }


}