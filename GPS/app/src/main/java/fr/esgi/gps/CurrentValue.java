package fr.esgi.gps;

import android.location.Location;

/**
 * Created by srussier on 07/02/2017.
 */

public class CurrentValue {

    // volatile permet d'éviter le conflit d'utilisation des variable
    private volatile static CurrentValue shared;

    private volatile double longitude;
    private volatile double latitude;
    private String imei;

    private CurrentValue() {}

    public static CurrentValue getInstance() {
        if (shared == null) {
            shared = new CurrentValue();
        }
        return shared;
    }

    public void setGeolocalisation(Location location) {
        this.longitude = location.getLongitude();
        this.latitude = location.getLatitude();
    }

}
