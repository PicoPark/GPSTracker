package fr.esgi.gps.Tools;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.*;


import fr.esgi.gps.Model.LocationJSON;
import fr.esgi.gps.Model.Utilisateur;

/**
 * Created by Pico on 15/02/2017.
 */

public class WebService {

    private final String URL = "https://gpstracker-api.herokuapp.com/";


    Gson gson;

    public WebService() {
        gson = new Gson();
    }

    private InputStream sendRequest(URL url) throws Exception {

        try {
            // Ouverture de la connexion
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            // Connexion à l'URL
            urlConnection.connect();

            // Si le serveur nous répond avec un code OK
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return urlConnection.getInputStream();
            }
        } catch (Exception e) {
            throw new Exception("");
        }
        return null;
    }

    public boolean Connection(String user, String motDePasse) {
        StringBuilder url =new StringBuilder();
        url.append(URL).append("/")
                .append(user).append("/")
                .append(motDePasse);

        try {
            // Envoi de la requete
            InputStream inputStream = sendRequest(new URL(url.toString()));

            // Vérification de l'inputStream
            if(inputStream != null) {
                // Lecture de l'inputStream dans un reader
                InputStreamReader reader = new InputStreamReader(inputStream);

                // enregistrer résultat dans fichier

                // Retourne la liste désérialisée par le moteur GSON
                Utilisateur u = gson.fromJson(reader, new TypeToken<Utilisateur>(){}.getType());
                return true;
            }

        } catch (Exception e) {
            Log.e("WebService", "Impossible de rapatrier les données :(");

        }
        return false;
    }

    public void sendLocation(LocationJSON location){
        try {
            URL url = new URL(URL + "/location/root/123456 ");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true); // Pour pouvoir envoyer des données
            connection.setRequestMethod("POST");
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(location.json());
            writer.flush();

        }catch (Exception e){

        }

    }

    private void writingFile(String content){

        try {

            FileOutputStream fos= new FileOutputStream(CurrentValue.getInstance().getFileName());
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fos);
            outputStreamWriter.write(content);
            outputStreamWriter.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

