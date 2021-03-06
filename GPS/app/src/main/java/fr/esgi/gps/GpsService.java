package fr.esgi.gps;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import fr.esgi.gps.Model.LocationJSON;
import fr.esgi.gps.Tools.CurrentValue;
import fr.esgi.gps.Tools.WebService;

/**
 * Created by srussier on 07/02/2017.
 */

public class GpsService extends Service implements LocationListener {

    // TODO : ajouter geoloc internet

    private final String TAG = "Gps.GpsService";

    private LocationManager locationManager;
    private WebService ws;

    @Override
    public void onCreate() {
        Log.v(TAG, "creation du GPSManager");

        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);


        //vérification de l'état du Gps
        if (locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)) {

            /* Compile malgré l'erreur affichée
             * ne sourtout pas ajouter une condition checkSelfPermission
             * ça fait crasher l'application avec une exception NULL !
            */

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, this);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 10, this);

            Log.v(TAG, locationManager.toString() + " gps enabled");
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        CurrentValue.getInstance().setGeolocalisation(location);

        ws.sendLocation(new LocationJSON(
                location.getLatitude(),
                location.getLongitude()
            )
        );
    }


    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

}

