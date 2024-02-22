package com.example.db_interaction_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Connect to the webservice to insert the new rep into the database
     * @param view
     */
    public void registerRepresentative(View view) {
        // Get the new rep's username and password from the EditTexts
        EditText usernameEd = findViewById(R.id.rep_username_ed);
        String username = usernameEd.getText().toString();
        EditText passwordEd = findViewById(R.id.rep_password_ed);
        String password = passwordEd.getText().toString();

        // Connect to the webservice and send request
        sendRegisterRequest(username, password);

        // Display result
        Toast.makeText(this, "New rep: " + username + ", password=" + password, Toast.LENGTH_SHORT).show();

    }

    public void sendRegisterRequest(String username, String password) {
        try {
            URL url = new URL("http://localhost:8080/Placement_side_project/register");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            //urlConnection.setRequestProperty();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}