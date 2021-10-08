package persistence;

import model.Booking;
import model.ListOfBooking;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ListOfBooking lob = new ListOfBooking();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testWriterEmptyListOfBooking() {
        try {
            ListOfBooking lob = new ListOfBooking();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyListOfBooking.json");
            writer.open();
            writer.write(lob);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyListOfBooking.json");
            lob = reader.read();
            assertEquals(0, lob.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralListOfBooking() {
        try {
            ListOfBooking lob = new ListOfBooking();
            lob.addBookingInfo(new Booking("C","33333333","1:00pm","TUESDAY"));
            lob.addBookingInfo(new Booking("D","44444444","7:00am","THURSDAY"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralListOfBooking.json");
            writer.open();
            writer.write(lob);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralListOfBooking.json");
            lob = reader.read();
            assertEquals(2, lob.getSize());
            checkBooking("C","33333333","1:00pm","TUESDAY",lob.getBookingInfo(0));
            checkBooking("D","44444444","7:00am","THURSDAY",lob.getBookingInfo(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
