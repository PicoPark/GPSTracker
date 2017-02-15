package fr.esgi.gps.Tools;

import android.location.Location;
import android.os.Environment;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by srussier on 07/02/2017.
 */

public class CurrentValue {

    // volatile permet d'éviter le conflit d'utilisation des variable
    private volatile static CurrentValue shared;

    private volatile LatLng latLng;
    private String imei;
    private final String DIR_NAME = Environment.getExternalStorageDirectory() + "/gps";
    private final String FILE_NAME = "/settings.txt";

    private CurrentValue() {
        File dir = new File(DIR_NAME);
        dir.mkdirs();

    }

    public static CurrentValue getInstance() {
        if (shared == null) {
            shared = new CurrentValue();
        }
        return shared;
    }

    public void setGeolocalisation(Location location) {
        latLng = new LatLng(
                location.getLatitude(),
                location.getLongitude()
        );
    }

    public LatLng getGeolocalisation() {
        return latLng;
    }

    public String getFileName() {
        return DIR_NAME + FILE_NAME;
    }

    public boolean isFileExist(){
        try{
            BufferedReader In = new BufferedReader(new FileReader(getFileName()));
           return true;
        } catch (FileNotFoundException fnfe) {
            return false;
        }
    }
}
