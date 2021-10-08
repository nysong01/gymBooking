package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

// Represents a list of booking and able to get booking information, add booking, remove booking,
// switch booking, and get number of bookings in the booking list.
public class ListOfBooking implements Writable {
    private List<Booking> bookingInfo;
    private Map<String, Booking> bookingMap;

    //EFFECTS: constructs an empty list of type Booking
    public ListOfBooking() {
        this.bookingInfo = new ArrayList<>();
        this.bookingMap = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: adds new Booking to ListOfBooking
    public void addBookingInfo(Booking b) {
        this.bookingInfo.add(b);
        this.bookingMap.put(b.getName(), b);
    }

    // EFFECTS: return bookings in a given day
    public Booking getBookingInfoWithKey(String name) {
        return bookingMap.get(name);
    }

    // MODIFIES: this
    // EFFECTS: removes a given booking in ListOfBooking
    public void removeBookingInfo(Booking b) {
        this.bookingInfo.remove(b);
        this.bookingMap.remove(b.getName(), b);
    }

    // REQUIRES: index >= 0
    // MODIFIES: this
    // EFFECTS: switch original Booking to given Booking in given index
    public void switchBooking(int index, Booking b) {
        int maxIndexNum = getSize() - 1;
        if (maxIndexNum >= index) {
            Booking booking = bookingInfo.get(index);
            this.bookingInfo.set(index, b);
            bookingMap.remove(booking.getName(), booking);
            bookingMap.put(b.getName(), b);
        }
    }

    // REQUIRES: index >= 0
    // EFFECTS: returns Booking at given index of ListOfBooking
    public Booking getBookingInfo(int index) {
        if (index < bookingInfo.size()) {
            return bookingInfo.get(index);
        }
        return null;
    }

    // EFFECTS: get size of the booking list
    public int getSize() {
        return this.bookingInfo.size();
    }

    // EFFECTS: returns an unmodifiable bookings in this list of booking
    public List<Booking> getBookings() {
        return Collections.unmodifiableList(bookingInfo);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("listOfBooking", bookingsToJson());
        return json;
    }

    // EFFECTS: returns bookings in this list of booking as a JSON array
    private JSONArray bookingsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Booking b : bookingInfo) {
            jsonArray.put(b.toJson());
        }

        return jsonArray;
    }

}
