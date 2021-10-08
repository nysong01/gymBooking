package model;

import org.json.JSONObject;
import persistence.Writable;


// Represents a Booking having a name, phone number, booking time, and day
public class Booking implements Writable {
    private String name;
    private String phoneNum;
    private String time;
    private String day;


     // MODIFIES: this
     // EFFECTS: make a new Booking by initializing the parameters
    public Booking(String accountName, String accountPhoneNumber, String time, String day) {
        this.name = accountName;
        this.phoneNum = accountPhoneNumber;
        this.time = time;
        this.day = day;
    }

    // EFFECTS: return owner name
    public String getName() {
        return name;
    }

    // EFFECTS: return owner phone number
    public String getPhoneNumber() {
        return phoneNum;
    }

    // EFFECTS: return time
    public String getTime() {
        return time;
    }

    // EFFECTS: return day
    public String getDay() {
        return day;
    }


    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("phoneNum", phoneNum);
        json.put("time", time);
        json.put("day",day);
        return json;
    }

}
