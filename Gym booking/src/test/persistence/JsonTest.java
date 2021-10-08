package persistence;

import model.Booking;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkBooking(String name, String phoneNum, String time, String day, Booking booking) {
        assertEquals(name, booking.getName());
        assertEquals(phoneNum, booking.getPhoneNumber());
        assertEquals(time, booking.getTime());
        assertEquals(day, booking.getDay());

    }
}
