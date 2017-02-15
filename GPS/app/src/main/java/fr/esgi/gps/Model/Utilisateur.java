package fr.esgi.gps.Model;

/**
 * Created by Pico on 15/02/2017.
 */

public class Utilisateur {

    public String login;
    public String password;

    public Utilisateur() {}

    public Utilisateur(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
