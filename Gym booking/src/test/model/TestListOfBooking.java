package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestListOfBooking {

    private ListOfBooking lob;
    private Booking b1 = new Booking("A", "1111111", "6:00pm", "MONDAY");
    private Booking b2 = new Booking("B","10101010","5:00am", "FRIDAY");
    private Booking b3 = new Booking("C", "333333333","9:00am", "TUESDAY");

    @BeforeEach
    void runBefore() {
        lob = new ListOfBooking();
    }


    @Test
    void testAddBooking() {
        lob.addBookingInfo(b1);

        assertEquals(lob.getBookingInfo(0), b1);
        assertEquals(lob.getBookingInfoWithKey(b1.getName()), b1);
    }

    @Test
    void testRemoveBooking() {
        lob.addBookingInfo(b1);
        lob.removeBookingInfo(b1);

        assertEquals(lob.getBookingInfo(0), null);
        assertEquals(lob.getBookingInfoWithKey(b1.getName()), null);
    }

    @Test
    void testSwitchBooking() {
        lob.addBookingInfo(b1);
        lob.addBookingInfo(b2);
        Booking newBooking = new Booking(b1.getName(),b1.getPhoneNumber(), "7:00am", b1.getDay());
        lob.switchBooking(0, newBooking);

        assertEquals(lob.getBookingInfo(0), newBooking);
        assertEquals(lob.getBookingInfoWithKey(b1.getName()), newBooking);
    }

    @Test
    void testSwitchBookingMoreThanMaxIndex() {
        lob.addBookingInfo(b1);
        lob.addBookingInfo(b2);
        Booking newBooking = new Booking(b1.getName(),b1.getPhoneNumber(), "7:00am", b1.getDay());
        lob.switchBooking(2, newBooking);

        assertEquals(lob.getBookingInfo(0), b1);
        assertEquals(lob.getBookingInfoWithKey(b1.getName()), b1);
        assertEquals(lob.getBookingInfo(1), b2);
        assertEquals(lob.getBookingInfoWithKey(b2.getName()), b2);
    }

    @Test
    void testGetBookingInfo() {
        lob.addBookingInfo(b1);
        lob.addBookingInfo(b2);

        assertEquals(lob.getBookingInfo(0),b1);
        assertEquals(lob.getBookingInfo(1),b2);
    }

    @Test
    void testGetBookingInfoWithKey() {
        lob.addBookingInfo(b1);
        lob.addBookingInfo(b2);

        assertEquals(lob.getBookingInfoWithKey(b1.getName()),b1);
        assertEquals(lob.getBookingInfoWithKey(b2.getName()),b2);
    }

    @Test
    void testGetBookingInfoNull() {
        lob.addBookingInfo(b1);
        lob.addBookingInfo(b2);

        assertEquals(lob.getBookingInfo(2),null);
        assertEquals(lob.getBookingInfoWithKey(b3.getName()), null);
    }

    @Test
    void testGetSize() {
        lob.addBookingInfo(b1);
        lob.addBookingInfo(b2);

        assertEquals(lob.getSize(),2);
    }



}
