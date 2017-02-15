package fr.esgi.gps.Tools;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by srussier on 07/02/2017.
 */

public class CurrentValue {

    // volatile permet d'éviter le conflit d'utilisation des variable
    private volatile static CurrentValue shared;

    private volatile LatLng latLng;
    private String imei;

    private CurrentValue() {}

    public static CurrentValue getInstance() {
        if (shared == null) {
            shared = new CurrentValue();
        }
        return shared;
    }

    public void setGeolocalisation(Location location) {
        latLng = new LatLng( location.getLatitude(),location.getLongitude());
    }

    public LatLng getGeolocalisation() {
        return latLng;
    }

}
