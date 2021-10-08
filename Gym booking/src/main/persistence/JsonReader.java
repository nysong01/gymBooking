package persistence;

import model.Booking;
import model.ListOfBooking;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads bookings from JSON data stored in file
// Citation: code obtained from JsonSerializationDemo
//           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads list of booking from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfBooking read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfBooking(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses listOfBooking from JSON object and returns it
    private ListOfBooking parseListOfBooking(JSONObject jsonObject) {
        ListOfBooking lob = new ListOfBooking();
        addBookings(lob, jsonObject);
        return lob;
    }

    // MODIFIES: lob
    // EFFECTS: parses bookings from JSON object and adds them to listOfBooking
    private void addBookings(ListOfBooking lob, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("listOfBooking");
        for (Object json : jsonArray) {
            JSONObject nextBooking = (JSONObject) json;
            addBooking(lob, nextBooking);
        }
    }

    // MODIFIES: lob
    // EFFECTS: parses booking from JSON object and adds it to listOfBooking
    private void addBooking(ListOfBooking lob, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String phoneNum = jsonObject.getString("phoneNum");
        String time = jsonObject.getString("time");
        String day = jsonObject.getString("day");
        Booking booking = new Booking(name, phoneNum, time, day);
        lob.addBookingInfo(booking);
    }
}