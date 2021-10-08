package persistence;

import model.Booking;
import model.ListOfBooking;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ListOfBooking lob = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyListOfBooking() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyListOfBooking.json");
        try {
            ListOfBooking lob = reader.read();
            assertEquals(0, lob.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralListOfBooking() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralListOfBooking.json");
        try {
            ListOfBooking lob = reader.read();
            List<Booking> bookingInfo = lob.getBookings();
            assertEquals(2, lob.getSize());
            checkBooking("A", "11111111", "10:00pm","MONDAY",bookingInfo.get(0));
            checkBooking("B", "22222222", "9:00am","FRIDAY",bookingInfo.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }























}
