package fr.esgi.gps.Activity;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fr.esgi.gps.Model.Utilisateur;
import fr.esgi.gps.R;
import fr.esgi.gps.Tools.WebService;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {

    Button buttonLogin, buttonExit;
    EditText editUser,editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin = (Button)findViewById(R.id.button_login);
        buttonExit = (Button)findViewById(R.id.button_cancel);

        editUser = (EditText)findViewById(R.id.edit_name);
        editPassword = (EditText)findViewById(R.id.edit_pass);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebService ws = new WebService();
                boolean result = ws.Connection(
                        editUser.getText().toString()
                        ,editPassword.getText().toString()
                );
                if (result){
                    Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Mauvais identifiant", Toast.LENGTH_LONG);
                }

            }
        });

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

