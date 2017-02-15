package fr.esgi.gps.Model;

import com.google.gson.Gson;

import java.util.Date;

/**
 * Created by Pico on 15/02/2017.
 */

public class LocationJSON {

    public Date time;
    public Double latitude;
    public Double longitude;
    private Gson gson;

    public LocationJSON(Double latitude, Double longitude) {
        this.time = new Date();
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String json(){
        return gson.toJson(this);
    }
}
